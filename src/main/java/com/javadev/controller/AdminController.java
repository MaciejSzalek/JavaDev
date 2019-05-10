package com.javadev.controller;

import com.javadev.model.Student;
import com.javadev.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final StudentRepository studentRepository;

    @Autowired
    public AdminController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @RequestMapping("/logout")
    public ModelAndView logoutPage(){
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/home")
    public ModelAndView homePage(){
        return new ModelAndView("admin_html/admin_home");
    }

    @GetMapping("/students")
    public ModelAndView studentsPage(@ModelAttribute Student student){

        ModelAndView modelAndView = new ModelAndView();

        List<Student> studentList = (List<Student>) studentRepository.findAll();
        Map<String, Object> params = new HashMap<>();
        params.put("studentList", studentList);

        modelAndView.addAllObjects(params);
        modelAndView.setViewName("admin_html/admin_students");

        return modelAndView;
    }

    @PostMapping("/students/add")
    public ModelAndView addStudent(@ModelAttribute Student student){
        studentRepository.save(student);
        return new ModelAndView("redirect:/admin/students");
    }
    @PutMapping("/students/{id}/update")
    public ModelAndView updateStudent(@ModelAttribute Student student){
        student.setId(student.getId());
        studentRepository.save(student);
        return new ModelAndView("redirect:/admin/students");
    }

    @DeleteMapping("/students/{id}/delete")
    public ModelAndView deleteStudent(@ModelAttribute Student student){
        studentRepository.deleteById(student.getId());
        return new ModelAndView("redirect:/admin/students");
    }
}