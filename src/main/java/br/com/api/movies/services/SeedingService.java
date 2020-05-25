package br.com.api.movies.services;

import br.com.api.movies.entities.*;
import br.com.api.movies.entities.enums.Profile;
import br.com.api.movies.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SeedingService {

    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final MediaRepository mediaRepository;
    private final SeasonRepository seasonRepository;
    private final CreditRepository creditRepository;

    /**
     * seed
     *
     */
    public void seed() {
        User user = new User();
        user.setName("User Admin");
        user.setLogin("uadm");
        user.setPassword(new BCryptPasswordEncoder().encode("admin"));
        user.addProfile(Profile.ADMIN);
        userRepository.save(user);

        User user1 = new User();
        user1.setName("User Explorer");
        user1.setLogin("uexp");
        user1.setPassword(new BCryptPasswordEncoder().encode("root"));
        user1.addProfile(Profile.USER);
        userRepository.save(user1);

        Person person = new Person();
        person.setName("Bryan Cranston");
        personRepository.save(person);

        Media media = new Media();
        media.setName("English Breaking Bad");
        media.setOriginalName("Breaking Bad");
        media.setCharacter("Walter White");
        mediaRepository.save(media);

        Season season1 = new Season();
        season1.setAirDate("20-01-2008");
        season1.setSeasonNumber(1);
        season1.setMedia(media);

        Season season2 = new Season();
        season2.setAirDate("08-03-2009");
        season2.setSeasonNumber(2);
        season2.setMedia(media);

        Season season3 = new Season();
        season3.setAirDate("21-03-2010");
        season3.setSeasonNumber(3);
        season3.setMedia(media);

        Season season4 = new Season();
        season4.setAirDate("17-07-2011");
        season4.setSeasonNumber(4);
        season4.setMedia(media);

        Season season5 = new Season();
        season5.setAirDate("15-07-2012");
        season5.setSeasonNumber(5);
        season5.setMedia(media);
        seasonRepository.saveAll(Arrays.asList(season1, season2, season3, season4, season5));

        Credit credit = new Credit();
        credit.setCreditType("cast");
        credit.setDepartment("Actors");
        credit.setJob("Actor");
        credit.setMediaType("tv");
        credit.setMedia(media);
        credit.setPerson(person);
        creditRepository.save(credit);
    }
}