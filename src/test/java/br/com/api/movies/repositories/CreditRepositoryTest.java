package br.com.api.movies.repositories;

import br.com.api.movies.MoviesApplication;
import br.com.api.movies.entities.Credit;
import br.com.api.movies.entities.Media;
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
@ActiveProfiles("test")
@EnableJpaRepositories(basePackageClasses = CreditRepository.class)
public class CreditRepositoryTest {

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MediaRepository mediaRepository;

    /**
     * setValues
     *
     */
    @BeforeEach
    public void setValues() {
        Person person = new Person();
        person.setName("Bryan Cranston");
        this.personRepository.save(person);

        Media media = new Media();
        media.setName("English Breaking Bad");
        media.setOriginalName("Breaking Bad");
        media.setCharacter("Walter White");
        this.mediaRepository.save(media);

        Credit credit = new Credit();
        credit.setCreditType("cast");
        credit.setDepartment("Actors");
        credit.setJob("Actor");
        credit.setMediaType("tv");
        credit.setPerson(person);
        credit.setMedia(media);

        this.creditRepository.save(credit);
    }

    /**
     * saveCreditTest
     *
     */
    @Test
    public void saveCreditTest() {
        Person person = new Person();
        person.setName("Bryan Cranston");
        this.personRepository.save(person);

        Media media = new Media();
        media.setName("English Breaking Bad");
        media.setOriginalName("Breaking Bad");
        media.setCharacter("Walter White");
        this.mediaRepository.save(media);

        Credit credit = new Credit();
        credit.setCreditType("cast");
        credit.setDepartment("Actors");
        credit.setJob("Actor");
        credit.setMediaType("tv");
        credit.setPerson(person);
        credit.setMedia(media);

        this.creditRepository.save(credit);

        Assertions.assertThat(credit.getId()).isNotNull();
        Assertions.assertThat(credit.getCreditType()).isEqualTo("cast");
        Assertions.assertThat(credit.getDepartment()).isEqualTo("Actors");
        Assertions.assertThat(credit.getPerson()).isEqualTo(person);
        Assertions.assertThat(credit.getMedia()).isEqualTo(media);
    }

    /**
     * listaAllCredits
     *
     */
    @Test
    public void listaAllCredits() {
        List<Credit> credits = this.creditRepository.findAll();
        Assertions.assertThat(credits.size()).isEqualTo(1);
    }

    /**
     * listCreditById
     *
     */
    @Test
    public void listCreditById() {
        Credit credit = this.creditRepository.findById(1L).get();
        Assertions.assertThat(credit.getId()).isEqualTo(1L);
    }
}