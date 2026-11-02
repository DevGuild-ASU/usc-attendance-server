CREATE TABLE academic_year (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    modified_at DATETIME(6) NOT NULL
);

CREATE TABLE semester (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    year_id BIGINT NOT NULL,
    semester_number TINYINT NOT NULL,
    created_at DATETIME(6) NOT NULL,
    modified_at DATETIME(6) NOT NULL,

    CONSTRAINT fk_semester_academic_year
        FOREIGN KEY (year_id)
        REFERENCES academic_year(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    CONSTRAINT chk_semester_number
        CHECK ( semester_number IN (1, 2)),

    CONSTRAINT uq_semester_number
        UNIQUE (year_id, semester_number)
);
