package com.school.service.impl;

import com.school.entity.Student;
import com.school.exception.ResourceNotFoundException;
import com.school.payload.StudentDto;
import com.school.repository.StudentRepository;
import com.school.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepo;
    private ModelMapper modelMapper;
    public StudentServiceImpl(StudentRepository studentRepo, ModelMapper modelMapper){
        this.studentRepo=studentRepo;
        this.modelMapper= modelMapper;
    }

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        Student student= new Student();
        //dto to entity
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setPhoneNumber(studentDto.getPhoneNumber());
        student.setAddress(studentDto.getAddress());
        Student newStudent= studentRepo.save(student);

        //entity to dto
        StudentDto dto= new StudentDto();
        dto.setId(newStudent.getId());
        dto.setName(newStudent.getName());
        dto.setEmail(newStudent.getEmail());
        dto.setPhoneNumber(newStudent.getPhoneNumber());
        dto.setAddress(newStudent.getAddress());
        return dto;
    }

    @Override
    public List<StudentDto> listAllStudents() {
        List<Student> students= studentRepo.findAll();
        List<StudentDto> stuDto= students.stream().map(student -> mapToDto(student)).collect(Collectors.toList());
        return stuDto;
    }

    @Override
    public StudentDto getStudentById(long id) {
        Student student= studentRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Student not found with id:" +id)
        );
        return mapToDto(student);
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto, long id) {
        Student student= studentRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Student not found with id:" +id)
        );
        Student newStudent = mapToEntity(studentDto);
        newStudent.setId(id);
        Student updatedStudent= studentRepo.save(newStudent);
        StudentDto dto= mapToDto(updatedStudent);
        return dto;
    }

    @Override
    public void delete(long id) {
       Student student= studentRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Student not found with id:" +id)
        );
        studentRepo.deleteById(id);
    }

    StudentDto mapToDto(Student student) {
        return modelMapper.map(student, StudentDto.class);
    }
    Student mapToEntity(StudentDto studentDto) {
        return modelMapper.map(studentDto, Student.class);
    }
}
