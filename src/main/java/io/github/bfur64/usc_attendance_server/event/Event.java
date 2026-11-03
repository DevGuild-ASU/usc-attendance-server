package io.github.bfur64.usc_attendance_server.event;

import io.github.bfur64.usc_attendance_server.semester.Semester;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "semester_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_event_semester")
    )
    private Semester semester;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dateStart;

    @Column(nullable = false)
    private LocalDate dateEnd;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant modifiedAt;

    public Event(@NonNull Semester semester, @NonNull String name, @NonNull LocalDate dateStart, @NonNull LocalDate dateEnd) {
        checkDateRange(dateStart, dateEnd);

        this.semester = semester;
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public void update(@NonNull Semester semester, @NonNull String name, @NonNull LocalDate dateStart, @NonNull LocalDate dateEnd) {
        checkDateRange(dateStart, dateEnd);

        this.semester = semester;
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    private void checkDateRange(LocalDate dateStart, LocalDate dateEnd) {
        if (dateStart.isAfter(dateEnd)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
    }
}
