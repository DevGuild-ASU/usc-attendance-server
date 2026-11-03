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
@RequiredArgsConstructor
@Entity
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "semester_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_event_semester")
    )
    private Semester semester;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    private LocalDate dateStart;

    @NonNull
    @Column(nullable = false)
    private LocalDate dateEnd;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant modifiedAt;

    public void update(@NonNull Semester semester, @NonNull String name, @NonNull LocalDate dateStart, @NonNull LocalDate dateEnd) {
        this.semester = semester;
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }
}
