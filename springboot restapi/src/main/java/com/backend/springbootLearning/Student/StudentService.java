package com.backend.springbootLearning.Student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

@Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }
    public void addNewStudent(Student student) {
        Optional<Student> studentWithEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentWithEmail.isPresent()){
            throw new IllegalStateException("Email already exist.");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(int studentId) {
        boolean exit = studentRepository.existsById(studentId);
        if(!exit){
            throw new IllegalStateException("Student ID "+studentId+" not found.");
        }
        studentRepository.deleteById(studentId);
    }
    public Optional<Student> updateStudent(int studentId, Student newStudent) {
        Student student = null;
        Optional<Student> optional = studentRepository.findById(studentId);
        if( optional.isPresent()){
            student = optional.get();
            if(newStudent.getName() != null && (newStudent.getName()).length()>0) {
                student.setName(newStudent.getName());
            }
            if(newStudent.getEmail() != null && (newStudent.getEmail()).length()>0) {
                Optional<Student> studentWithEmail = studentRepository.findStudentByEmail(newStudent.getEmail());
                if(studentWithEmail.isPresent()){
                    throw new IllegalStateException("Email already exist.");
                }
                student.setEmail(newStudent.getEmail());
            }
            if(newStudent.getDob()!=null && (newStudent.getDob()).isBefore(LocalDate.now())) student.setDob(newStudent.getDob());
            studentRepository.save(student);
        }
        assert student != null;
        return Optional.of(student);
    }

}
















