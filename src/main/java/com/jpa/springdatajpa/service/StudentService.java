package com.jpa.springdatajpa.service;

import com.jpa.springdatajpa.model.Student;
import com.jpa.springdatajpa.repository.StudentRepository;
import com.jpa.springdatajpa.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllDetails(){
        return studentRepository.findAll();
    }

    public String saveDetails(Student student, MultipartFile imageFile) throws IOException {

        // Save Image Data
        student.setImageName(imageFile.getOriginalFilename());
        student.setImageType(imageFile.getContentType());
        student.setImageData(imageFile.getBytes());
        // Save to Database
        Student save = studentRepository.save(student);

        if(save != null){
            return "FIle Uploaded Successfully" + imageFile.getOriginalFilename();
        }

        return "Error :- Couldn't upload the file";

    }

    public Student getTheDetailsById(long id){
        return studentRepository.getReferenceById(id);
    }

    public void deleteDetails(long id){
       studentRepository.deleteById(id);
    }

    public Student updateDetails(Student student){
        return studentRepository.save(student);
    }

    public Page<Student> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return studentRepository.findAll(pageable);
    }
}
