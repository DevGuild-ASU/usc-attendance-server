package io.github.bfur64.usc_attendance_server.academic_year;

import io.github.bfur64.usc_attendance_server.academic_year.dto.AcademicYearRequest;
import io.github.bfur64.usc_attendance_server.academic_year.dto.AcademicYearResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AcademicYearService {

    private final AcademicYearRepository repository;

    @Transactional(readOnly = true)
    public List<AcademicYearResponse> findAll() {
        return repository.findAll().stream().map(existing -> new AcademicYearResponse(
            existing.getId(),
            existing.getName(),
            existing.getCreatedAt(),
            existing.getModifiedAt()
        ))
        .toList();
    }

    @Transactional(readOnly = true)
    public Optional<AcademicYearResponse> findById(Long id) {
        return repository.findById(id).map(existing -> new AcademicYearResponse(
            existing.getId(),
            existing.getName(),
            existing.getCreatedAt(),
            existing.getModifiedAt()
        ));
    }

    @Transactional
    public AcademicYearResponse create(AcademicYearRequest request) {
        AcademicYear entity = repository.save(new AcademicYear(request.name()));

        return new AcademicYearResponse(
            entity.getId(),
            entity.getName(),
            entity.getCreatedAt(),
            entity.getModifiedAt()
        );
    }

    @Transactional
    public Optional<AcademicYearResponse> update(Long id, AcademicYearRequest request) {
        return repository.findById(id).map(existing -> {
            existing.update(request.name());

            return new AcademicYearResponse(
                existing.getId(),
                existing.getName(),
                existing.getCreatedAt(),
                existing.getModifiedAt()
            );
        });
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
