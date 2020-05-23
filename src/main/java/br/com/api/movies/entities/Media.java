package br.com.api.movies.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "tb_medias")
@NoArgsConstructor
public class Media implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String originalName;
    private String character;

    @JsonIgnore
    @OneToOne(mappedBy = "media")
    private Credit credit;

    @OneToMany(mappedBy = "media")
    private List<Season> seasons;
}