package br.com.api.movies.repositories;

import br.com.api.movies.MoviesApplication;
import br.com.api.movies.entities.Media;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MoviesApplication.class)
@ActiveProfiles("dev")
public class MediaRepositoryTest {

    @Autowired
    private MediaRepository mediaRepository;

    /**
     * setValues
     *
     */
    @BeforeEach
    public void setValues() {
        Media media = new Media();
        media.setId(1396L);
        media.setName("English Breaking Bad");
        media.setOriginalName("Breaking Bad");
        media.setCharacter("Walter White");
        this.mediaRepository.save(media);
    }

    /**
     * saveMediaTest
     *
     */
    @Test
    public void saveMediaTest() {
        Media media = new Media();
        media.setId(1396L);
        media.setName("English Breaking Bad");
        media.setOriginalName("Breaking Bad");
        media.setCharacter("Walter White");
        this.mediaRepository.save(media);

        Assertions.assertThat(media.getId()).isNotNull();
        Assertions.assertThat(media.getName()).isEqualTo("English Breaking Bad");
    }

    /**
     * listaAllMedias
     *
     */
    @Test
    public void listaAllMedias() {
        List<Media> medias = this.mediaRepository.findAll();
        Assertions.assertThat(medias.size()).isEqualTo(1);
    }

    /**
     * listMediaById
     *
     */
    @Test
    public void listMediaById() {
        Media media = this.mediaRepository.findById(1L).get();
        Assertions.assertThat(media.getId()).isEqualTo(1L);
    }
}