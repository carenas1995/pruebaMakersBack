package com.example.pruebaMakers.Services;

import com.example.pruebaMakers.Entity.Dto.PrestamoRequestDTO;
import com.example.pruebaMakers.Entity.Dto.PrestamoResponseDTO;
import com.example.pruebaMakers.Entity.Models.PrestamosEntity;
import com.example.pruebaMakers.Entity.Models.UserEntity;
import com.example.pruebaMakers.Entity.PrestamosStatus;
import com.example.pruebaMakers.Repository.PrestamosJpaRepository;
import com.example.pruebaMakers.Repository.UserJpaRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrestamosService {

    private final PrestamosJpaRepository prestamosRepository;
    private final UserJpaRepository userRepository;

    public PrestamosService(PrestamosJpaRepository prestamosRepository, UserJpaRepository userRepository) {
        this.prestamosRepository = prestamosRepository;
        this.userRepository = userRepository;
    }

    private PrestamoResponseDTO mapToDTO(PrestamosEntity entity) {
        return new PrestamoResponseDTO(
                entity.getId(),
                entity.getUser().getEmail(),
                entity.getAmount(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getTermMonths()
        );
    }

    @Transactional
    @CacheEvict(value = "prestamosCache", allEntries = true)
    public PrestamoResponseDTO createPrestamo(String userEmail, PrestamoRequestDTO dto) {
        UserEntity user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + userEmail));
        PrestamosEntity prestamo = new PrestamosEntity();
        prestamo.setUser(user);
        prestamo.setAmount(dto.getAmount());
        prestamo.setTermMonths(dto.getTermMonths() != null ? dto.getTermMonths() : 12);
        prestamo.setStatus(PrestamosStatus.PENDIENTE);
        PrestamosEntity prestamoGuardado = prestamosRepository.save(prestamo);
        return mapToDTO(prestamoGuardado);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "prestamosCache", key = "#userEmail")
    public List<PrestamoResponseDTO> getPrestamosByUser(String userEmail) {
        List<PrestamosEntity> entidades = prestamosRepository.findByUserEmail(userEmail);
        List<PrestamoResponseDTO> dtos = entidades.stream()
                .map(this::mapToDTO)
                .toList();

        return dtos;
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "prestamosCache", key = "'all'")
    public List<PrestamoResponseDTO> getAllPrestamos() {
        return prestamosRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Transactional
    @CacheEvict(value = "prestamosCache", allEntries = true)
    public PrestamoResponseDTO actualizarStatus(Long prestamoId, PrestamosStatus status) {
        PrestamosEntity prestamo = prestamosRepository.findById(prestamoId)
                .orElseThrow(() -> new RuntimeException("Préstamo no encontrado ID: " + prestamoId));
        prestamo.setStatus(status);
        PrestamosEntity prestamoActualizado = prestamosRepository.save(prestamo);
        return mapToDTO(prestamoActualizado);
    }
}