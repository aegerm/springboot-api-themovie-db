package br.com.api.movies.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private JwtUtilSecurity jwtUtilSecurity;
    private UserDetailsService userDetailsService;

    /**
     * JwtAuthorizationFilter
     *
     * @param authenticationManager
     * @param jwtUtilSecurity
     * @param userDetailsService
     */
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtUtilSecurity jwtUtilSecurity, UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtUtilSecurity = jwtUtilSecurity;
        this.userDetailsService = userDetailsService;
    }

    /**
     * doFilterInternal
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            UsernamePasswordAuthenticationToken token = getAuth(header.substring(7));

            if (token != null) {
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }

        chain.doFilter(request, response);
    }

    /**
     * getAuth
     *
     * @param value
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuth(String value) {
        if (jwtUtilSecurity.isValidToken(value)) {
            String userLogin = jwtUtilSecurity.getUserLogin(value);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userLogin);
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }
        return null;
    }
}