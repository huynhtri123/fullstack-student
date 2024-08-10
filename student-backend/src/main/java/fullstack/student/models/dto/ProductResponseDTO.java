package fullstack.student.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private int id;
    private String name;
    private String imageUrl;
    private String cloudinaryImageId;
}
