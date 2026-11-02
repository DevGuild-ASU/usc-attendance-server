package io.github.bfur64.usc_attendance_server.academic_year.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AcademicYearRequest(
        @NotBlank
        @Size(max = 255)
        String name
) {}
