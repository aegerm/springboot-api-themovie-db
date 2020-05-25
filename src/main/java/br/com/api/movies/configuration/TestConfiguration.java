package br.com.api.movies.configuration;

import br.com.api.movies.services.SeedingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestConfiguration {

    private final SeedingService seedingService;

    /**
     * initDatabase
     *
     */
    @Bean
    public void initDatabase() {
        seedingService.seedTest();
    }
}