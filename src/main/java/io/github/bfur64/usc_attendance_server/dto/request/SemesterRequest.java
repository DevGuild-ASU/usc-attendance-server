package io.github.bfur64.usc_attendance_server.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SemesterRequest(
    @NotNull
    Long yearId,

    @NotNull
    @Min(value = 1, message = "Semester number must be at least 1")
    @Max(value = 2, message = "Semester number must not exceed 2")
    Integer semesterNumber
) {}
