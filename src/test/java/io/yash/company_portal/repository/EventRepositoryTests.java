package io.yash.company_portal.repository;

import io.yash.company_portal.entity.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class EventRepositoryTests {

    @Autowired
    private EventRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateEvent(){
        Event event = new Event();
        event.setFullName("Yash Gurijala");
        event.setDate("2020-2-26");
        event.setDescription("Random event");

        Event savedEvent = repo.save(event);
        Event existingEvent = entityManager.find(Event.class, savedEvent.getId());

        assertThat(existingEvent.getDescription()).isEqualTo(event.getDescription());
    }
}
