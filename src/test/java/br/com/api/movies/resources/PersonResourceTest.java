package br.com.api.movies.resources;

import br.com.api.movies.MoviesApplication;
import br.com.api.movies.dto.CredentialDTO;
import br.com.api.movies.entities.Person;
import br.com.api.movies.repositories.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MoviesApplication.class)
@ActiveProfiles("dev")
public class PersonResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PersonRepository personRepository;
    private String token;

    /**
     * setup
     *
     */
    @BeforeEach
    public void setup() {
        ResponseEntity<String> token = restTemplate.postForEntity("/login", new CredentialDTO("uadm", "admin"), String.class);
        String _token = token.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        this.token = _token;
    }

    /**
     * returnError401NotAuth
     *
     */
    @Test
    public void returnError401NotAuth() {
        var requestEntity = restTemplate.exchange("/persons", HttpMethod.GET, null, Person.class);
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, requestEntity.getStatusCode());
    }

    /**
     * listPersonsTest
     *
     */
    @Test
    public void listPersonsTest() {
        Person person1 = new Person();
        person1.setName("Dean Winchester");
        Person person2 = new Person();
        person2.setName("Sam Winchester");
        Person person3 = new Person();
        person3.setName("John Winchester");

        List<Person> personList = Arrays.asList(person1, person2, person3);
        BDDMockito.when(this.personRepository.findAll()).thenReturn(personList);

        ResponseEntity<List<Person>> response =
                this.restTemplate.exchange("/persons", HttpMethod.GET,
                new HttpEntity<>("parameters", httpHeaders()),
                new ParameterizedTypeReference<>() {});

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    /**
     * listPersonByIdTest
     *
     */
    @Test
    public void listPersonByIdTest() {
        Person person1 = new Person();
        person1.setId(1L);
        person1.setName("Dean Winchester");

        BDDMockito.when(this.personRepository.findById(1L)).thenReturn(Optional.of(person1));

        ResponseEntity<Person> response =
                this.restTemplate.exchange("/persons/1", HttpMethod.GET,
                new HttpEntity<>("parameters", httpHeaders()),
                Person.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    /**
     * listPersonByIdNotFoundTest
     *
     */
    @Test
    public void listPersonByIdNotFoundTest() {
        ResponseEntity<Person> response =
                this.restTemplate.exchange("/persons/2", HttpMethod.GET,
                        new HttpEntity<>("parameters", httpHeaders()),
                        Person.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    /**
     * httpHeaders
     *
     * @return
     */
    private HttpHeaders httpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.add("Authorization", this.token);
        return httpHeaders;
    }
}