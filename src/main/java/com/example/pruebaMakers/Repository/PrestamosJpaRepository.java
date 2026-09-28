package com.example.pruebaMakers.Repository;

import com.example.pruebaMakers.Entity.Models.PrestamosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamosJpaRepository extends JpaRepository<PrestamosEntity, Long> {
    List<PrestamosEntity> findByUserEmail(String email);
}