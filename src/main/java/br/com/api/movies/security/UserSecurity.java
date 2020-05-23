package br.com.api.movies.security;

import br.com.api.movies.entities.enums.Profile;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSecurity implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Getter
    private Long id;
    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * UserSecurity
     *
     */
    public UserSecurity() {
    }

    /**
     * UserSecurity
     *
     * @param id
     * @param login
     * @param password
     * @param profiles
     */
    public UserSecurity(Long id, String login, String password, Set<Profile> profiles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.authorities = profiles.stream().map(p -> new SimpleGrantedAuthority(p.getRole())).collect(Collectors.toList());
    }

    /**
     * getAuthorities
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * getPassword
     *
     * @return
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * getUsername
     *
     * @return
     */
    @Override
    public String getUsername() {
        return login;
    }

    /**
     * isAccountNonExpired
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * isAccountNonLocked
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * isCredentialsNonExpired
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * isEnabled
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}