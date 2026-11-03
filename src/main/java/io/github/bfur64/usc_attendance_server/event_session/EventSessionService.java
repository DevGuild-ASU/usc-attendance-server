package io.github.bfur64.usc_attendance_server.event_session;

import io.github.bfur64.usc_attendance_server.event.Event;
import io.github.bfur64.usc_attendance_server.event.EventRepository;
import io.github.bfur64.usc_attendance_server.event_session.dto.EventSessionResponse;
import io.github.bfur64.usc_attendance_server.event_session.dto.EventSessionRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventSessionService {

    private final EventSessionRepository repository;
    private final EventRepository eventRepository;

    @Transactional(readOnly = true)
    public List<EventSessionResponse> findAllByEventId(Long id) {
        return repository.findAllByEventId(id).stream().map(existing ->
            new EventSessionResponse(
                existing.getId(),
                existing.getEvent().getId(),
                existing.getDate(),
                existing.getType(),
                existing.getFee(),
                existing.getCreatedAt(),
                existing.getModifiedAt()
            ))
            .toList();
    }

    @Transactional
    public EventSessionResponse create(Long id, EventSessionRequest request) {
        Event event = eventRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Event id not found"));

        EventSession entity = repository.save(new EventSession(event, request.date(), request.type(), request.fee()));

        return new EventSessionResponse(
            entity.getId(),
            entity.getEvent().getId(),
            entity.getDate(),
            entity.getType(),
            entity.getFee(),
            entity.getCreatedAt(),
            entity.getModifiedAt()
        );
    }

    @Transactional
    public Optional<EventSessionResponse> update(Long id, EventSessionRequest request) {
        return repository.findById(id).map(existing -> {
            existing.update(request.date(), request.type(), request.fee());

            return new EventSessionResponse(
                existing.getId(),
                existing.getEvent().getId(),
                existing.getDate(),
                existing.getType(),
                existing.getFee(),
                existing.getCreatedAt(),
                existing.getModifiedAt()
            );
        });
    }

}
