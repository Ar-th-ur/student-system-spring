package com.example.demo.controllers;

import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.Optional;

@Controller
public class StudentController {
    @Autowired
    private StudentRepository repository;

    @GetMapping("/add-student")
    public String openPageAddStudent() {
        return "students/add-student";
    }

    @PostMapping("/add-student")
    public String addStudent(@RequestParam String lastname,
                             @RequestParam String name,
                             @RequestParam String streamGroup,
                             @RequestParam Date dateOfAdmission) {
        Student student = new Student(lastname, name, streamGroup, dateOfAdmission);
        repository.save(student);
        return "redirect:/students";
    }

    @GetMapping("/students")
    public String showStudents(Model model) {
        Iterable<Student> students = repository.findAll();
        model.addAttribute("students", students);
        return "students/students";
    }

    @GetMapping("/edit-student/{id}")
    public String openPageEditStudent(@PathVariable Long id, Model model) {
        Optional<Student> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return "redirect:/students";
        }

        model.addAttribute("student", optional.get());
        return "students/edit-student";
    }

    @PostMapping("/edit-student")
    public String editStudent(@RequestParam Long id,
                              @RequestParam String lastname,
                              @RequestParam String name,
                              @RequestParam String streamGroup,
                              @RequestParam Date dateOfAdmission) {
        Optional<Student> optional = repository.findById(id);
        if (optional.isPresent()) {
            Student student = optional.get();
            student.setLastname(lastname);
            student.setName(name);
            student.setStreamGroup(streamGroup);
            student.setDateOfAdmission(dateOfAdmission);
            repository.save(student);
        }
        return "redirect:/students";
    }

    @GetMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        return "redirect:/students";
    }
}
