package io.github.bfur64.usc_attendance_server.event_session.dto;

import io.github.bfur64.usc_attendance_server.event_session.EventSessionType;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record EventSessionResponse(
    Long id,
    Long eventId,
    LocalDate date,
    EventSessionType type,
    BigDecimal fee,
    Instant createdAt,
    Instant modifiedAt
) {}
