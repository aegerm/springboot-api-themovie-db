package br.com.api.movies.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CredentialDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String login;
    private String password;
}