package io.github.bfur64.usc_attendance_server.event;

import io.github.bfur64.usc_attendance_server.event.dto.EventRequest;
import io.github.bfur64.usc_attendance_server.event.dto.EventResponse;
import io.github.bfur64.usc_attendance_server.semester.Semester;
import io.github.bfur64.usc_attendance_server.semester.SemesterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repository;
    private final SemesterRepository semesterRepository;

    @Transactional(readOnly = true)
    public List<EventResponse> findAll() {
        return repository.findAll().stream().map(existing -> new EventResponse(
            existing.getId(),
            existing.getSemester().getId(),
            existing.getName(),
            existing.getDateStart(),
            existing.getDateEnd(),
            existing.getCreatedAt(),
            existing.getModifiedAt()
        ))
        .toList();
    }

    @Transactional(readOnly = true)
    public Optional<EventResponse> findById(Long id) {
        return repository.findById(id).map(existing -> new EventResponse(
            existing.getId(),
            existing.getSemester().getId(),
            existing.getName(),
            existing.getDateStart(),
            existing.getDateEnd(),
            existing.getCreatedAt(),
            existing.getModifiedAt()
        ));
    }

    @Transactional
    public EventResponse create(EventRequest request) {
        Semester semester = semesterRepository.findById(request.semesterId())
            .orElseThrow(() -> new EntityNotFoundException("Semester not found"));

        Event entity = repository.save(new Event(
            semester,
            request.name(),
            request.dateStart(),
            request.dateEnd()
        ));

        return new EventResponse(
            entity.getId(),
            entity.getSemester().getId(),
            entity.getName(),
            entity.getDateStart(),
            entity.getDateEnd(),
            entity.getCreatedAt(),
            entity.getModifiedAt()
        );
    }

    @Transactional
    public Optional<EventResponse> update(Long id, EventRequest request) {
        return repository.findById(id).map(existing -> {
            Semester semester = semesterRepository.findById(request.semesterId())
                .orElseThrow(() -> new EntityNotFoundException("Semester not found"));

            existing.update(semester, request.name(), request.dateStart(), request.dateEnd());

            return new EventResponse(
                existing.getId(),
                existing.getSemester().getId(),
                existing.getName(),
                existing.getDateStart(),
                existing.getDateEnd(),
                existing.getCreatedAt(),
                existing.getModifiedAt()
            );
        });
    }

}
