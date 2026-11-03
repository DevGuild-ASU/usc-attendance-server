package io.github.bfur64.usc_attendance_server.semester;

import io.github.bfur64.usc_attendance_server.semester.dto.SemesterRequest;
import io.github.bfur64.usc_attendance_server.semester.dto.SemesterResponse;
import io.github.bfur64.usc_attendance_server.academic_year.AcademicYear;
import io.github.bfur64.usc_attendance_server.academic_year.AcademicYearRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SemesterService {

    private final SemesterRepository repository;
    private final AcademicYearRepository academicYearRepository;

    @Transactional(readOnly = true)
    public List<SemesterResponse> findAll() {
        return repository.findAll().stream().map(existing -> new SemesterResponse(
            existing.getId(),
            existing.getAcademicYear().getId(),
            existing.getSemesterType(),
            existing.getCreatedAt(),
            existing.getModifiedAt()
        ))
        .toList();
    }

    @Transactional(readOnly = true)
    public Optional<SemesterResponse> findById(Long id) {
        return repository.findById(id).map(existing -> new SemesterResponse(
            existing.getId(),
            existing.getAcademicYear().getId(),
            existing.getSemesterType(),
            existing.getCreatedAt(),
            existing.getModifiedAt()
        ));
    }

    @Transactional
    public SemesterResponse create(SemesterRequest request) {
        AcademicYear academicYear = academicYearRepository.findById(request.yearId())
            .orElseThrow(() -> new EntityNotFoundException("Academic year not found"));

        Semester entity = repository.save(new Semester(academicYear, request.semesterType()));

        return new SemesterResponse(
            entity.getId(),
            entity.getAcademicYear().getId(),
            entity.getSemesterType(),
            entity.getCreatedAt(),
            entity.getModifiedAt()
        );
    }

    @Transactional
    public Optional<SemesterResponse> update(Long id, SemesterRequest request) {
        return repository.findById(id).map(existing -> {
            AcademicYear academicYear = academicYearRepository.findById(request.yearId())
                    .orElseThrow(() -> new EntityNotFoundException("Academic year not found"));

            existing.update(academicYear, request.semesterType());

            return new SemesterResponse(
                existing.getId(),
                existing.getAcademicYear().getId(),
                existing.getSemesterType(),
                existing.getCreatedAt(),
                existing.getModifiedAt()
            );
        });
    }

}
