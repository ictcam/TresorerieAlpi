/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.entity.Caisse;
import com.example.demo.entity.Solde;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import net.minidev.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Casimir
 */
@Repository
public interface SoldeRepository extends JpaRepository<Solde, Long>{    
    Boolean existsByDate(LocalDate date);
    Solde findByDate(LocalDate date);
    Solde findFirstByOrderByIdDesc();
    List<Solde> findFirst2ByOrderByIdDesc();
    String add = "INSERT INTO solde (date, solde, date_modification) VALUES (?1,?2,?3)";
  
    @Query(value=add, nativeQuery = true)
    public Solde insert(LocalDate date, int solde, Date date2);
    
    String update = "UPDATE `solde` SET `solde`=?1,`date_modification`=?2 WHERE date = ?3";
  
    @Query(value=update, nativeQuery = true)
    public Solde update(int solde, Date date, LocalDate date2);
    
}
