package com.gl.controller;

import com.gl.dto.LoginDetails;
import com.gl.model.LoginRequest;
import com.gl.model.Role;
import com.gl.model.Utilisateur;
import com.gl.service.UtilisateurService;
import com.gl.utilitaire.JwtUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        String jwt = jwtUtils.generateJwtToken(authentication);
        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        LoginDetails  loginDetails = new LoginDetails(loginRequest.getUsername(), jwt,roles);
        return  ResponseEntity.ok(loginDetails);

    }

    @PostMapping("/account")
    public ResponseEntity<Utilisateur> register(@RequestBody Utilisateur user) {
        Utilisateur u =  utilisateurService.addUtilisateur(user);
        return ResponseEntity.ok().body(u);
    }


}
