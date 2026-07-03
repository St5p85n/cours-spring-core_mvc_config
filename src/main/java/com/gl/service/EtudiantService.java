package com.gl.service;

import com.gl.model.Etudiant;
import com.gl.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {
    @Value("${app.name}")
    private String username;
    private final EtudiantRepository etudiantRepository;
    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }
    public List<Etudiant> findAll() {
        return etudiantRepository.findAll();
    }
    public void save(Etudiant etudiant) {
        etudiantRepository.save(etudiant);
    }
    public void deleteById(Integer id) {
        etudiantRepository.deleteById(id);
    }
    public Optional<Etudiant> findById(Integer id) {
        return etudiantRepository.findById(id);
    }
    public Etudiant update(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }
}
