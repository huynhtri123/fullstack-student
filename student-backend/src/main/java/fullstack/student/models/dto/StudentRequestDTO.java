package fullstack.student.models.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Student entity")
public class StudentRequestDTO {
    @NotBlank(message = "Bắt buộc nhập name")
    @Schema(description = "Name of Student", example = "Tri")
    private String name;
    @Schema(description = "Email of Student", example = "tri@gmail.com")
    @NotBlank(message = "Bắt buộc nhập email")
    private String email;
    @Schema(description = "Address of Student", example = "Long An, VietNam")
    @NotBlank(message = "Bắt buộc nhập address")
    private String address;
}
