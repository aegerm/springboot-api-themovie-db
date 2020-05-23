package br.com.api.movies.services;

import br.com.api.movies.entities.Media;
import br.com.api.movies.entities.Season;
import br.com.api.movies.repositories.MediaRepository;
import br.com.api.movies.repositories.SeasonRepository;
import br.com.api.movies.services.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SeasonService {

    private final SeasonRepository seasonRepository;
    private final MediaRepository mediaRepository;

    /**
     * saveSeason
     *
     * @param mediaId
     * @param season
     */
    public void saveSeason(Long mediaId, Season season) {
        log.info("Salvando informações de uma temporada");
        this.mediaRepository.findById(mediaId).map(media -> {
            season.setMedia(media);
            return this.seasonRepository.save(season);
        }).orElseThrow(() -> new ObjectNotFoundException("Media is null: " + Media.class.getName()));
    }

    /**
     * findAll
     *
     * @return
     */
    public List<Season> findAll() {
        log.info("Buscando todas temporadas");
        List<Season> seasons = this.seasonRepository.findAll();
        return seasons;
    }

    /**
     * findById
     *
     * @param id
     * @return
     */
    public Season findById(Long id) {
        log.info("Buscando informações de uma temporada pelo Id");
        Optional<Season> season = this.seasonRepository.findById(id);
        return season.orElseThrow(() -> new ObjectNotFoundException("Season is null: " + Season.class.getName()));
    }
}