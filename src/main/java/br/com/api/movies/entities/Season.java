package br.com.api.movies.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_seasons")
@NoArgsConstructor
public class Season implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date airDate;
    private Integer seasonNumber;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;
}