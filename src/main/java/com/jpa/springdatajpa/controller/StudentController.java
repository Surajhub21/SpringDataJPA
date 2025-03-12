package com.jpa.springdatajpa.controller;

import com.jpa.springdatajpa.model.Student;
import com.jpa.springdatajpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/c")
    public ResponseEntity<String> createStudent(
            @RequestPart("student") Student student,
            @RequestPart("imageFile") MultipartFile imageFile) {

        try {
            String response = studentService.saveDetails(student, imageFile);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save student: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/gl")
    public List<Student> getAll(){
        return studentService.getAllDetails();
    }

    @GetMapping("/img/{id}")
    public ResponseEntity<byte[]> getImageBYProductId(@PathVariable long id){

        Student student = studentService.getTheDetailsById(id);
        byte[] imageFIle = student.getImageData();

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(student.getImageType()))
                .body(imageFIle);
    }

    @GetMapping("/s")
    public ResponseEntity<Page<Student>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size) {

        Page<Student> users = studentService.getUsers(page, size);
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable long id){
        try {
            studentService.deleteDetails(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
