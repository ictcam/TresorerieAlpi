/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Controller;

import com.example.demo.entity.Caisse;
import com.example.demo.entity.Notifications;
import com.example.demo.entity.Solde;
import com.example.demo.message.ResponseMessage;
import com.example.demo.repository.NotificationsRepository;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.repository.SoldeRepository;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Casimir
 */
@Controller
@CrossOrigin("*")
@RequestMapping(value = "/soldeCaisse")
public class SoldeController {
    
    @Autowired
    SoldeRepository soldeRepository;
    
    @Autowired
    NotificationsRepository notificationRepository;
    
    int solde;
    LocalDate date1, date2 ;
    JSONObject json, json2, json3, json4;
    String mts;
    
    @GetMapping()
    public ResponseEntity<?> getLastSolde(){
        Solde s = new Solde();
        if(soldeRepository.existsByDate(LocalDate.now())){
            s = soldeRepository.findFirst2ByOrderByIdDesc().get(1);
            System.err.println("solde dernier "+s.getSolde());            
        }else{
            s = soldeRepository.findFirstByOrderByIdDesc();
            System.err.println("solde dernier "+s.getSolde());
        }
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
//    
//    @GetMapping("/last")
//    public ResponseEntity<?> hier(){
//        LocalDate date = LocalDate.now().minusDays(1);
//        List<JSONObject> c = caisseRepository.today(date);
//        return new ResponseEntity<>(c, HttpStatus.OK);
//    }
//    
}
