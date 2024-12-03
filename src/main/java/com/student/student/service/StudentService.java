package com.student.student.service;

import com.student.student.entity.LoginRequest;
import com.student.student.entity.PasswordUpdateRequest;
import com.student.student.entity.Student;
import com.student.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Student register(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

//    public String login(LoginRequest loginRequest) {
//        Student student = studentRepository.findByEmail(loginRequest.getUsername())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        if (!passwordEncoder.matches(loginRequest.getPassword(), student.getPassword())) {
//            throw new RuntimeException("Invalid credentials");
//        }
//        return "JWT_TOKEN"; // Implement JWT
//    }

    public String login(LoginRequest loginRequest) {
        Student student = studentRepository.findByEmail(loginRequest.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), student.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return "JWT_TOKEN"; // Replace with actual JWT logic
    }



    public Student getProfile(String token) {
        // Decode JWT and fetch user details
        return new Student();
    }

    public String updatePassword(PasswordUpdateRequest request) {
        Student student = studentRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        student.setPassword(passwordEncoder.encode(request.getNewPassword()));
        studentRepository.save(student);
        return "Password updated successfully";
    }
}

