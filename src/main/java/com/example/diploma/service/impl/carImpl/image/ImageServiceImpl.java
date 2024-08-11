package com.example.diploma.service.impl.carImpl.image;

import com.example.diploma.exceptions.CustomException;
import com.example.diploma.model.db.entity.Car;
import com.example.diploma.model.db.entity.images.Image;
import com.example.diploma.model.db.repository.CarRepo;
import com.example.diploma.model.db.repository.ImageRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {
    private final ImageRepo imageRepo;
    private final CarRepo carRepo;
    private ObjectMapper mapper;

    @Override
    public Image postImage(MultipartFile file) throws IOException {
        imageRepo.findByOriginalFileName(file.getOriginalFilename()).ifPresent(image -> {
            throw new CustomException("Image already exists", HttpStatus.CONFLICT);
        });
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        image = imageRepo.save(image);
        return image;
    }

    public void deleteImage(Long id) {
        imageRepo.findById(id).orElseThrow(() -> new NoSuchElementException("файл не найден"));
        imageRepo.deleteImageById(id);
    }

    public ResponseEntity<?> getImage(Long id) {
        try {
            Image image = imageRepo.findById(id).orElseThrow(() -> new NoSuchElementException("File not found"));
            return ResponseEntity.ok()
                    .header("fileName", image.getOriginalFileName())
                    .contentType(MediaType.valueOf(image.getContentType()))
                    .contentLength(image.getSize())
                    .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
        } catch (NoSuchElementException e) {
            log.error("файл не найден");
            return ResponseEntity.badRequest()
                    .body("файл не найден");
        }
    }
    public void setImage(Long carId, Long imageId) {
        Image image = imageRepo.findById(imageId).orElseThrow(() -> new NoSuchElementException("File not found"));
        Car car = carRepo.findById(carId).orElseThrow(() -> new NoSuchElementException("File not found"));
        car.setImage(image);
        image.setCar(car);
        carRepo.save(car);
        imageRepo.save(image);
    }

}
