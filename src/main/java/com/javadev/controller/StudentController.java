package com.javadev.controller;

import com.javadev.model.Attendance;
import com.javadev.model.Lecture;
import com.javadev.model.Student;
import com.javadev.repository.AttendanceRepository;
import com.javadev.repository.LectureRepository;
import com.javadev.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;
    private final AttendanceRepository attendanceRepository;
    private final LectureRepository lectureRepository;
    private Student studentDB;

    @Autowired
    public StudentController(StudentRepository studentRepository,
                             AttendanceRepository attendanceRepository,
                             LectureRepository lectureRepository){
        this.studentRepository = studentRepository;
        this.attendanceRepository = attendanceRepository;
        this.lectureRepository = lectureRepository;
    }


    @GetMapping("/home")
    public ModelAndView homePage(){
        ModelAndView mav = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String user = auth.getName();
        List<Student> students =
                studentRepository.findStudentByMailOrIndexNumber(user, user);
        if(students.size() > 0){
            for(Student s: students){
                studentDB = s;
            }
        }

        String welcome = "Welcome " + studentDB.getFirstName() + " " + studentDB.getLastName()
                + " in JavaDev Console";
        String userName = studentDB.getFirstName() + " " + studentDB.getLastName();

        mav.addObject("userName", userName);
        mav.addObject("welcome", welcome);
        mav.setViewName("student_html/student_home");
        return mav;
    }

    @GetMapping("/details")
    public ModelAndView details(){
        ModelAndView mav = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String user = auth.getName();
        List<Student> students =
                studentRepository.findStudentByMailOrIndexNumber(user, user);
        if(students.size() > 0){
            for(Student s: students){
                studentDB = s;
            }
        }
        String userName = studentDB.getFirstName() + " " + studentDB.getLastName();

        mav.addObject("userName", userName);
        mav.addObject("student", studentDB);
        mav.setViewName("student_html/student_details");
        return mav;
    }

    @GetMapping("/attendances")
    public ModelAndView attendance(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String user = auth.getName();
        List<Student> students =
                studentRepository.findStudentByMailOrIndexNumber(user, user);
        if(students.size() > 0){
            for(Student s: students){
                studentDB = s;
            }
        }
        String userName = studentDB.getFirstName() + " " + studentDB.getLastName();
        String userId = studentDB.getId().toString();

        List<Lecture> lectureList = (List<Lecture>) lectureRepository.findAll();
        lectureList.sort(Comparator.comparing(Lecture::getDate));
        Map<String, Object> lectureParams = new HashMap<>();
        lectureParams.put("lectureList", lectureList);

        List<Attendance> attendanceList = attendanceRepository.findByStudentId(studentDB.getId());
        Map<String, Object> attendanceParams = new HashMap<>();
        attendanceParams.put("attendanceList", attendanceList);

        ModelAndView mav = new ModelAndView();
        mav.addAllObjects(attendanceParams);
        mav.addAllObjects(lectureParams);
        mav.addObject("userName", userName);
        mav.addObject("userId", userId);
        mav.setViewName("student_html/student_attendances");
        return mav;
    }

    @PutMapping("/attendances/{lectureId}/update")
    public ModelAndView updateAttendance(@PathVariable("lectureId") Long lectureId){

        Long id = studentDB.getId();
        List<Attendance> attendanceList = attendanceRepository.findAttendanceByLectureAndStudent(lectureId, id);
        if(attendanceList.size() == 0){
            Attendance attendance = new Attendance();
            attendance.setLectureId(lectureId);
            attendance.setStudentId(id);
            attendanceRepository.save(attendance);
        }else{
            for(Attendance a: attendanceList) {
                attendanceRepository.deleteById(a.getId());
            }
        }
        return new ModelAndView("redirect:/student/attendances");
    }

    @PutMapping("/edit/password")
    public ModelAndView editStudentPassword(@ModelAttribute Student student){
        ModelAndView mav = new ModelAndView();
        studentRepository.save(student);
        mav.setViewName("redirect:/student/details");
        return mav;
    }
}