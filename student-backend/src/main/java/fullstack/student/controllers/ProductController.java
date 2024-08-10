package fullstack.student.controllers;

import fullstack.student.exception.FuncErrorException;
import fullstack.student.exception.NotFoundException;
import fullstack.student.models.Product;
import fullstack.student.models.ProductRequestDTO;
import fullstack.student.models.ResponseObject;
import fullstack.student.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

//    @PostMapping("/image/{id}")
//    public ResponseEntity<?> uploadImage(@PathVariable final Integer id, @RequestPart MultipartFile file) throws Exception {
//        this.productService.uploadImage(id, file);
//        return ResponseEntity.ok("Upload succesfully");
//    }

    @PostMapping("/create")
    public ResponseEntity<ResponseObject> create(@RequestParam("productName") String productName,
                                                 @RequestParam("file") MultipartFile file) throws FuncErrorException {
        ProductRequestDTO productRequestDTO = new ProductRequestDTO();
        productRequestDTO.setProductName(productName);

        if (file == null || file.isEmpty())
            throw new NotFoundException("File not found");

        Product product = productService.create(productRequestDTO, file);
        return ResponseEntity.ok(new ResponseObject(
                "Created successfully",
                HttpStatus.OK.value(),
                product
        ));
    }

    @GetMapping("/getAll")
    public List<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping("/getOne/{id}")
    public Product getOne(@PathVariable int id){
        return productService.getOne(id);
    }
}
