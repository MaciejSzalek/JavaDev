package com.javadev.controller;

import com.javadev.model.Student;
import com.javadev.repository.AttendanceRepository;
import com.javadev.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;
    private Student student;

    @Autowired
    public StudentController(StudentRepository studentRepository,
                             AttendanceRepository attendanceRepository){
        this.studentRepository = studentRepository;
        this.attendanceRepository = attendanceRepository;
    }


    @GetMapping("/home")
    public ModelAndView homePage(){
        ModelAndView mav = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String user = auth.getName();
        Optional<Student> studentOptional =
                studentRepository.findStudentByMailOrIndexNumber(user, user);
        if(studentOptional.isPresent()){
            student = studentOptional.get();
        }

        String welcome = "Welcome " + student.getFirstName() + " " + student.getLastName()
                + " in JavaDev Console";
        String userName = student.getFirstName() + " " + student.getLastName();

        mav.addObject("userName", userName);
        mav.addObject("welcome", welcome);
        mav.setViewName("student_html/student_home");
        return mav;
    }

    @GetMapping("/details")
    public ModelAndView details(){
        ModelAndView mav = new ModelAndView();
        String userName = student.getFirstName() + " " + student.getLastName();

        mav.addObject("userName", userName);
        mav.addObject("student", student);
        mav.setViewName("student_html/student_details");
        return mav;
    }

    @GetMapping("/attendances")
    public ModelAndView attendance(){
        ModelAndView mav = new ModelAndView();
        String userName = student.getFirstName() + " " + student.getLastName();

        mav.addObject("userName", userName);
        mav.setViewName("student_html/student_attendances");
        return mav;
    }

    @PutMapping("/edit/password/{newPassword}")
    public ModelAndView editStudentPassword(@ModelAttribute Student student,
                                            @PathVariable("newPassword") String newPassword){
        ModelAndView mav = new ModelAndView();
        student.setId(student.getId());
        student.setPassword(student.getPassword());
        studentRepository.save(student);
        mav.setViewName("redirect:/student/details");
        //String test = "/id=" + student.getId()+"password=" + newPassword;
        //mav.setViewName(test);
        return mav;
    }

}