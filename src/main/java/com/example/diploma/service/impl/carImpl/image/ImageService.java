package com.example.diploma.service.impl.carImpl.image;

import com.example.diploma.model.db.entity.images.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Transactional
public interface ImageService {
    Image postImage(MultipartFile file) throws IOException;

    void deleteImage(Long id);

    void setImage(Long carId, Long imageId);

    ResponseEntity<?> getImage(Long id);
}
