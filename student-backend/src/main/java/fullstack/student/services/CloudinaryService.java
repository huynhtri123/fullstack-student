package fullstack.student.services;

import fullstack.student.exception.FuncErrorException;
import fullstack.student.models.dto.CloudinaryResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    public CloudinaryResponse uploadFile(MultipartFile file, String fileName) throws FuncErrorException;
    public boolean deleteFileById(String publicId) throws FuncErrorException;
}
