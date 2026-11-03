package io.github.bfur64.usc_attendance_server.event_session;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventSessionRepository extends JpaRepository<EventSession, Long> {

    List<EventSession> findAllByEventId(Long id);
}
