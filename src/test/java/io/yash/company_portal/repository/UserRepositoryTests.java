package io.yash.company_portal.repository;

import io.yash.company_portal.entity.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @Order(1)
    public void testCreateUser(){
        User user = new User();
        user.setEmail("user2@gmail.com");
        user.setPassword("user45$");
        user.setFirstName("User");
        user.setLastName("2");

        User savedUser = repo.save(user);
        User existingUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existingUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    @Order(2)
    public void testFindUserByEmail(){
        String email = "user2@gmail.com";
        User user = repo.findByEmail(email);

        assertThat(user).isNotNull();
    }

    @Test
    @Order(3)
    public void testUpdateFirstNameByEmail(){
        String email = "user2@gmail.com";
        String newFirstName = "Admin";
        repo.UpdateFirstNameByEmail(email, newFirstName);

        User updatedUser = repo.findByEmail(email);

        assertThat(updatedUser.getFirstName()).isEqualTo(newFirstName);
    }

    @Test
    @Order(4)
    public void testDeleteUserByEmail(){
        String email = "user2@gmail.com";
        repo.deleteByEmail(email);

        assertThat(repo.findByEmail(email)).isNull();
    }
}
