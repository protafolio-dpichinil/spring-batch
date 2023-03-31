package cl.dpichinil.test.springbatch.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private String dni;
    private String firstName;
    private String secondName;
    private String lastName;
    private String secondLastName;
}
