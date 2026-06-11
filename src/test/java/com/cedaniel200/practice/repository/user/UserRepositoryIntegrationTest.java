package com.cedaniel200.practice.repository.user;

import com.cedaniel200.practice.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void listUsers_shouldReturnNonEmptyList() throws Exception {
        List<User> users = userRepository.list();
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.get(0).getId());
        assertNotNull(users.get(0).getName());
    }

    @Test
    public void findById_existingUser_shouldReturnUser() throws Exception {
        User user = userRepository.findById(1);
        assertNotNull(user);
        assertEquals(1, user.getId());
        assertNotNull(user.getName());
    }

    @Test
    public void findById_nonExistentId_shouldReturnEmptyUser() throws Exception {
        User user = userRepository.findById(9999);
        assertNull(user);
    }
}
