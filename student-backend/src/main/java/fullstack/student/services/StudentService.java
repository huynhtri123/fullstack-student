package fullstack.student.services;

import fullstack.student.models.dto.ResponseObject;
import fullstack.student.models.dto.StudentRequestDTO;
import fullstack.student.models.entity.Student;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    public ResponseEntity<ResponseObject> getAllStudents();
    public ResponseEntity<ResponseObject> getStudent(int id);
    public ResponseEntity<ResponseObject> createStudent(StudentRequestDTO studentRequestDTO);
    public ResponseEntity<ResponseObject> deleteStudent(int id);
    public ResponseEntity<ResponseObject> updateStudent(int id, StudentRequestDTO studentRequestDTO);
    public ResponseEntity<ResponseObject> searchByName(String input);
}
