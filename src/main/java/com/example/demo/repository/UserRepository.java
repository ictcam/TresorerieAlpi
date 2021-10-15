/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.entity.User;
import java.util.List;
import java.util.Optional;
import net.minidev.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Casimir
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
//    Optional<User> findByName(String name);
    Boolean existsByUsername(String username);
//    Boolean existsByEmail(String email);
//    Boolean existsByTel(String tel);
//    Long count(User u);
   
    String count = "select count(u.id) from user u where u.etat = 1 ";
  
    @Query(value=count, nativeQuery = true)
    public Long Count();
    
    String all = "SELECT u.username, u.id, r.name as role"
            + " FROM user_roles ur "
            + "join user u on ur.user_id = u.id "
            + "join roles r on ur.role_id = r.id "
            ;
  
    @Query(value=all, nativeQuery = true)
    public List<JSONObject> getUsers();
    
    String activeuser = "SELECT u.username, u.etat, u.id, r.name as role"
            + " FROM user_roles ur "
            + "join user u on ur.user_id = u.id "
            + "join roles r on ur.role_id = r.id "
            + "where u.etat = 1  and r.name != \"ROLE_SUPER_ADMIN\"";
  
    @Query(value=activeuser, nativeQuery = true)
    public List<JSONObject> getActiveUsers();
    
    String user = "SELECT u.username, u.etat, u.id, r.name as role"
            + " FROM user_roles ur "
            + "join user u on ur.user_id = u.id "
            + "join roles r on ur.role_id = r.id "
            + "where u.id = ?1";
  
    @Query(value=user, nativeQuery = true)
    public JSONObject getUser(Long id);
}