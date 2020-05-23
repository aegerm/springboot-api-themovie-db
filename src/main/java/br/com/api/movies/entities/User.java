package br.com.api.movies.entities;

import br.com.api.movies.entities.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "tb_users")
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String login;

    @JsonIgnore
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tb_profiles")
    private Set<Integer> profiles = new HashSet<>();

    /**
     * getProfiles
     *
     * @return
     */
    public Set<Profile> getProfiles() {
        return profiles.stream().map(p -> Profile.toEnum(p)).collect(Collectors.toSet());
    }

    /**
     * addProfile
     *
     * @param profile
     */
    public void addProfile(Profile profile) {
        profiles.add(profile.getCode());
    }
}