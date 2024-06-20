package fullstack.student.services;

import fullstack.student.models.ResponseObject;
import fullstack.student.models.Student;
import fullstack.student.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public ResponseEntity<ResponseObject> getAllStudents() {
        List<Student> listStudents = studentRepository.findAll();
        if (listStudents.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FAILED", "Cannot find list student", "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Successfully", listStudents)
            );
        }
    }

    @Override
    public ResponseEntity<ResponseObject> getStudent(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Successfully", student.get())
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FAILED", "Cannot find student!", "")
            );
        }
    }

    @Override
    public ResponseEntity<ResponseObject> createStudent(Student student) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "created student", studentRepository.save(student))
        );
    }

    @Override
    public ResponseEntity<ResponseObject> deleteStudent(int id) {
        boolean exists = studentRepository.existsById(id);
        if (exists){
            studentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "deleted student", "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("FAILED", "Cannot find student", "")
            );
        }

    }

    @Override
    public ResponseEntity<ResponseObject> updateStudent(int id, Student newStudent) {
        boolean isUpdate = studentRepository.existsById(id);

        Student updatedStudent =studentRepository.findById(id)
                .map(student -> {
                    student.setName(newStudent.getName());
                    student.setEmail(newStudent.getEmail());
                    student.setAddress(newStudent.getAddress());

                    return studentRepository.save(student);
                }).orElseGet(()-> {
                    return studentRepository.save(newStudent);
                });

        if (isUpdate){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Update student successfully", updatedStudent)
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Create student successfully", updatedStudent)
            );
        }
    }

    @Override
    public ResponseEntity<ResponseObject> searchByName(String input) {
       List<Student> listFound = studentRepository.searchStudentByNameContainingIgnoreCase(input);
       if (listFound.isEmpty()){
           return ResponseEntity.status(HttpStatus.OK).body(
                   new ResponseObject("EMPTY", "Cannot find with this keyword", new ArrayList<>())
           );
       } else {
           return ResponseEntity.status(HttpStatus.OK).body(
                   new ResponseObject("OK", "OK", listFound)
           );
       }
    }
}
