package com.javadev.controller;

import com.javadev.model.Attendance;
import com.javadev.model.Lecture;
import com.javadev.model.Student;
import com.javadev.repository.LectureRepository;
import com.javadev.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;
    private Attendance attendance;

    @Autowired
    public AdminController(StudentRepository studentRepository, LectureRepository lectureRepository){
        this.studentRepository = studentRepository;
        this.lectureRepository = lectureRepository;
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

    @GetMapping("/lectures")
    public ModelAndView lecturesPage(@ModelAttribute Lecture lecture){

        ModelAndView modelAndView = new ModelAndView();

        List<Lecture> lectureList = (List<Lecture>) lectureRepository.findAll();
        Map<String, Object> params = new HashMap<>();
        params.put("lectureList", lectureList);

        modelAndView.addAllObjects(params);
        modelAndView.setViewName("admin_html/admin_lectures");

        return modelAndView;
    }

    @PostMapping("/lectures/add")
    public ModelAndView addStudent(@ModelAttribute Lecture lecture){
        List<Student> studentList = (List<Student>) studentRepository.findAll();
        if(studentList.size() != 0){
            for(Student s: studentList){
                attendance = new Attendance();
                attendance.setLecture(lecture);
                attendance.setStudent(s);
                attendance.setAttendanceStatus(false);
                lecture.addAttendance(attendance);
            }
        }
        lectureRepository.save(lecture);
        return new ModelAndView("redirect:/admin/lectures");
    }
    @PutMapping("/lectures/{id}/update")
    public ModelAndView updateStudent(@ModelAttribute Lecture lecture){
        lecture.setId(lecture.getId());
        lectureRepository.save(lecture);
        return new ModelAndView("redirect:/admin/lectures");
    }

    @DeleteMapping("/lectures/{id}/delete")
    public ModelAndView deleteStudent(@ModelAttribute Lecture lecture){
        lectureRepository.deleteById(lecture.getId());
        return new ModelAndView("redirect:/admin/lectures");
    }

    @PostMapping("/test")
    public ModelAndView test(@ModelAttribute Student student,@ModelAttribute Lecture lecture){
        return new ModelAndView("redirect:/admin/students");
    }
}