package br.com.api.movies.resources;

import br.com.api.movies.entities.Season;
import br.com.api.movies.services.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/seasons")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SeasonResource {

    private final SeasonService seasonService;

    /**
     * findSeasons
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Season>> findSeasons() {
        List<Season> seasons = this.seasonService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(seasons);
    }

    /**
     * findSeasonById
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<Season> findSeasonById(@PathVariable Long id) {
        Season season = this.seasonService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(season);
    }

    /**
     * saveSeason
     *
     * @param mediaId
     * @param season
     * @return
     */
    @PostMapping(value = "/medias/{id}")
    public ResponseEntity<Void> saveSeason(@PathVariable Long mediaId, @RequestBody Season season) {
        this.seasonService.saveSeason(mediaId, season);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}