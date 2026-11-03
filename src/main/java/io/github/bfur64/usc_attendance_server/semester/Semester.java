package io.github.bfur64.usc_attendance_server.semester;

import io.github.bfur64.usc_attendance_server.academic_year.AcademicYear;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Entity
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "year_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_semester_academic_year")
    )
    private AcademicYear academicYear;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SemesterType type;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant modifiedAt;

    public void update(@NonNull AcademicYear academicYear, @NonNull SemesterType type) {
        this.academicYear = academicYear;
        this.type = type;
    }

}
