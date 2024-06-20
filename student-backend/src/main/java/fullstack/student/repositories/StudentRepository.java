package fullstack.student.repositories;

import fullstack.student.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    //tim trong ten, ko phan biet chu hoa thuong
    public List<Student> searchStudentByNameContainingIgnoreCase(String input);
}
