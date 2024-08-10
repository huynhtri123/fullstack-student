package fullstack.student.repositories;

import fullstack.student.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductReposiroty extends JpaRepository<Product, Integer> {
}
