package io.yash.company_portal.repository;

import io.yash.company_portal.entity.Event;
import io.yash.company_portal.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

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
        event.setDay(23);
        event.setMonth(2);
        event.setYear(2021);
        event.setDescription("Random event");

        Event savedEvent = repo.save(event);
        Event existingEvent = entityManager.find(Event.class, savedEvent.getId());

        assertThat(existingEvent.getDescription()).isEqualTo(event.getDescription());
    }
}
