package com.example.test33.Service;

import com.example.test33.Model.Admin;
import com.example.test33.Repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin getAdminById(Long adminId) {
        return adminRepository.findById(adminId).orElseThrow(() -> new MyNotFoundExeption("admin not found"));
    }

    public Admin getAdminByUsernameAndEmail(String username, String email) {
        return adminRepository.findByUsernameAndEmail(username, email);
    }


    public boolean authenticateAdmin(String username, String email, String password) {

        Admin admin = adminRepository.findByUsernameAndEmail(username, email);

        if (admin != null && admin.getPassword().equals(password)) {
            return true;
        } else
            return false;
        }
    }

