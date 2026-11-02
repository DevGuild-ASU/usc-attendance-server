package io.github.bfur64.usc_attendance_server.repository;

import io.github.bfur64.usc_attendance_server.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterRepository extends JpaRepository<Semester, Long> {}
