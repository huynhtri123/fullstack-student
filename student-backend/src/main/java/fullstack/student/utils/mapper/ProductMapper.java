package fullstack.student.utils.mapper;

import fullstack.student.models.dto.ProductRequestDTO;
import fullstack.student.models.dto.ProductResponseDTO;
import fullstack.student.models.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    @Autowired
    private ModelMapper modelMapper;
    public ProductResponseDTO convertToDTO(Product product){
        return modelMapper.map(product, ProductResponseDTO.class);
    }
}
