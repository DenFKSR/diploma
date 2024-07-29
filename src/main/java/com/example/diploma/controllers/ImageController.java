package com.example.diploma.controllers;

import com.example.diploma.model.db.entity.images.Image;
import com.example.diploma.service.impl.carImpl.image.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;


    @PostMapping("/add")
    public Image postImage(@RequestParam("file") MultipartFile file) throws IOException {
        Image uploadedImage = imageService.postImage(file);
        return uploadedImage;

    }

    @DeleteMapping("/deleteImage/{id}")
    @Operation(summary = "Удаление картинки")
    public String deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        String message = "Изображение удалено";
        return message;
    }

    @GetMapping("/getImage/{id}")
    @Operation(summary = "Получение картинки")
    public ResponseEntity<?> getImage(@PathVariable Long id) {
        return imageService.getImage(id);

    }

    @PutMapping("/set/{carId}/{imageId}")
    public void setImage(@PathVariable Long carId, @PathVariable Long imageId) {
        imageService.setImage(carId, imageId);
    }
}
