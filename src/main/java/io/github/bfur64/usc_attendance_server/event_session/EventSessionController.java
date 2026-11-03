package io.github.bfur64.usc_attendance_server.event_session;

import io.github.bfur64.usc_attendance_server.event_session.dto.EventSessionResponse;
import io.github.bfur64.usc_attendance_server.event_session.dto.EventSessionRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class EventSessionController {

    private final EventSessionService service;

    @GetMapping("/events/{id}/sessions")
    public List<EventSessionResponse> findAllByEventId(Long id) {
        return service.findAllByEventId(id);
    }

    @PostMapping("/events/{id}/sessions")
    public ResponseEntity<EventSessionResponse> create(@PathVariable Long id, @Valid EventSessionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(id, request));
    }

    @PutMapping("/sessions/{id}")
    public ResponseEntity<EventSessionResponse> update(@PathVariable Long id, @Valid EventSessionRequest request) {
        return service.update(id, request).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Implement when the other tables exist
    @DeleteMapping("/sessions/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

}
