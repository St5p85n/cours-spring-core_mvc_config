package com.gl.service;

import com.gl.model.Role;
import com.gl.model.RoleType;
import com.gl.model.Utilisateur;
import com.gl.repository.RoleRepository;
import com.gl.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UtilisateurService implements UserDetailsService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);

        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec l'identifiant : " + username);
        }

        List<GrantedAuthority> grantedAuthorities = utilisateur.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
        return new User(
                utilisateur.getUsername(),
                utilisateur.getPassword(),
                grantedAuthorities // Liste d'autorisations/rôles vide pour l'instant
        );
    }


    public Utilisateur addUtilisateur(Utilisateur utilisateur) {
        String password = passwordEncoder.encode(utilisateur.getPassword());
        utilisateur.setPassword(password);
        if(utilisateur.getRoles()==null || utilisateur.getRoles().isEmpty()){
            Role role = roleRepository.findByName(RoleType.ROLE_USER).orElseThrow(
                    () ->  new RuntimeException("Le role ROLE_USER n'existe pas")
            );
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            utilisateur.setRoles(roles);
        }

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
