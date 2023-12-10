package com.example.test33;

import com.example.test33.Model.Admin;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AdminTest {

    @Test
    public void testAdminGettersAndSetters() {
        Admin admin = new Admin();
        admin.setUsername("testUser");
        admin.setEmail("test@example.com");
        admin.setPassword("testPassword");

        Long id = 1L;
        admin.setId(id);

        assertEquals(id, admin.getId());
        assertEquals("testUser", admin.getUsername());
        assertEquals("test@example.com", admin.getEmail());
        assertEquals("testPassword", admin.getPassword());
    }
}

