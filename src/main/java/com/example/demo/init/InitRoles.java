/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.init;

import com.example.demo.models.RoleName;
import com.example.demo.repository.RoleRepository;
import com.example.demo.entity.Roles;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Casimir
 */

@Component
@Order(1)
public class InitRoles implements ApplicationRunner{

    
    @Autowired
    PasswordEncoder encoder;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    UserRepository utilisateurRepository;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {;
        System.out.println("initialisation des roles");        
        Roles roleUser = new Roles(RoleName.ROLE_ADMIN);
        Roles roleSuperAdmin = new Roles(RoleName.ROLE_SUPER_ADMIN);
        Roles roleTresorier = new Roles(RoleName.ROLE_TRESORIER);

        if (!roleRepository.existsByName(RoleName.ROLE_ADMIN)) {
            roleRepository.save(roleUser);
        }

        if (!roleRepository.existsByName(RoleName.ROLE_TRESORIER)) {
            roleRepository.save(roleTresorier);
        }

        if (!roleRepository.existsByName(RoleName.ROLE_SUPER_ADMIN)) {
            roleRepository.save(roleSuperAdmin);
        }
     
    }
    
}
