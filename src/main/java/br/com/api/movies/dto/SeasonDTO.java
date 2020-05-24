package br.com.api.movies.dto;

import br.com.api.movies.entities.Season;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SeasonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String airDate;
    private Integer seasonNumber;

    /**
     * SeasonDTO
     *
     * @param season
     */
    public SeasonDTO(Season season) {
        this.airDate = season.getAirDate();
        this.seasonNumber = season.getSeasonNumber();
    }
}