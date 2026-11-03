package io.github.bfur64.usc_attendance_server.event.dto;

import java.time.Instant;
import java.time.LocalDate;

public record EventResponse(
    Long id,
    Long semesterId,
    String name,
    LocalDate dateStart,
    LocalDate dateEnd,
    Instant createdAt,
    Instant modifiedAt
) {}
