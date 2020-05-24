package br.com.api.movies.dto;

import br.com.api.movies.entities.Credit;
import br.com.api.movies.entities.Media;
import br.com.api.movies.entities.Person;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CreditDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String creditType;
    private String department;
    private String job;
    private String mediaType;
    private Media media;
    private Person person;

    /**
     * CreditDTO
     *
     * @param credit
     */
    public CreditDTO(Credit credit) {
        this.id = credit.getId();
        this.creditType = credit.getCreditType();
        this.department = credit.getDepartment();
        this.job = credit.getJob();
        this.mediaType = credit.getMediaType();
        this.media = credit.getMedia();
        this.person = credit.getPerson();
    }
}