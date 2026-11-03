package io.github.bfur64.usc_attendance_server.academic_year.dto;

import java.time.Instant;

public record AcademicYearResponse(
    Long id,
    String name,
    Instant createdAt,
    Instant modifiedAt
) {}
