package cl.dpichinil.test.springbatch.writer;

import cl.dpichinil.test.springbatch.model.dto.PersonDto;
import cl.dpichinil.test.springbatch.util.Parse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonItemWriter implements ItemWriter<PersonDto> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public void write(List<? extends PersonDto> list) throws Exception {
        LOGGER.info("Escribiendo los movimientos {}", list);
        for(PersonDto person : list){
            String json = Parse.ObjectToJson(person);
            LOGGER.info(String.format("json: (%s)",json));
        }
    }
}
