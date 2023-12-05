package com.school.service;

import com.school.payload.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto saveStudent(StudentDto studentDto);

    List<StudentDto> listAllStudents();

    StudentDto getStudentById(long id);

    StudentDto updateStudent(StudentDto studentDto, long id);

    void delete(long id);
}
