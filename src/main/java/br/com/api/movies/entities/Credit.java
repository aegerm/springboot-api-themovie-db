package br.com.api.movies.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_credits")
@NoArgsConstructor
public class Credit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String creditType;
    private String department;
    private String job;
    private String mediaType;

    @OneToOne
    @JoinColumn(name = "media_id")
    private Media media;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
}