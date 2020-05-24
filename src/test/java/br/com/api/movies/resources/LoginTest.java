package br.com.api.movies.resources;

import br.com.api.movies.MoviesApplication;
import br.com.api.movies.dto.CredentialDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MoviesApplication.class)
@ActiveProfiles("dev")
public class LoginTest {

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * loginSuccess
     *
     */
    @Test
    public void loginSuccess() {
        ResponseEntity<String> response = restTemplate.postForEntity("/login", new CredentialDTO("uadm", "admin"), String.class);
        String _token = response.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(_token);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
}
