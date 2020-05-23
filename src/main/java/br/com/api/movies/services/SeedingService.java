package br.com.api.movies.services;

import br.com.api.movies.entities.User;
import br.com.api.movies.entities.enums.Profile;
import br.com.api.movies.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SeedingService {

    private final UserRepository userRepository;

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
    }
}