package com.javadev.service;

import com.javadev.model.Role;
import com.javadev.model.Student;
import com.javadev.repository.RoleRepository;
import com.javadev.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsService implements
        org.springframework.security.core.userdetails.UserDetailsService{

    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;
    private Student student;

    @Autowired
    public UserDetailsService(StudentRepository studentRepository, RoleRepository roleRepository){
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user;
        List<GrantedAuthority> authList = new ArrayList<>();

        List<Student> students =  studentRepository.findStudentByMailOrIndexNumber(login, login);
        if(students.size() > 0){
            for(Student s: students){
                student = s;
            }
            Role role = roleRepository.findRoleByStudent(student);
            authList.add(new SimpleGrantedAuthority(role.getRole()));
            String password = passwordEncoder().encode(student.getPassword());
            user = new User(login , password,
                    true,
                    true,
                    true,
                    true,
                    authList);
        }else{
            authList.add(new SimpleGrantedAuthority("ADMIN"));
            String adminPass = passwordEncoder().encode("admin");
            user = new User("admin" , adminPass,
                    true,
                    true,
                    true,
                    true,
                    authList);
        }
        return user;
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}