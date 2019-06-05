package com.javadev.controller;

import com.javadev.model.Role;
import com.javadev.model.Student;
import com.javadev.repository.RoleRepository;
import com.javadev.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class SecurityController {

    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public SecurityController(StudentRepository studentRepository,
                              RoleRepository roleRepository){
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping({"/","/login"})
    public ModelAndView login(){
        return new ModelAndView("security_html/login");
    }

    @GetMapping("/login/result")
    public ModelAndView loginPage(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        ModelAndView mav = new ModelAndView();

        for(GrantedAuthority grantedAuthority: user.getAuthorities()){
            if(grantedAuthority.toString().equals("STUDENT")){
                mav.setViewName("redirect:/student/home");
            }else if(grantedAuthority.toString().equals("ADMIN")){
                mav.setViewName("redirect:/admin/home");
            }
        }
        return mav;
    }
    @GetMapping("/login?logout")
    public ModelAndView logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/register")
    public ModelAndView registerPage(@ModelAttribute Student student){
        return new ModelAndView("security_html/register");
    }

    @PostMapping("/register/confirm")
    public ModelAndView registerConfirm(@ModelAttribute Student student){
        ModelAndView mav = new ModelAndView();
        List<Student> students = studentRepository.findStudentByMailOrIndexNumber(student.getMail(),
                student.getIndexNumber());
        if(students.size() > 0){
            String message = "Account exists !!! Check your e-mail or index number ";
            mav.addObject("errorMessage", message);
            mav.setViewName("security_html/register");
        }else{
            Role role = new Role();
            role.setRole("STUDENT");
            role.setStudent(student);
            student.addRole(role);
            studentRepository.save(student);
            mav.setViewName("redirect:/login");
        }
        return mav;
    }
}