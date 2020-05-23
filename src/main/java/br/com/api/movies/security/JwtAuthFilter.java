package br.com.api.movies.security;

import br.com.api.movies.dto.CredentialDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtUtilSecurity jwtUtilSecurity;

    /**
     * attemptAuthentication
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            CredentialDTO credentials = new ObjectMapper().readValue(request.getInputStream(), CredentialDTO.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(credentials.getLogin(), credentials.getPassword(), Arrays.asList());
            Authentication auth = authenticationManager.authenticate(authenticationToken);
            return auth;
        }

        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * successfulAuthentication
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String login = ((UserSecurity) authResult.getPrincipal()).getUsername();
        String token = jwtUtilSecurity.generateToken(login);
        response.addHeader("Authorization", "Bearer " + token);
    }
}