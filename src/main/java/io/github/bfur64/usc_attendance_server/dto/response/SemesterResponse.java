package io.github.bfur64.usc_attendance_server.dto.response;

import java.time.Instant;

public record SemesterResponse(
    Long id,
    Long yearId,
    Integer semesterNumber,
    Instant createdAt,
    Instant modifiedAt
) {}
