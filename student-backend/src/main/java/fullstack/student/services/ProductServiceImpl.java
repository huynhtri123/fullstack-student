package fullstack.student.services;

import fullstack.student.exception.FuncErrorException;
import fullstack.student.exception.NotFoundException;
import fullstack.student.models.dto.CloudinaryResponse;
import fullstack.student.models.dto.ProductResponseDTO;
import fullstack.student.models.dto.ResponseObject;
import fullstack.student.models.entity.Product;
import fullstack.student.models.dto.ProductRequestDTO;
import fullstack.student.repositories.ProductReposiroty;
import fullstack.student.utils.FileUploadUtil;
import fullstack.student.utils.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductReposiroty productReposiroty;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private ProductMapper productMapper;

    @Transactional
    @Override
    public void uploadImage(Integer id, MultipartFile file) throws NotFoundException {
        final Product product = productReposiroty.findById(id).orElseThrow(()-> new NotFoundException("Not found product"));
        FileUploadUtil.assertAllowed(file, FileUploadUtil.IMAGE_PATTERN);
        final String fileName = FileUploadUtil.getFileName(file.getOriginalFilename());
        final CloudinaryResponse response = cloudinaryService.uploadFile(file, fileName);
        product.setImageUrl(response.getUrl());
        product.setCloudinaryImageId(response.getPublicId());
        productReposiroty.save(product);
    }

    @Override
    public ResponseEntity<ResponseObject> create(ProductRequestDTO productRequestDTO, MultipartFile file) throws FuncErrorException {
        Product product = new Product();

        FileUploadUtil.assertAllowed(file, FileUploadUtil.IMAGE_PATTERN);
        final String fileName = FileUploadUtil.getFileName(file.getOriginalFilename());
        final CloudinaryResponse response = cloudinaryService.uploadFile(file, fileName);

        product.setImageUrl(response.getUrl());
        product.setCloudinaryImageId(response.getPublicId());

        product.setName(productRequestDTO.getProductName());

        ProductResponseDTO responseProduct = productMapper.convertToDTO(productReposiroty.save(product));
        return ResponseEntity.ok(
                new ResponseObject("Thành công", HttpStatus.OK.value(), responseProduct)
        );
    }

    @Override
    public ResponseEntity<ResponseObject> getAll() {
        List<ProductResponseDTO> list = productReposiroty.findAll()
                .stream().map(productMapper::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(
                new ResponseObject("Thanh cong", HttpStatus.OK.value(), list)
        );
    }

    @Override
    public ResponseEntity<ResponseObject> getOne(int id) {
        Product p = productReposiroty.findById(id).orElse(null);
        if (p != null){
            ProductResponseDTO responseDTO = productMapper.convertToDTO(p);
            return ResponseEntity.ok(
                    new ResponseObject("Thanh cong", HttpStatus.OK.value(), responseDTO)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Failed", HttpStatus.NOT_FOUND.value(), null)
        );

    }
}
