package io.github.bfur64.usc_attendance_server.repository;

import io.github.bfur64.usc_attendance_server.entity.AcademicYear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicYearRepository extends JpaRepository<AcademicYear, Long> {}
