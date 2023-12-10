package com.example.test33;

import com.example.test33.Request.LoginRequest;
import com.example.test33.Service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AdminService adminService;

    @BeforeEach
    void setUp() {
        when(adminService.authenticateAdmin("testUsername", "testEmail", "testPassword")).thenReturn(true);
    }

    @Test
    void testLoginEndpoint() throws Exception {
        String requestBody = "{\"username\":\"testUsername\",\"email\":\"testEmail\",\"password\":\"testPassword\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/log")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }
}

