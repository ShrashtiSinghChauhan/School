package com.school.controller;

import com.school.payload.StudentDto;
import com.school.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService= studentService;
    }
    //http://localhost:8080/api/student
    @PostMapping
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto studentDto){
        StudentDto dto= studentService.saveStudent(studentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/student
    @GetMapping
    public List<StudentDto> listAllStudents(){
        List<StudentDto> StuDtos= studentService.listAllStudents();
        return StuDtos;
    }
    //http://localhost:8080/api/id
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentByEmail(@PathVariable("id") long id){
        StudentDto dto= studentService.getStudentById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    //http://localhost:8080/api/id
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto,
                                                    @PathVariable("id") long id){
        StudentDto updatedStudent= studentService.updateStudent(studentDto,id);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }
    //http://localhost:8080/api/id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id){
        studentService.delete(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }
}
