package br.com.api.movies.resources;

import br.com.api.movies.dto.MediaDTO;
import br.com.api.movies.entities.Media;
import br.com.api.movies.services.MediaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/medias")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MediaResource {

    private final MediaService mediaService;

    /**
     * findMedias
     *
     * @return
     */
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping
    public ResponseEntity<List<MediaDTO>> findMedias() {
        List<Media> medias = this.mediaService.findAll();
        List<MediaDTO> mediaDTOS = medias.stream().map(value -> new MediaDTO(value)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(mediaDTOS);
    }

    /**
     * findMediaById
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Media> findMediaById(@PathVariable Long id) {
        Media media = this.mediaService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(media);
    }

    /**
     * saveMedia
     *
     * @param dto
     * @return
     */
    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Void> saveMedia(@RequestBody MediaDTO dto) {
        Media media = this.mediaService.fromDTO(dto);
        this.mediaService.saveMedia(media);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}