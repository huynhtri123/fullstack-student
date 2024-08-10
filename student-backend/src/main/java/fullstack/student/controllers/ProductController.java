package fullstack.student.controllers;

import fullstack.student.exception.FuncErrorException;
import fullstack.student.exception.NotFoundException;
import fullstack.student.models.dto.ProductRequestDTO;
import fullstack.student.models.dto.ResponseObject;
import fullstack.student.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

        return productService.create(productRequestDTO, file);

    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseObject> getAll(){
        return productService.getAll();
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<ResponseObject> getOne(@PathVariable int id){
        return productService.getOne(id);
    }
}
