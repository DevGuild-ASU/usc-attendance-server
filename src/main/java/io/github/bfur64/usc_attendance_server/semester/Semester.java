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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "year_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_semester_academic_year")
    )
    @NonNull
    private AcademicYear academicYear;

    @NonNull
    @Column(nullable = false)
    private Byte semesterNumber;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant modifiedAt;

    public void update(@NonNull AcademicYear academicYear, @NonNull Byte semesterNumber) {
        this.academicYear = academicYear;
        this.semesterNumber = semesterNumber;
    }

}
