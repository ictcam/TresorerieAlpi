/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.entity.Caisse;
import java.time.LocalDate;
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
public interface CaisseRepository extends JpaRepository<Caisse, Long>{
    Caisse findFirstByOrderByIdDesc();
    List<Caisse> findByProvisoirTrue(); 
    
    String historique = "SELECT c.date, "
            + "COALESCE(SUM(c.decaissement),0) as nbre "
            + "from caisse c "
            + "where c.date between ?1 and ?2 "
            + "group BY c.date";
  
    @Query(value=historique, nativeQuery = true)
    public List<JSONObject> last30DaysGraphs(LocalDate date, LocalDate date2);
    
    String section = "SELECT c.cc, count(c.cc) as count, "
            + "COALESCE(SUM(c.decaissement),0) as nbre "
            + "from caisse c "
            + "where c.date between ?1 and ?2 and c.decaissement > 0 "
            + "group BY c.cc";
  
    @Query(value=section, nativeQuery = true)
    public List<JSONObject> sectionGraphs(LocalDate date, LocalDate date2);
    
    String today = "SELECT c.id, c.date, c.cc, c.decaissement, c.encaissement, c.solde, c.mouvement, c.piece, c.provisoir "
            + "from caisse c "
            + "where c.date = ?1 "
            + " order by id desc" ;
  
    @Query(value=today, nativeQuery = true)
    public List<JSONObject> today(LocalDate date);
    
    String range = "SELECT c.id, c.date, c.cc, c.decaissement, c.encaissement, c.solde, c.mouvement, c.piece, c.provisoir "
            + "from caisse c "
            + "where c.date between ?1 and ?2"
            + " order by id desc" ;
  
    @Query(value=range, nativeQuery = true)
    public List<JSONObject> range(LocalDate date, LocalDate date2);
    
    String months = "SELECT c.id, c.date, c.cc, c.decaissement, c.encaissement, c.solde, c.mouvement, c.piece, c.provisoir "
            + "from caisse c "
            + "where DATE_FORMAT(c.date, '%Y/%m') = ?1"
            + " order by id desc" ;
  
    @Query(value=months, nativeQuery = true)
    public List<JSONObject> month(String month);
    
    String list = "SELECT * FROM caisse c WHERE c.id > ?1 ORDER BY c.id asc" ;
  
    @Query(value=list, nativeQuery = true)
    public List<JSONObject> listForUpdate(Long id);
    
    String list2 = "SELECT * FROM caisse c WHERE c.id < ?1 ORDER BY c.id desc" ;
  
    @Query(value=list2, nativeQuery = true)
    public List<Caisse> listForUpdate2(Long id);
}
