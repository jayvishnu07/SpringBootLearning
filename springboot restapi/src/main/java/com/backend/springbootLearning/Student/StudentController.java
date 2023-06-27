package com.backend.springbootLearning.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
   private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping("/getAllStudents")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }
    @PostMapping("/addStudent")
    public void addNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }
    @PutMapping("/updateStudent/{studentId}")
    public Optional<Student> updateStudent(@PathVariable("studentId") int studentId,
                                           @RequestBody(required = false) Student newStudent){
        return studentService.updateStudent(studentId,newStudent);
    }
    @DeleteMapping("/deleteStudent/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId){
        studentService.deleteStudent(studentId);
    }
}
