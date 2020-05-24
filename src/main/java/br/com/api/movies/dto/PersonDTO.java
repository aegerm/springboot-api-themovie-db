package br.com.api.movies.dto;

import br.com.api.movies.entities.Person;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;

    /**
     * PersonDTO
     *
     * @param person
     */
    public PersonDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
    }
}