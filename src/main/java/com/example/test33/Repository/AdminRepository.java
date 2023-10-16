package com.example.test33.Repository;

import com.example.test33.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface AdminRepository extends JpaRepository<Admin, Long> {
        Admin findByUsernameAndEmail(String username, String email);
    }

