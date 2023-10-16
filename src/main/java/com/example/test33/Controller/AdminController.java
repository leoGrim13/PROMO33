package com.example.test33.Controller;

import com.example.test33.Request.LoginRequest;
import com.example.test33.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class AdminController {

        @Autowired
        private AdminService adminService;

        @PostMapping("/log")
        public ResponseEntity<String> login(@RequestBody LoginRequest request) {
            boolean isAuthenticated = adminService.authenticateAdmin(
                    request.getUsername(),
                    request.getEmail(),
                    request.getPassword()
            );

            if (isAuthenticated) {
                return ResponseEntity.ok("BRAVO");
            } else {
                return ResponseEntity.status(HttpStatus.OK).body("wtf fail again");

            }
        }

    }

