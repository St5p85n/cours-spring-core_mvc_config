package com.gl.controller;

import com.gl.exception.ResourceNotFound;
import com.gl.model.Etudiant;
import com.gl.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/etudiant")
public class EtudiantController {
    @Autowired
    private EtudiantService etudiantService;
    @GetMapping
    public List<Etudiant> findAll() {
      return  etudiantService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Etudiant etudiant) {
        etudiantService.save(etudiant);
        return ResponseEntity.ok().build();

    }
    @GetMapping("/exo")
    public String getNom(){
        return "Diop alpha";
    }

    @GetMapping("/{id}")
    public Etudiant findById(@PathVariable("id") int id){
        Optional<Etudiant> e = etudiantService.findById(id);
        if(!e.isPresent()){
            throw new ResourceNotFound("L'etudiant avec l'id "+id+" n'existe pas");
        }
        return e.get();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        etudiantService.deleteById(id);
    }

    @PutMapping
    public void update(@RequestBody Etudiant etudiant) {
        etudiantService.update(etudiant);
    }
}
