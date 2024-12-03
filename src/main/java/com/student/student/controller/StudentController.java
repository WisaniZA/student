package com.student.student.controller;

import com.student.student.entity.LoginRequest;
import com.student.student.entity.PasswordUpdateRequest;
import com.student.student.entity.Student;
import com.student.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:54356")// Replace with your Angular app's URL
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.register(student));
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        return ResponseEntity.ok(studentService.login(loginRequest));
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            return ResponseEntity.ok(studentService.login(loginRequest));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed");
        }
    }


    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(studentService.getProfile(token));
    }

    @PutMapping("/password")
    public ResponseEntity<?> updatePassword(@RequestBody PasswordUpdateRequest request) {
        return ResponseEntity.ok(studentService.updatePassword(request));
    }
}

