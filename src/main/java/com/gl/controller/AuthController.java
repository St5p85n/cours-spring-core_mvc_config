package com.gl.controller;

import com.gl.model.LoginRequest;
import com.gl.model.Utilisateur;
import com.gl.service.UtilisateurService;
import com.gl.utilitaire.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        return jwtUtils.generateJwtToken(authentication.getName());
    }

    @PostMapping("/account")
    public ResponseEntity<Utilisateur> register(@RequestBody Utilisateur user) {
        Utilisateur u =  utilisateurService.addUtilisateur(user);
        return ResponseEntity.ok().body(u);
    }
}
