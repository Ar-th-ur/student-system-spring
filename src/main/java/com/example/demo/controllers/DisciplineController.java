package com.example.demo.controllers;

import com.example.demo.models.Discipline;
import com.example.demo.repositories.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class DisciplineController {
    @Autowired
    private DisciplineRepository repository;

    @GetMapping("/disciplines")
    public String showDisciplines(Model model) {
        Iterable<Discipline> disciplines = repository.findAll();
        model.addAttribute("disciplines", disciplines);
        return "discipline/disciplines";
    }

    @GetMapping("/add-discipline")
    public String openPageAddDiscipline() {
        return "discipline/add-discipline";
    }

    @PostMapping("/add-discipline")
    public String addDiscipline(@RequestParam String name) {
        Discipline discipline = new Discipline(name);
        repository.save(discipline);
        return "redirect:/disciplines";
    }

    @GetMapping("/edit-discipline/{id}")
    public String openPageEditDiscipline(@PathVariable Long id, Model model) {
        Optional<Discipline> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return "redirect:/discipline";
        }

        model.addAttribute(optional.get());
        return "discipline/edit-discipline";
    }

    @PostMapping("/edit-discipline")
    public String editDiscipline(@RequestParam Long id,
                                 @RequestParam String name) {
        Optional<Discipline> optional = repository.findById(id);
        if (optional.isPresent()) {
            Discipline discipline = optional.get();
            discipline.setName(name);
            repository.save(discipline);
        }
        return "redirect:/disciplines";
    }

    @GetMapping("/delete-discipline/{id}")
    public String deleteDiscipline(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/disciplines";
    }
}
