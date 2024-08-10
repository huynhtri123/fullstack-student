package fullstack.student.services;

import fullstack.student.exception.FuncErrorException;
import fullstack.student.exception.NotFoundException;
import fullstack.student.models.CloudinaryResponse;
import fullstack.student.models.Product;
import fullstack.student.models.ProductRequestDTO;
import fullstack.student.repositories.ProductReposiroty;
import fullstack.student.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductReposiroty productReposiroty;
    @Autowired
    private CloudinaryService cloudinaryService;

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
    public Product create(ProductRequestDTO productRequestDTO, MultipartFile file) throws FuncErrorException {
        Product product = new Product();

        FileUploadUtil.assertAllowed(file, FileUploadUtil.IMAGE_PATTERN);
        final String fileName = FileUploadUtil.getFileName(file.getOriginalFilename());
        final CloudinaryResponse response = cloudinaryService.uploadFile(file, fileName);

        product.setImageUrl(response.getUrl());
        product.setCloudinaryImageId(response.getPublicId());

        product.setName(productRequestDTO.getProductName());
        return productReposiroty.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productReposiroty.findAll();
    }

    @Override
    public Product getOne(int id) {
        return productReposiroty.findById(id).orElse(null);
    }
}
