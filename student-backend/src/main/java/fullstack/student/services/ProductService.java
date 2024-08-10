package fullstack.student.services;

import fullstack.student.exception.FuncErrorException;
import fullstack.student.exception.NotFoundException;
import fullstack.student.models.dto.ProductResponseDTO;
import fullstack.student.models.dto.ResponseObject;
import fullstack.student.models.entity.Product;
import fullstack.student.models.dto.ProductRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    public void uploadImage(final Integer id, final MultipartFile file) throws NotFoundException;
    public ResponseEntity<ResponseObject> create(ProductRequestDTO productRequestDTO, MultipartFile file) throws FuncErrorException;
    public ResponseEntity<ResponseObject> getAll();
    public ResponseEntity<ResponseObject> getOne(int id);
}
