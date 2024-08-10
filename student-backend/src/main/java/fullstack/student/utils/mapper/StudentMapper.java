package fullstack.student.utils.mapper;

import fullstack.student.models.dto.StudentRequestDTO;
import fullstack.student.models.dto.StudentResponseDTO;
import fullstack.student.models.entity.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    @Autowired
    private ModelMapper mapper;

    public StudentResponseDTO convertToDTO(Student student){
        return mapper.map(student, StudentResponseDTO.class);
    }

    public Student convertToEntity(StudentRequestDTO studentRequestDTO){
        Student student = new Student();
        student.setName(studentRequestDTO.getName());
        student.setEmail(studentRequestDTO.getEmail());
        student.setAddress(studentRequestDTO.getAddress());

        return student;
    }
}
