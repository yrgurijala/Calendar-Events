package io.yash.company_portal.repository;

import io.yash.company_portal.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
