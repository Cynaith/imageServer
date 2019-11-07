package com.ly.imageserver.repository;

import com.ly.imageserver.domain.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
public interface ImagesRepository extends JpaRepository<Images,Long> {
}
