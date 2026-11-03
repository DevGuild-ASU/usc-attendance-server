package io.github.bfur64.usc_attendance_server.event_session.dto;

import io.github.bfur64.usc_attendance_server.event_session.EventSessionType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EventSessionRequest(
    @NotNull
    LocalDate date,

    @NotNull
    EventSessionType type,

    @NotNull
    @Min(0)
    @Max(99)
    BigDecimal fee
) {}
