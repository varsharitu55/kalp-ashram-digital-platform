package com.kalpashram.user.repository;

import com.kalpashram.user.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByMobileNumber_ReturnsUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("varsha");
        userEntity.setEmail("varsharitu55@gmail.com");
        userEntity.setMobileNumber("7838834930");
        userEntity.setUserRole("admin");
        userRepository.save(userEntity);

        Optional<UserEntity> found = userRepository.findByMobileNumber("7838834930");
        assertTrue(found.isPresent());
        assertEquals("varsha", found.get().getName());

    }
}
