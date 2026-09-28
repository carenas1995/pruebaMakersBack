package com.example.pruebaMakers.Controllers;

import com.example.pruebaMakers.Entity.Dto.PrestamoRequestDTO;
import com.example.pruebaMakers.Entity.Dto.PrestamoResponseDTO;
import com.example.pruebaMakers.Entity.PrestamosStatus;
import com.example.pruebaMakers.Services.PrestamosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
@CrossOrigin(origins = "*")
public class PrestamosController {

    private final PrestamosService prestamosService;

    public PrestamosController(PrestamosService prestamosService) {
        this.prestamosService = prestamosService;
    }

    @PostMapping
    public ResponseEntity<PrestamoResponseDTO> requestPrestamo(@Valid @RequestBody PrestamoRequestDTO request, Authentication auth) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(prestamosService.createPrestamo(auth.getName(), request));
    }

    @GetMapping("/misprestamos")
    public ResponseEntity<List<PrestamoResponseDTO>> getMyPrestamos(Authentication auth) {
        return ResponseEntity.ok(prestamosService.getPrestamosByUser(auth.getName()));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<PrestamoResponseDTO>> getAllPrestamos() {
        return ResponseEntity.ok(prestamosService.getAllPrestamos());
    }

    @PutMapping("/{id}/aprobado")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PrestamoResponseDTO> approvePrestamo(@PathVariable Long id) {
        return ResponseEntity.ok(prestamosService.actualizarStatus(id, PrestamosStatus.APROBADO));
    }

    @PutMapping("/{id}/rechazado")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PrestamoResponseDTO> rejectPrestamo(@PathVariable Long id) {
        return ResponseEntity.ok(prestamosService.actualizarStatus(id, PrestamosStatus.RECHAZADO));
    }
}