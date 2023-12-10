package com.example.test33;

import com.example.test33.Model.Admin;
import com.example.test33.Repository.AdminRepository;
import com.example.test33.Service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private AdminRepository adminRepository;

    @Test
    void testGetAdminByUsernameAndEmail() {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setUsername("adminUser");
        admin.setEmail("admin@example.com");
        when(adminRepository.findByUsernameAndEmail("adminUser", "admin@example.com")).thenReturn(admin);
        Admin result = adminService.getAdminByUsernameAndEmail("adminUser", "admin@example.com");
        assertNotNull(result);
        assertEquals(admin.getId(), result.getId());
        assertEquals(admin.getUsername(), result.getUsername());
        assertEquals(admin.getEmail(), result.getEmail());
        verify(adminRepository, times(1)).findByUsernameAndEmail("adminUser", "admin@example.com");
    }

    @Test
    void testAuthenticateAdmin() {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setUsername("adminUser");
        admin.setEmail("admin@example.com");
        admin.setPassword("password123");
        when(adminRepository.findByUsernameAndEmail("adminUser", "admin@example.com")).thenReturn(admin);
        boolean result = adminService.authenticateAdmin("adminUser", "admin@example.com", "password123");
        assertTrue(result);
        verify(adminRepository, times(1)).findByUsernameAndEmail("adminUser", "admin@example.com");
    }
}

