package io.github.bfur64.usc_attendance_server.controller;

import io.github.bfur64.usc_attendance_server.dto.request.AcademicYearRequest;
import io.github.bfur64.usc_attendance_server.dto.response.AcademicYearResponse;
import io.github.bfur64.usc_attendance_server.service.AcademicYearService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/academic-years")
@RequiredArgsConstructor
public class AcademicYearController {

    private final AcademicYearService service;

    @GetMapping
    public List<AcademicYearResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcademicYearResponse> findById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AcademicYearResponse> create(@Valid @RequestBody AcademicYearRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcademicYearResponse> update(@PathVariable Long id, @Valid @RequestBody AcademicYearRequest request) {
        return service.update(id, request).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
