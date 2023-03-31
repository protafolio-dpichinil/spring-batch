package cl.dpichinil.test.springbatch.processor;

import cl.dpichinil.test.springbatch.model.dto.PersonDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;


public class PersonItemProcesor implements ItemProcessor<PersonDto, PersonDto> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public PersonDto process(PersonDto person) throws Exception {
        PersonDto response = new PersonDto(
                person.getDni().toUpperCase(),
                person.getFirstName().toUpperCase(),
                person.getSecondName().toUpperCase(),
                person.getLastName().toUpperCase(),
                person.getSecondLastName().toUpperCase()
                );
        logger.info(String.format("Convirtiendo (%s) a (%s)", person.toString(), response.toString()));
        return response;
    }
}
