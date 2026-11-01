package io.github.bfur64.usc_attendance_server.dto.response;

import java.time.LocalDateTime;

public record AcademicYearResponse(
        Long id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {}
