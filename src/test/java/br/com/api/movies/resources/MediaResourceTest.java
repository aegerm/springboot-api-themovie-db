package br.com.api.movies.resources;

import br.com.api.movies.MoviesApplication;
import br.com.api.movies.dto.CredentialDTO;
import br.com.api.movies.entities.Media;
import br.com.api.movies.repositories.MediaRepository;
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
@ActiveProfiles("test")
public class MediaResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private MediaRepository mediaRepository;
    private String token;

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
        var requestEntity = restTemplate.exchange("/medias", HttpMethod.GET, null, Media.class);
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, requestEntity.getStatusCode());
    }

    /**
     * listMediaTest
     *
     */
    @Test
    public void listMediaTest() {
        Media media = new Media();
        media.setId(1396L);
        media.setName("English Breaking Bad");
        media.setOriginalName("Breaking Bad");
        media.setCharacter("Walter White");

        BDDMockito.when(this.mediaRepository.findAll()).thenReturn(Arrays.asList(media));

        ResponseEntity<List<Media>> response =
                this.restTemplate.exchange("/medias", HttpMethod.GET,
                        new HttpEntity<>("parameters", httpHeaders()),
                        new ParameterizedTypeReference<>() {});

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    /**
     * listMediaByIdTest
     *
     */
    @Test
    public void listMediaByIdTest() {
        Media media = new Media();
        media.setId(1396L);
        media.setName("English Breaking Bad");
        media.setOriginalName("Breaking Bad");
        media.setCharacter("Walter White");

        BDDMockito.when(this.mediaRepository.findById(1396L)).thenReturn(Optional.of(media));

        ResponseEntity<Media> response =
                this.restTemplate.exchange("/medias/1396", HttpMethod.GET,
                        new HttpEntity<>("parameters", httpHeaders()),
                        Media.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    /**
     * listMediaByIdNotFoundTest
     *
     */
    @Test
    public void listMediaByIdNotFoundTest() {
        ResponseEntity<Media> response =
                this.restTemplate.exchange("/medias/11", HttpMethod.GET,
                        new HttpEntity<>("parameters", httpHeaders()),
                        Media.class);

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