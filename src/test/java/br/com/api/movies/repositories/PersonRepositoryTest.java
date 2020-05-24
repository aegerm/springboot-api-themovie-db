package br.com.api.movies.repositories;

import br.com.api.movies.MoviesApplication;
import br.com.api.movies.entities.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MoviesApplication.class)
@ActiveProfiles("dev")
@EnableJpaRepositories(basePackageClasses = PersonRepository.class)
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    /**
     * setValues
     *
     */
    @BeforeEach
    public void setValues() {
        Person person = new Person();
        person.setId(1L);
        person.setName("Bryan Cranston");
        this.personRepository.save(person);
    }

    /**
     * savePersonTest
     *
     */
    @Test
    public void savePersonTest() {
        Person person = new Person();
        person.setName("Bryan Cranston");
        this.personRepository.save(person);

        Assertions.assertThat(person.getId()).isNotNull();
        Assertions.assertThat(person.getName()).isEqualTo("Bryan Cranston");
    }

    /**
     * listAllPersons
     *
     */
    @Test
    public void listAllPersons() {
        List<Person> personList = this.personRepository.findAll();
        Assertions.assertThat(personList.size()).isEqualTo(1);
    }

    /**
     * listPersonById
     *
     */
    @Test
    public void listPersonById() {
        Person person = this.personRepository.findById(1L).get();
        Assertions.assertThat(person.getId()).isEqualTo(1L);
    }
}