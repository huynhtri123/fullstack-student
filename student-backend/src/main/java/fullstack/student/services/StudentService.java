package fullstack.student.services;

import fullstack.student.models.ResponseObject;
import fullstack.student.models.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    public ResponseEntity<ResponseObject> getAllStudents();
    public ResponseEntity<ResponseObject> getStudent(int id);
    public ResponseEntity<ResponseObject> createStudent(Student student);
    public ResponseEntity<ResponseObject> deleteStudent(int id);
    public ResponseEntity<ResponseObject> updateStudent(int id, Student newStudent);
    public ResponseEntity<ResponseObject> searchByName(String input);
}
