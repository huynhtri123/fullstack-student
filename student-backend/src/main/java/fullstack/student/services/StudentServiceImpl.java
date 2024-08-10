package fullstack.student.services;

import fullstack.student.models.dto.ResponseObject;
import fullstack.student.models.dto.StudentRequestDTO;
import fullstack.student.models.entity.Student;
import fullstack.student.repositories.StudentRepository;
import fullstack.student.utils.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public ResponseEntity<ResponseObject> getAllStudents() {
        List<Student> listStudents = studentRepository.findAll();
        if (listStudents.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Cannot find list student", HttpStatus.NOT_FOUND.value(), "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject( "Successfully", HttpStatus.OK.value(), listStudents)
            );
        }
    }

    @Override
    public ResponseEntity<ResponseObject> getStudent(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject( "Successfully", HttpStatus.OK.value(), student.get())
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject( "Cannot find student!", HttpStatus.NOT_FOUND.value(), "")
            );
        }
    }

    @Override
    public ResponseEntity<ResponseObject> createStudent(StudentRequestDTO studentRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("created student",
                        HttpStatus.OK.value(),
                        studentRepository.save(studentMapper.convertToEntity(studentRequestDTO)))
        );
    }

    @Override
    public ResponseEntity<ResponseObject> deleteStudent(int id) {
        boolean exists = studentRepository.existsById(id);
        if (exists){
            studentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject( "deleted student", HttpStatus.OK.value(), "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject( "Cannot find student", HttpStatus.OK.value(), "")
            );
        }

    }

    @Override
    public ResponseEntity<ResponseObject> updateStudent(int id, StudentRequestDTO studentRequestDTO) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Student not found", HttpStatus.NOT_FOUND.value(), null)
            );
        }
        student.setName(studentRequestDTO.getName());
        student.setEmail(studentRequestDTO.getEmail());
        student.setAddress(studentRequestDTO.getAddress());

        studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Updated successfully", HttpStatus.OK.value(),
                        studentMapper.convertToDTO(student))
        );
    }

    @Override
    public ResponseEntity<ResponseObject> searchByName(String input) {
       List<Student> listFound = studentRepository.searchStudentByNameContainingIgnoreCase(input);
       if (listFound.isEmpty()){
           return ResponseEntity.status(HttpStatus.OK).body(
                   new ResponseObject( "Cannot find with this keyword", HttpStatus.NOT_FOUND.value(), null)
           );
       } else {
           return ResponseEntity.status(HttpStatus.OK).body(
                   new ResponseObject("OK", HttpStatus.OK.value(), listFound)
           );
       }
    }
}
