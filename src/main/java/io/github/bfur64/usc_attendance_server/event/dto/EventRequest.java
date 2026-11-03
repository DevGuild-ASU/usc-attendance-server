package io.github.bfur64.usc_attendance_server.event.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record EventRequest(
    @NotNull
    Long semesterId,

    @NotBlank
    @Size(max = 255)
    String name,

    @NotNull
    LocalDate dateStart,

    @NotNull
    LocalDate dateEnd
) {}
