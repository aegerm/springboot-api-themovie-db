package br.com.api.movies.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Profile {

    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER");

    private int code;
    private String role;

    /**
     * toEnum
     *
     * @param code
     * @return
     */
    public static Profile toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (Profile p : Profile.values()) {
            if (code.equals(p.getCode())) {
                return  p;
            }
        }

        throw new IllegalArgumentException("Code invalid: " + code);
    }
}