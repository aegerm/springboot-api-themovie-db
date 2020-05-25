package br.com.api.movies.repositories;

import br.com.api.movies.MoviesApplication;
import br.com.api.movies.entities.Media;
import br.com.api.movies.entities.Season;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MoviesApplication.class)
@ActiveProfiles("test")
@EnableJpaRepositories(basePackageClasses = SeasonRepository.class)
public class SeasonRepositoryTest {

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private MediaRepository mediaRepository;

    /**
     * setValues
     *
     */
    @BeforeEach
    public void setValues() {
        Media media = new Media();
        media.setName("English Breaking Bad");
        media.setOriginalName("Breaking Bad");
        media.setCharacter("Walter White");

        this.mediaRepository.save(media);

        Season season1 = new Season();
        season1.setId(1L);
        season1.setAirDate("15-07-2012");
        season1.setSeasonNumber(5);
        season1.setMedia(media);

        this.seasonRepository.save(season1);
    }

    /**
     * saveSeasonTest
     *
     */
    @Test
    public void saveSeasonTest() {
        Media media = new Media();
        media.setName("English Breaking Bad");
        media.setOriginalName("Breaking Bad");
        media.setCharacter("Walter White");

        this.mediaRepository.save(media);

        Season season1 = new Season();
        season1.setId(1L);
        season1.setAirDate("15-07-2012");
        season1.setSeasonNumber(5);
        season1.setMedia(media);

        this.seasonRepository.save(season1);

        Assertions.assertThat(season1.getId()).isNotNull();
        Assertions.assertThat(season1.getSeasonNumber()).isEqualTo(5);
    }

    /**
     * listaAllSeasons
     *
     */
    @Test
    public void listaAllSeasons() {
        List<Season> seasons = this.seasonRepository.findAll();
        Assertions.assertThat(seasons.size()).isEqualTo(1);
    }

    /**
     * listSeasonById
     *
     */
    @Test
    public void listSeasonById() {
        Season season = this.seasonRepository.findById(1L).get();
        Assertions.assertThat(season.getId()).isEqualTo(1L);
    }
}