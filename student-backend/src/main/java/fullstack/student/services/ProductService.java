package fullstack.student.services;

import fullstack.student.exception.FuncErrorException;
import fullstack.student.exception.NotFoundException;
import fullstack.student.models.Product;
import fullstack.student.models.ProductRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    public void uploadImage(final Integer id, final MultipartFile file) throws NotFoundException;
    public Product create(ProductRequestDTO productRequestDTO, MultipartFile file) throws FuncErrorException;
    public List<Product> getAll();
    public Product getOne(int id);
}
