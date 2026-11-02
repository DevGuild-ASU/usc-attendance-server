package io.github.bfur64.usc_attendance_server.dto.response;

import java.time.Instant;

public record SemesterResponse(
    Long id,
    Long yearId,
    Byte semesterNumber,
    Instant createdAt,
    Instant modifiedAt
) {}
