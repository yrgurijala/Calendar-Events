package io.yash.company_portal.repository;

import io.yash.company_portal.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.date = ?1")
    List<Event> findEventsByDate(String date);
}
