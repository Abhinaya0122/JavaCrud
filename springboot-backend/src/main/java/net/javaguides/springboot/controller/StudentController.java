package net.javaguides.springboot.controller;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Student;
import net.javaguides.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/students1")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    // build create employee REST API
   @PostMapping("/students1")
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // build get employee by id REST API
    @GetMapping("{rollno}")
    public ResponseEntity<Student> getStudentById(@PathVariable  long rollno){
        Student student = studentRepository.findById(rollno)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with rollno:" + rollno));
        return ResponseEntity.ok(student);
    }

    // build update employee REST API
    @PutMapping("{rollno}")
    public ResponseEntity<Student> updateStudent(@PathVariable long rollno,@RequestBody Student studentDetails) {
        Student updateStudent = studentRepository.findById(rollno)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with rollno: " + rollno));

        updateStudent.setFirstName(studentDetails.getFirstName());
        updateStudent.setLastName(studentDetails.getLastName());
        updateStudent.setEmailId(studentDetails.getEmailId());

        studentRepository.save(updateStudent);

        return ResponseEntity.ok(updateStudent);
    }

    // build delete employee REST API
    @DeleteMapping("{rollno}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable long rollno){

        Student student = studentRepository.findById(rollno)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id: " + rollno));

        studentRepository.delete(student);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}