package io.github.bfur64.usc_attendance_server.semester;

import io.github.bfur64.usc_attendance_server.semester.dto.SemesterRequest;
import io.github.bfur64.usc_attendance_server.semester.dto.SemesterResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/semesters")
@RequiredArgsConstructor
public class SemesterController {

    private final SemesterService service;

    @GetMapping
    public List<SemesterResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SemesterResponse> findById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SemesterResponse> create(@Valid @RequestBody SemesterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PostMapping("/{id}")
    public ResponseEntity<SemesterResponse> update(@PathVariable Long id, @Valid @RequestBody SemesterRequest request) {
        return service.update(id, request).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Implement when the other tables exist
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

}
