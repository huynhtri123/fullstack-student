package fullstack.student.services;

import com.cloudinary.Cloudinary;
import fullstack.student.exception.FuncErrorException;
import fullstack.student.models.CloudinaryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;

    @Transactional
    @Override
    public CloudinaryResponse uploadFile(MultipartFile file, String fileName) throws FuncErrorException {
        try {
            //tải tệp lên cloudinary
            final Map result = cloudinary.uploader().upload(
                    file.getBytes(), Map.of("public_id", "fullstack/product/" + fileName));

            final String url = (String) result.get("secure_url");
            final String publicId = (String) result.get("public_id");
            return CloudinaryResponse.builder()
                    .publicId(publicId)
                    .url(url)
                    .build();

        } catch (Exception e) {
            throw new FuncErrorException("Failed to upload file, " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public boolean deleteFileById(String publicId) throws FuncErrorException {
        try {
            Map result = cloudinary.uploader().destroy(publicId, Map.of());

            // Kiểm tra xem ảnh đã bị xóa thành công chưa
            String resultStatus = (String) result.get("result");
            return "ok".equals(resultStatus);

        } catch (Exception e) {
            throw new FuncErrorException("Failed to delete file, " + e.getMessage());
        }
    }
}
