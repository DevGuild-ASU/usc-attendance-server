package io.github.bfur64.usc_attendance_server.event_session;

import io.github.bfur64.usc_attendance_server.event.Event;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class EventSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "event_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_event_session_event")
    )
    private Event event;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventSessionType type;

    @Column(nullable = false, precision = 3)
    private BigDecimal fee;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant modifiedAt;

    public EventSession(@NonNull Event event, @NonNull LocalDate date, @NonNull EventSessionType type, @NonNull BigDecimal fee) {
        checkDateRange(date, event.getDateStart(), event.getDateEnd());

        this.event = event;
        this.date = date;
        this.type = type;
        this.fee = fee;
    }

    public void update(@NonNull LocalDate date, @NonNull EventSessionType type, @NonNull BigDecimal fee) {
        checkDateRange(date, event.getDateStart(), event.getDateEnd());

        this.date = date;
        this.type = type;
        this.fee = fee;
    }

    private static void checkDateRange(LocalDate date, LocalDate dateStart, LocalDate dateEnd) {
        if (date.isBefore(dateStart) || date.isAfter(dateEnd)) {
            throw new IllegalArgumentException("Date must fall within the specified date range");
        }
    }
}
