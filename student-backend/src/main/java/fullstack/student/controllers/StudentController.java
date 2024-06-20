package fullstack.student.controllers;

import fullstack.student.models.ResponseObject;
import fullstack.student.models.Student;
import fullstack.student.services.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/student")
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/getAllStudents")
    public ResponseEntity<ResponseObject> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/getStudent/{id}")
    public ResponseEntity<ResponseObject> getStudentById(@PathVariable int id){
        return studentService.getStudent(id);
    }

    @PostMapping("createStudent")
    public ResponseEntity<ResponseObject> createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<ResponseObject> updateStudent(@PathVariable int id, @RequestBody Student newStudent){
        return studentService.updateStudent(id, newStudent);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<ResponseObject> deleteStudentById(@PathVariable int id){
        return studentService.deleteStudent(id);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseObject> searchByName(@RequestParam String keyword){
        return studentService.searchByName(keyword);
    }
}
