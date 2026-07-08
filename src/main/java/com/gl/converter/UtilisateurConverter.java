package com.gl.converter;

import com.gl.model.Utilisateur;
import com.gl.model.LoginRequest;

public class UtilisateurConverter {

    public static LoginRequest toDTO(Utilisateur utilisateur) {
        LoginRequest u = new LoginRequest();
        u.setUsername(u.getUsername());
        u.setPassword(u.getPassword());
        return u;
    }
}
