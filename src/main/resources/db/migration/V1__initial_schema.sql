CREATE TABLE academic_year (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    modified_at DATETIME(6) NOT NULL,

    CONSTRAINT uq_academic_year_name
        UNIQUE (name)
);

CREATE TABLE semester (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    year_id BIGINT NOT NULL,
    type VARCHAR(6) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    modified_at DATETIME(6) NOT NULL,

    CONSTRAINT fk_semester_academic_year
        FOREIGN KEY (year_id)
        REFERENCES academic_year(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    CONSTRAINT chk_type
        CHECK ( type IN ('first', 'second') ),

    CONSTRAINT uq_semester_number
        UNIQUE (year_id, type)
);

CREATE TABLE event (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    semester_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    date_start DATE NOT NULL,
    date_end DATE NOT NULL,
    created_at DATETIME(6) NOT NULL,
    modified_at DATETIME(6) NOT NULL,

    CONSTRAINT fk_event_semester
        FOREIGN KEY (semester_id)
        REFERENCES semester(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    CONSTRAINT chk_event_dates
        CHECK (date_end >= date_start)
);

CREATE TABLE event_session (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_id BIGINT NOT NULL,
    date DATE NOT NULL,
    type VARCHAR(16) NOT NULL,
    fee DECIMAL(3, 0) NOT NULL,

    CONSTRAINT fk_event_session_event
        FOREIGN KEY (event_id)
        REFERENCES event(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    CONSTRAINT uq_event_session
        UNIQUE (event_id, date, type),

    CONSTRAINT chk_type
        CHECK ( type IN ('morning_login', 'morning_logout', 'afternoon_login', 'afternoon_logout') ),

    CONSTRAINT chk_event_session_fee
        CHECK (fee >= 0)
);
