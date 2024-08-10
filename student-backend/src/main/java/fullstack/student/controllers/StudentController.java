package fullstack.student.controllers;

import fullstack.student.models.dto.ResponseObject;
import fullstack.student.models.dto.StudentRequestDTO;
import fullstack.student.models.entity.Student;
import fullstack.student.services.StudentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/student")
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    @Operation(summary = "Get all students")
    @ApiResponse(responseCode = "200", description = "Get alls students succesfully")
    @GetMapping("/getAllStudents")
    public ResponseEntity<ResponseObject> getAllStudents(){
        return studentService.getAllStudents();
    }

    @Operation(summary = "Get student by id", description = "Return a single student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student found"),
            @ApiResponse(responseCode = "404", description = "Student not found")
    })
    @GetMapping("/getStudent/{id}")
    public ResponseEntity<ResponseObject> getStudentById(@PathVariable int id){
        return studentService.getStudent(id);
    }

    @Operation(summary = "Create a new student", description = "Return a list of students")
    @ApiResponse(responseCode = "200", description = "List found")
    @PostMapping("createStudent")
    public ResponseEntity<ResponseObject> createStudent(@RequestBody @Valid StudentRequestDTO studentRequestDTO){
        return studentService.createStudent(studentRequestDTO);
    }

    @Operation(summary = "Update a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated successfully"),
            @ApiResponse(responseCode = "201", description = "Create a new student because student is not found!")

    })
    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<ResponseObject> updateStudent(@PathVariable int id,
                                                        @RequestBody @Valid StudentRequestDTO studentRequestDTO){
        return studentService.updateStudent(id, studentRequestDTO);
    }

    @Operation(summary = "Delete a student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Delete FAILED! Cannot found student by this id")
    })
    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<ResponseObject> deleteStudentById(@PathVariable int id){
        return studentService.deleteStudent(id);
    }

    @Operation(summary = "Search students by name containing")
    @ApiResponse(responseCode = "200", description = "Completed search!")
    @GetMapping("/search")
    public ResponseEntity<ResponseObject> searchByName(@RequestParam String keyword){
        return studentService.searchByName(keyword);
    }
}
