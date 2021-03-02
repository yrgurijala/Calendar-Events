package io.yash.company_portal.repository;

import io.yash.company_portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);

    @Modifying
    @Query("DELETE FROM User u WHERE u.email = ?1")
    void deleteByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.firstName = ?2 WHERE u.email = ?1")
    void UpdateFirstNameByEmail(String email, String firstName);
}
