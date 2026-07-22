package com.gl.controller;

import com.gl.model.Etudiant;
import com.gl.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.hibernate.internal.CoreLogging.logger;

@Controller
@RequestMapping("/etudiantview")
public class MyEtudiantController {
    @Autowired
    private EtudiantService etudiantService;
    @GetMapping
    public String getsEtudiant(Model model) {
        List<Etudiant> etudiants = etudiantService.findAll();
        model.addAttribute("etudiants", etudiants);
        return "etudiants/list";
    }
    @GetMapping("/formulaire")
    public String formulaire(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "etudiants/add";
    }

    @PostMapping
    public String postsEtudiant(@ModelAttribute("etudiant") Etudiant etudiant) {
        IO.println(etudiant);
        etudiantService.save(etudiant);
        return "redirect:/etudiantview";
    }
    @GetMapping("/{id}")
    public String deleteEtudiant(@PathVariable("id") Integer id, Model model) {
        etudiantService.deleteById(id);
        return "redirect:/etudiantview";
    }
    @GetMapping("/update/{id}")
    public String updateEtudiant(@PathVariable("id") Integer id, Model model) {
        Optional<Etudiant> etudiant = etudiantService.findById(id);
        if (etudiant.isPresent()) {
            model.addAttribute("etudiant", etudiant.get());
            return "etudiants/add";
        }
        return "redirect:/etudiantview";

    }
}
