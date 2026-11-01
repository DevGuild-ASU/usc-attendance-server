package io.github.bfur64.usc_attendance_server.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AcademicYearRequest(
        @NotBlank String name
) {}
