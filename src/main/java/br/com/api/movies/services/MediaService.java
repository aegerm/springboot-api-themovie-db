package br.com.api.movies.services;

import br.com.api.movies.entities.Media;
import br.com.api.movies.repositories.MediaRepository;
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
public class MediaService {

    private final MediaRepository mediaRepository;

    /**
     * saveMedia
     *
     * @param media
     */
    public void saveMedia(Media media) {
        log.info("Salvando tipo de mídia.");
        this.mediaRepository.save(media);
    }

    /**
     * findAll
     *
     * @return
     */
    public List<Media> findAll() {
        log.info("Listando todas as mídias.");
        List<Media> mediaList = this.mediaRepository.findAll();
        return mediaList;
    }

    /**
     * findById
     *
     * @param id
     * @return
     */
    public Media findById(Long id) {
        log.info("Buscando mídia pelo Id.");
        Optional<Media> media = this.mediaRepository.findById(id);
        return media.orElseThrow(() -> new ObjectNotFoundException("Media is null: " + Media.class.getName()));
    }
}