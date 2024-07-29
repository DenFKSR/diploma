package com.example.diploma.model.db.repository;

import com.example.diploma.model.db.entity.images.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ImageRepo extends JpaRepository<Image, Long> {
    Optional<Image> findById(Long id);

    Optional<Image> findByOriginalFileName(String originalFileName);


    @Transactional
    @Modifying
    @Query("delete from Image i where i.id = ?1")
    void deleteImageById(Long id);
}
