package io.github.bfur64.usc_attendance_server.semester.dto;

import io.github.bfur64.usc_attendance_server.semester.SemesterType;
import jakarta.validation.constraints.NotNull;

public record SemesterRequest(
    @NotNull
    Long yearId,

    @NotNull
    SemesterType type
) {}
