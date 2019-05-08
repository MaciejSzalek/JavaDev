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

@Controller
public class AdminController {

    private final StudentRepository studentRepository;

    @Autowired
    public AdminController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @RequestMapping("/admin/home")
    public ModelAndView homePage(){
        return new ModelAndView("admin_html/admin_home");
    }

    @RequestMapping("/logout")
    public ModelAndView logoutPage(){
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/admin/add")
    public ModelAndView addPage(){

        Student student = new Student();
        student.setFirstName("Jan");
        student.setLastName("Kowalski");
        student.setMail("jankowalski@mail.com");
        student.setStudyYear("2");
        student.setStudyField("mechanika");
        student.setIndexNumber("1234567");
        student.setPassword("StuPassword");

        studentRepository.save(student);

        return new ModelAndView("redirect:/admin/students");
    }

    @RequestMapping("/admin/students")
    public ModelAndView studentsPage(@ModelAttribute Student student){

        ModelAndView modelAndView = new ModelAndView();

        List<Student> studentList = (List<Student>) studentRepository.findAll();
        Map<String, Object> params = new HashMap<>();
        params.put("studentList", studentList);

        modelAndView.addAllObjects(params);
        modelAndView.setViewName("admin_html/admin_students");

        return modelAndView;
    }

    @RequestMapping("/admin/students/add")
    public ModelAndView addStudent(@ModelAttribute Student student){
        studentRepository.save(student);
        return new ModelAndView("redirect:/admin/students");
    }
    @RequestMapping("/admin/students/update/{id}")
    public ModelAndView updateStudent(@ModelAttribute Student student){
        student.setId(student.getId());
        studentRepository.save(student);
        return new ModelAndView("redirect:/admin/students");
    }

    @RequestMapping("/admin/students/delete/{id}")
    public ModelAndView deleteStudent(@ModelAttribute Student student){
        studentRepository.deleteById(student.getId());
        return new ModelAndView("redirect:/admin/students");
    }

}