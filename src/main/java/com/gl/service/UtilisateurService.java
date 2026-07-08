package com.gl.service;

import com.gl.model.Utilisateur;
import com.gl.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurService implements UserDetailsService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);

        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec l'identifiant : " + username);
        }

        return new User(
                utilisateur.getUsername(),
                utilisateur.getPassword(),
                new ArrayList<>() // Liste d'autorisations/rôles vide pour l'instant
        );
    }


    public Utilisateur addUtilisateur(Utilisateur utilisateur) {
        String password = passwordEncoder.encode(utilisateur.getPassword());
        utilisateur.setPassword(password);
        return utilisateurRepository.save(utilisateur);
    }
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }
    public void deleteUtilisateur(Integer id) {
        utilisateurRepository.deleteById(id);
    }
    public Utilisateur updateUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }
}
