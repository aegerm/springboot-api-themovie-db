package br.com.api.movies.dto;

import br.com.api.movies.entities.Media;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class MediaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

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
