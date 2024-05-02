package com.Pfe.ProjetCollaboratif.Service;

import com.Pfe.ProjetCollaboratif.Entity.Admin;
import com.Pfe.ProjetCollaboratif.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository;


    @Override
    public Admin ajouterAdmin(Admin admin) {

        return adminRepository.save(admin);
    }

    @Override
    public Admin modifierAdmin(Admin admin) {

        return adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAdmin() {

            return adminRepository.findAll();
    }

    @Override
    public void Deleteadmin(Long id) {
        adminRepository.deleteById(id);

    }

    @Override
    public Optional<Admin> getAdminById(Long id) {

        return adminRepository.findById(id);
    }
}
