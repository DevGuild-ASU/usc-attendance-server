package io.github.bfur64.usc_attendance_server.semester.dto;

import io.github.bfur64.usc_attendance_server.semester.SemesterType;

import java.time.Instant;

public record SemesterResponse(
    Long id,
    Long yearId,
    SemesterType type,
    Instant createdAt,
    Instant modifiedAt
) {}
