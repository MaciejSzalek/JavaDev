package com.javadev.controller;

import com.javadev.model.Attendance;
import com.javadev.model.Lecture;
import com.javadev.model.Student;
import com.javadev.repository.AttendanceRepository;
import com.javadev.repository.LectureRepository;
import com.javadev.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final StudentRepository studentRepository;
    private final LectureRepository lectureRepository;
    private final AttendanceRepository attendanceRepository;
    private Attendance attendance;

    @Autowired
    public AdminController(StudentRepository studentRepository,
                           LectureRepository lectureRepository,
                           AttendanceRepository attendanceRepository){
        this.studentRepository = studentRepository;
        this.lectureRepository = lectureRepository;
        this.attendanceRepository = attendanceRepository;
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
        studentList.sort(Comparator.comparing(Student::getLastName));
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
        lectureList.sort(Comparator.comparing(Lecture::getDate));
        Map<String, Object> lectureParams = new HashMap<>();
        lectureParams.put("lectureList", lectureList);

        modelAndView.addAllObjects(lectureParams);
        modelAndView.setViewName("admin_html/admin_lectures");

        return modelAndView;
    }

    @PostMapping("/lectures/add")
    public ModelAndView addLecture(@ModelAttribute Lecture lecture){
        lectureRepository.save(lecture);
        return new ModelAndView("redirect:/admin/lectures");
    }
    @PutMapping("/lectures/{id}/update")
    public ModelAndView updateLecture(@ModelAttribute Lecture lecture){
        lecture.setId(lecture.getId());
        lectureRepository.save(lecture);
        return new ModelAndView("redirect:/admin/lectures");
    }

    @DeleteMapping("/lectures/{id}/delete")
    public ModelAndView deleteLecture(@ModelAttribute Lecture lecture){
        lectureRepository.deleteById(lecture.getId());
        return new ModelAndView("redirect:/admin/lectures");
    }

    @GetMapping("/attendances")
    public ModelAndView attendancePage(@ModelAttribute Lecture lecture,
                                       @ModelAttribute Student student,
                                       @ModelAttribute Attendance attendance){

        ModelAndView modelAndView = new ModelAndView();

        List<Lecture> lectureList = (List<Lecture>) lectureRepository.findAll();
        lectureList.sort(Comparator.comparing(Lecture::getDate));
        Map<String, Object> lectureParams = new HashMap<>();
        lectureParams.put("lectureList", lectureList);

        List<Student> studentList = (List<Student>) studentRepository.findAll();
        studentList.sort(Comparator.comparing(Student::getLastName));
        Map<String, Object> studentParams = new HashMap<>();
        studentParams.put("studentList", studentList);

        List<Attendance> attendanceList = (List<Attendance>) attendanceRepository.findAll();
        Map<String, Object> attendanceParams = new HashMap<>();
        attendanceParams.put("attendanceList", attendanceList);

        modelAndView.addAllObjects(lectureParams);
        modelAndView.addAllObjects(studentParams);
        modelAndView.addAllObjects(attendanceParams);
        modelAndView.setViewName("admin_html/admin_attendances");

        return modelAndView;
    }

    @PutMapping("/attendances/{lectureId}/{studentId}/present")
    public ModelAndView updateAttendancePresent (@PathVariable("lectureId")  Long lectureId,
                                                 @PathVariable("studentId") Long studentId){

        Lecture lecture = new Lecture();
        Student student = new Student();

        List<Attendance> attendanceList = attendanceRepository.findAttendanceByLectureAndStudent(lectureId,studentId);
        Optional<Lecture> lectureOpt = lectureRepository.findById(lectureId);
        Optional<Student> studentOpt = studentRepository.findById(studentId);

        if(lectureOpt.isPresent()){
            lecture = lectureOpt.get();
        }
        if(studentOpt.isPresent()) {
            student = studentOpt.get();
        }

        if(attendanceList.size() == 0){
            Student stu = new Student();
            attendance = new Attendance();
            attendance.setLecture(lecture);
            attendance.setStudent(student);
            stu.addAttendance(attendance);
        }


        return new ModelAndView("redirect:/admin/attendances");
    }

    @PutMapping("/attendances/{lectureId}/{studentId}/absent")
    public ModelAndView updateAttendanceAbsent (@PathVariable("lectureId")  Long lectureId,
                                          @PathVariable("studentId") Long studentId){

        Lecture lecture = new Lecture();
        Student student = new Student();

        List<Attendance> attendanceList = attendanceRepository.findAttendanceByLectureAndStudent(lectureId,studentId);
        Optional<Lecture> lectureOpt = lectureRepository.findById(lectureId);
        Optional <Student> studentOpt = studentRepository.findById(studentId);

        if(lectureOpt.isPresent()){
            lecture = lectureOpt.get();
        }
        if(studentOpt.isPresent()) {
            student = studentOpt.get();
        }

        for(Attendance a: attendanceList) {
            attendanceRepository.deleteById(a.getId());
        }

        return new ModelAndView("redirect:/admin/attendance");
    }


    @RequestMapping("/test")
    public String test(){
        String existsStr = "";
        StringBuilder builder = new StringBuilder();
        List <Attendance> attendanceList = attendanceRepository.findAttendanceByLectureAndStudent(2L,9L);

        for(Attendance a : attendanceList)
        if(attendanceRepository.existsById(a.getId())){
            existsStr = " exists: true";
        }else{
            existsStr = " exists: false";
        }

        for(Attendance a : attendanceList){
            builder.append("\nId: " +  a.getId() + "\nstudent: " + a.getStudent().getLastName());
        }
        return builder.toString() + existsStr;
    }
}