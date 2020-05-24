package br.com.api.movies.resources;

import br.com.api.movies.MoviesApplication;
import br.com.api.movies.dto.CredentialDTO;
import br.com.api.movies.entities.Media;
import br.com.api.movies.entities.Season;
import br.com.api.movies.repositories.MediaRepository;
import br.com.api.movies.repositories.SeasonRepository;
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

import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MoviesApplication.class)
@ActiveProfiles("dev")
public class SeasonResourceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private SeasonRepository seasonRepository;

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
        var requestEntity = restTemplate.exchange("/seasons", HttpMethod.GET, null, Season.class);
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, requestEntity.getStatusCode());
    }

    /**
     * listSeasonTest
     *
     */
    @Test
    public void listSeasonTest() {
        Media media = new Media();
        media.setId(1396L);
        media.setName("English Breaking Bad");
        media.setOriginalName("Breaking Bad");
        media.setCharacter("Walter White");

        BDDMockito.when(this.mediaRepository.findById(1396L)).thenReturn(Optional.of(media));

        Date d1 = new Date();
        d1.setTime(1342310400);

        Season season1 = new Season();
        season1.setAirDate(new SimpleDateFormat("yyyy-MM-dd").format(d1));
        season1.setSeasonNumber(5);
        season1.setMedia(media);

        List<Season> seasonList = Arrays.asList(season1);
        BDDMockito.when(this.seasonRepository.findAll()).thenReturn(seasonList);

        ResponseEntity<List<Season>> response =
                this.restTemplate.exchange("/seasons", HttpMethod.GET,
                        new HttpEntity<>("parameters", httpHeaders()),
                        new ParameterizedTypeReference<>() {});

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    /**
     * listSeasonByIdTest
     *
     */
    @Test
    public void listSeasonByIdTest() {
        Media media = new Media();
        media.setId(1396L);
        media.setName("English Breaking Bad");
        media.setOriginalName("Breaking Bad");
        media.setCharacter("Walter White");

        BDDMockito.when(this.mediaRepository.findById(1396L)).thenReturn(Optional.of(media));

        Date d1 = new Date();
        d1.setTime(1342310400);

        Season season1 = new Season();
        season1.setId(1L);
        season1.setAirDate(new SimpleDateFormat("yyyy-MM-dd").format(d1));
        season1.setSeasonNumber(5);
        season1.setMedia(media);

        BDDMockito.when(this.seasonRepository.findById(1L)).thenReturn(Optional.of(season1));

        ResponseEntity<Season> response =
                this.restTemplate.exchange("/seasons/1", HttpMethod.GET,
                        new HttpEntity<>("parameters", httpHeaders()),
                        Season.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    /**
     * listSeasonByIdNotFoundTest
     *
     */
    @Test
    public void listSeasonByIdNotFoundTest() {
        ResponseEntity<Season> response =
                this.restTemplate.exchange("/seasons/50", HttpMethod.GET,
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