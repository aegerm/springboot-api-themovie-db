package br.com.api.movies.resources;

import br.com.api.movies.MoviesApplication;
import br.com.api.movies.dto.CredentialDTO;
import br.com.api.movies.entities.Credit;
import br.com.api.movies.entities.Media;
import br.com.api.movies.entities.Person;
import br.com.api.movies.entities.Season;
import br.com.api.movies.repositories.CreditRepository;
import br.com.api.movies.repositories.MediaRepository;
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
public class CreditResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private CreditRepository creditRepository;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private MediaRepository mediaRepository;
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
        var requestEntity = restTemplate.exchange("/credits", HttpMethod.GET, null, Credit.class);
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, requestEntity.getStatusCode());
    }

    /**
     * listCreditsTest
     *
     */
    @Test
    public void listCreditsTest() {
        Media media = new Media();
        media.setId(1396L);
        media.setName("English Breaking Bad");
        media.setOriginalName("Breaking Bad");
        media.setCharacter("Walter White");

        BDDMockito.when(this.mediaRepository.findById(1396L)).thenReturn(Optional.of(media));

        Person person1 = new Person();
        person1.setId(17419L);
        person1.setName("Bryan Cranston");

        BDDMockito.when(this.personRepository.findById(17419L)).thenReturn(Optional.of(person1));

        Credit credit1 = new Credit();
        credit1.setCreditType("cast");
        credit1.setDepartment("Actors");
        credit1.setJob("Actor");
        credit1.setMediaType("tv");
        credit1.setPerson(person1);
        credit1.setMedia(media);

        List<Credit> credits = Arrays.asList(credit1);
        BDDMockito.when(this.creditRepository.findAll()).thenReturn(credits);

        ResponseEntity<List<Person>> response =
                this.restTemplate.exchange("/persons", HttpMethod.GET,
                        new HttpEntity<>("parameters", httpHeaders()),
                        new ParameterizedTypeReference<>() {});

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    /**
     * listCreditByIdTest
     *
     */
    @Test
    public void listCreditByIdTest() {
        Media media = new Media();
        media.setId(1396L);
        media.setName("English Breaking Bad");
        media.setOriginalName("Breaking Bad");
        media.setCharacter("Walter White");

        BDDMockito.when(this.mediaRepository.findById(1396L)).thenReturn(Optional.of(media));

        Person person1 = new Person();
        person1.setId(17419L);
        person1.setName("Bryan Cranston");

        BDDMockito.when(this.personRepository.findById(17419L)).thenReturn(Optional.of(person1));

        Credit credit1 = new Credit();
        credit1.setCreditType("cast");
        credit1.setDepartment("Actors");
        credit1.setJob("Actor");
        credit1.setMediaType("tv");
        credit1.setPerson(person1);
        credit1.setMedia(media);

        BDDMockito.when(this.creditRepository.findById(1L)).thenReturn(Optional.of(credit1));

        ResponseEntity<Season> response =
                this.restTemplate.exchange("/credits/1", HttpMethod.GET,
                        new HttpEntity<>("parameters", httpHeaders()),
                        Season.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    /**
     * listCreditByIdNotFoundTest
     *
     */
    @Test
    public void listCreditByIdNotFoundTest() {
        ResponseEntity<Season> response =
                this.restTemplate.exchange("/credits/5", HttpMethod.GET,
                        new HttpEntity<>("parameters", httpHeaders()),
                        Season.class);

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