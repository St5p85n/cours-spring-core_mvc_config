package com.gl.controller;

import com.gl.model.Etudiant;
import com.gl.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiant")
public class EtudiantController {
    @Autowired
    private EtudiantService etudiantService;
    @GetMapping
    public List<Etudiant> findAll() {
      return  etudiantService.findAll();
    }

    @PostMapping
    public void save(@RequestBody Etudiant etudiant) {
        etudiantService.save(etudiant);
    }
    @GetMapping("/exo")
    public String getNom(){
        return "Diop alpha";
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
