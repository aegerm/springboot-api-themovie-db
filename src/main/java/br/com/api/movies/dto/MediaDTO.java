package br.com.api.movies.dto;

import br.com.api.movies.entities.Media;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MediaDTO {

    private Long id;
    private String name;
    private String originalName;
    private String character;

    /**
     * MediaDTO
     *
     * @param media
     */
    public MediaDTO(Media media) {
        this.id = media.getId();
        this.name = media.getName();
        this.originalName = media.getOriginalName();
        this.character = media.getCharacter();
    }
}
