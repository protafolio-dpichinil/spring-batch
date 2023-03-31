package cl.dpichinil.test.springbatch.config;

import cl.dpichinil.test.springbatch.listener.JobListener;
import cl.dpichinil.test.springbatch.model.dto.PersonDto;
import cl.dpichinil.test.springbatch.processor.PersonItemProcesor;
import cl.dpichinil.test.springbatch.writer.PersonItemWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BashConfiguration {
    public final JobBuilderFactory jobBuilderFactory;

    public final StepBuilderFactory stepBuilderFactory;
    @Value("${file.input}")
    private String fileInput;


    @Bean
    public Job importPersonJob(JobListener jobListener, Step stepOne){
        return jobBuilderFactory.get("importPersonaJob")
                .incrementer(new RunIdIncrementer())
                .listener(jobListener)
                .flow(stepOne)
                .end()
                .build();
    }

    @Bean
    public Step stepOne(){
        return stepBuilderFactory.get("stepOne")
                .<PersonDto, PersonDto> chunk(2)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public FlatFileItemReader<PersonDto> reader (){
        return new FlatFileItemReaderBuilder<PersonDto>()
                .name("personaItemReader")
                .resource(new PathResource(fileInput))
                .delimited()
                .delimiter(";")
                .names(new String[] {"dni","firstName", "secondName", "lastName", "secondLastName"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<PersonDto>(){{
                    setTargetType(PersonDto.class);
                }})
                .build();
    }

    @Bean
    public PersonItemProcesor processor(){
        return new PersonItemProcesor();
    }

    @Bean
    public PersonItemWriter writer (){
        return new PersonItemWriter();
    }


}
