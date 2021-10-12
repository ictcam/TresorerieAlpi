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
import com.example.demo.repository.CaisseRepository;
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
import java.time.Instant;
import java.time.LocalTime;
import java.util.Date;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Casimir
 */
@Controller
@CrossOrigin("*")
@RequestMapping(value = "/api/caisse")
public class CaisseController {
    
    @Autowired
    CaisseRepository caisseRepository;
    
    @Autowired
    SoldeRepository soldeRepository;
    
    @Autowired
    NotificationsRepository notificationRepository;
    
    int solde;
    LocalDate date1, date2 ;
    JSONObject json, json2, json3, json4;
    String mts;
    
    @GetMapping()
    public ResponseEntity<?> getCaisse(){
        LocalDate date1 = LocalDate.now();
        List<JSONObject> c = caisseRepository.today(date1);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
    
    @GetMapping("/hier")
    public ResponseEntity<?> hier(){
        LocalDate date = LocalDate.now().minusDays(1);
        List<JSONObject> c = caisseRepository.today(date);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
    
    @GetMapping("/csem")
	public ResponseEntity<?> thisWeek(){
            
            Calendar cal = Calendar.getInstance();
            cal.setFirstDayOfWeek(0);
            int week = cal.get(Calendar.DAY_OF_WEEK);
            System.out.println("jour de la semaine: "+week);
            if(week == 1){
                date1 = LocalDate.now().minusDays(6);
                date2 = LocalDate.now();                
            }else if(week == 2){
                date1 = LocalDate.now();
                date2 = LocalDate.now().plusDays(6);                
            }else if(week == 3){
                date1 = LocalDate.now().minusDays(1);
                date2 = LocalDate.now().plusDays(5);                
            }else if(week == 4){
                date1 = LocalDate.now().minusDays(2);
                date2 = LocalDate.now().plusDays(4);                
            }else if(week == 5){
                date1 = LocalDate.now().minusDays(3);
                date2 = LocalDate.now().plusDays(3);                
            }else if(week == 6){
                date1 = LocalDate.now().minusDays(4);
                date2 = LocalDate.now().plusDays(2);                
            }else if(week == 7){
                date1 = LocalDate.now().minusDays(5);
                date2 = LocalDate.now().plusDays(1);                
            }
            System.out.println("jour de la semaine1: "+ date1.getDayOfWeek() + date1.getDayOfMonth());
            System.out.println("jour de la semaine7: "+ date2.getDayOfWeek() + date2.getDayOfMonth());
            		 
            List<JSONObject> c = caisseRepository.range(date1, date2);
        return new ResponseEntity<>(c, HttpStatus.OK);
            
	}
        
    @GetMapping("/semp")
    public ResponseEntity<?> lastWeek(){

        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(0);
        int week = cal.get(Calendar.DAY_OF_WEEK);
        if(week == 1){
            date1 = LocalDate.now().minusDays(13);
            date2 = LocalDate.now().minusDays(7);                
        }else if(week == 2){
            date1 = LocalDate.now().minusDays(7);
            date2 = LocalDate.now().minusDays(1);                
        }else if(week == 3){
            date1 = LocalDate.now().minusDays(8);
            date2 = LocalDate.now().minusDays(2);                
        }else if(week == 4){
            date1 = LocalDate.now().minusDays(9);
            date2 = LocalDate.now().minusDays(3);                
        }else if(week == 5){
            date1 = LocalDate.now().minusDays(10);
            date2 = LocalDate.now().minusDays(4);                
        }else if(week == 6){
            date1 = LocalDate.now().minusDays(11);
            date2 = LocalDate.now().minusDays(5);                
        }else if(week == 7){
            date1 = LocalDate.now().minusDays(12);
            date2 = LocalDate.now().minusDays(6);                
        }
        System.out.println("jour de la semaine passée1: "+ date1.getDayOfWeek() + date1.getDayOfMonth());
        System.out.println("jour de la semaine^passée7: "+ date2.getDayOfWeek() + date2.getDayOfMonth());

        List<JSONObject> c = caisseRepository.range(date1, date2);
    return new ResponseEntity<>(c, HttpStatus.OK);
            
    }    
    
    @GetMapping("/thisMonth")
	public ResponseEntity<?> thisMonth(){
            Calendar cal = Calendar.getInstance();
            cal.setFirstDayOfWeek(0);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            if((month+1) < 10){
                mts = String.valueOf(year)+"/0"+ String.valueOf(month+1);
            System.out.println("ce mois: "+ mts);
            }else{
                mts = String.valueOf(year)+"/"+ String.valueOf(month+1);
            }            
            
            List<JSONObject> c = caisseRepository.month(mts);
    return new ResponseEntity<>(c, HttpStatus.OK);
	}
        
    @GetMapping("/lastMonth")
    public ResponseEntity<?> lastMonth(){
            Calendar cal = Calendar.getInstance();
            cal.setFirstDayOfWeek(0);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            if((month + 1)< 10){
                if(month == 0){
                    mts = String.valueOf(year - 1)+"/12";
                }else{
                    mts = String.valueOf(year)+"/0"+ String.valueOf(month);
                }                
            }else{
                if((month+1) == 10){
                    mts = String.valueOf(year)+"/09";
                }else{
                    mts = String.valueOf(year)+"/"+ String.valueOf(month);
                }
//                mts = String.valueOf(year)+"/"+ String.valueOf(month);
            }
            System.out.println("mois passé: "+ mts);
//		return panneRepository.MonthPannes(mts);
                List<JSONObject> c = caisseRepository.month(mts);
    return new ResponseEntity<>(c, HttpStatus.OK);
	}
    
    @PostMapping()
    public ResponseEntity<?> createInstance(@RequestBody Caisse caisse) { 
        LocalTime time1 = LocalTime.now();
        LocalTime time2 = LocalTime.of(17, 30, 00);
        LocalTime time3 = LocalTime.of(8, 00, 00);
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(0);
        int week = cal.get(Calendar.DAY_OF_WEEK);
        System.out.println("jour de la semaine: "+week);
            
        if(week == 1 || week == 7){
            return new ResponseEntity<Object>(new ResponseMessage("Ceci n'est pas un jour Ouvrable"),
                  HttpStatus.BAD_REQUEST);
        }
            
        if(time1.isAfter(time3) && time1.isBefore(time2)){
            System.out.println("heure : "+time1);
            
        Caisse c = caisseRepository.findFirstByOrderByIdDesc();   
        
        Notifications notifications = new Notifications();
        notifications.setDate(LocalDate.now()); 
        
        if(c != null){ 
            if(caisse.getEncaissement() != 0 && caisse.getDecaissement() != 0){
                return new ResponseEntity<Object>(new ResponseMessage("Opération impossible"),
                  HttpStatus.BAD_REQUEST);
            }
            System.err.println("etat de la caisse : " + c.getSolde()+ "Fcfa");
            int sol = 0;
            Solde s = new Solde();
            s.setDate(LocalDate.now());
            s.setDate_modification(new Date());
            if(caisse.getEncaissement() > 0){
                sol = c.getSolde() + caisse.getEncaissement();
                notifications.setDescription("Ajout de "+caisse.getEncaissement()+ " Fcfa dans la caisse");
                caisse.setSolde(sol);
//                s.setSolde(sol);
                if(!soldeRepository.existsByDate(LocalDate.now())){
                    s.setSolde(sol);
                soldeRepository.save(s);
                }else{
                    Solde ss = soldeRepository.findFirstByOrderByIdDesc();
                    int fin = ss.getSolde() + caisse.getEncaissement();
                    updateSolde(LocalDate.now(), fin);
//                    soldeRepository.update(sol, new Date(), s.getDate());
                }
            }else {
                sol = c.getSolde() - caisse.getDecaissement();
                if(sol < 0){
                    return new ResponseEntity<Object>(new ResponseMessage("La caisse ne dispose pas assez de fonds pour retirer ce montant "+ caisse.getDecaissement()+ " Fcfa"),
                      HttpStatus.BAD_REQUEST);
                }             
                notifications.setDescription("Retrait de "+caisse.getDecaissement()+" Fcfa de la caisse");
//                s.setSolde(sol);
                if(!soldeRepository.existsByDate(LocalDate.now())){
                    s.setSolde(sol);
                soldeRepository.save(s);
                }else{
                    Solde ss = soldeRepository.findFirstByOrderByIdDesc();
                    int fin = ss.getSolde() - caisse.getDecaissement();
                    updateSolde(LocalDate.now(), fin);
//                    soldeRepository.update(sol, new Date(), s.getDate());
                }
            }        
            solde = sol;
            caisse.setSolde(solde);
        } else { 
            if(caisse.getEncaissement() != 0 && caisse.getDecaissement() != 0){
                return new ResponseEntity<Object>(new ResponseMessage("Opération impossible"),
                  HttpStatus.BAD_REQUEST);
            }            
            if(caisse.getDecaissement() != 0){
                return new ResponseEntity<Object>(new ResponseMessage("La caisse ne dispose pas assez de fonds pour retirer ce montant "+ caisse.getDecaissement()+ " Fcfa"),
                  HttpStatus.BAD_REQUEST);
            } 
            if(caisse.getEncaissement() == 0){
                return new ResponseEntity<Object>(new ResponseMessage("L'initialisation de la caisse ne peut se faire avec un montant <= "+ caisse.getDecaissement()+ " Fcfa"),
                  HttpStatus.BAD_REQUEST);
            } 
            caisse.setSolde(caisse.getEncaissement());
            caisse.setDecaissement(0);
            caisse.setEncaissement(caisse.getEncaissement());
        };
        caisse.setDate(LocalDate.now());
        caisse.setDate_creation(new Date());
        caisse.setDate_modification(new Date());
        caisse.setProvisoir(caisse.isProvisoir());
        
        caisseRepository.save(caisse);
        notificationRepository.save(notifications);      
        
        return new ResponseEntity<>(new ResponseMessage("nouveau mouvement dans la caisse"), HttpStatus.CREATED);
        }else{
        return new ResponseEntity<Object>(new ResponseMessage("cette opération ne peut se faire en dehors des heures de travail"),
                  HttpStatus.BAD_REQUEST);
        }
    
    }
    
    @GetMapping("/last30days")
    public ResponseEntity<?> countAll(){
        LocalDate date1 = LocalDate.now().minusDays(30);
        LocalDate date2 = LocalDate.now();
        System.out.println("date1: "+date1);
        System.out.println("date2: "+date2);
        List<JSONObject> stats = caisseRepository.last30DaysGraphs(date1, date2);
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
    
    @GetMapping("/section")
    public ResponseEntity<?> groupBySection(){
        LocalDate date1 = LocalDate.now().minusDays(30);
        LocalDate date2 = LocalDate.now();
        System.out.println("date1: "+date1);
        System.out.println("date2: "+date2);
        List<JSONObject> stats = caisseRepository.sectionGraphs(date1, date2);
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
    
    @GetMapping("/solde")
    public ResponseEntity<?> solde(){
        Caisse stats = caisseRepository.findFirstByOrderByIdDesc();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> showInstance(@PathVariable Long id){
        Caisse stats = caisseRepository.findById(id).get();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
    
    @GetMapping("/provisoir")
    public ResponseEntity<?> showInstanceProvisoir(){
        List<Caisse> stats = caisseRepository.findByProvisoirTrue();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCaisse(@PathVariable Long id, @RequestBody Caisse caisse){
        Caisse c = caisseRepository.findById(id).get();        
        List<JSONObject> caisses = caisseRepository.listForUpdate(id);
        List<Caisse> caisses2 = caisseRepository.listForUpdate2(id);
        LocalTime time1 = LocalTime.now();
        LocalTime time2 = LocalTime.of(17, 30, 00);
        LocalTime time3 = LocalTime.of(8, 00, 00);
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(0);
        int week = cal.get(Calendar.DAY_OF_WEEK);
        System.out.println("jour de la semaine: "+week);
            
        if(week == 1 || week == 7){
            return new ResponseEntity<Object>(new ResponseMessage("Ceci n'est pas un jour Ouvrable"),
                  HttpStatus.BAD_REQUEST);
        }
        if((time1.isAfter(time2) && time1.isBefore(time3))){
            System.err.println("heure " +time1);
            return new ResponseEntity<Object>(new ResponseMessage("modification impossible en dehors des heures de travail"),
                  HttpStatus.BAD_REQUEST);
        }
        if(!c.getDate().equals(LocalDate.now())){
            System.err.println("date instance " +c.getDate());
            System.err.println("date du jour " +LocalDate.now());
            return new ResponseEntity<Object>(new ResponseMessage("Cette instance ne peut plus être modifiée"),
                  HttpStatus.BAD_REQUEST);
        }
            
            if((c.getDecaissement() == caisse.getDecaissement()) && (c.getEncaissement() == caisse.getEncaissement())){
                
                c.setCc(caisse.getCc());
//                c.setDate(LocalDate.now());
                c.setMouvement(caisse.getMouvement());
                c.setPiece(caisse.getPiece());
                c.setProvisoir(caisse.isProvisoir());
                c.setDate_modification(new Date());
                caisseRepository.save(c);
                return new ResponseEntity<>(c, HttpStatus.OK);
            }
            
            if((c.getDecaissement() != caisse.getDecaissement()) && (c.getEncaissement() == caisse.getEncaissement())){
                
                c.setCc(caisse.getCc());
                c.setMouvement(caisse.getMouvement());
                c.setPiece(caisse.getPiece());
                c.setProvisoir(caisse.isProvisoir());               
                
                int ecart = caisse.getDecaissement() - c.getDecaissement();
                System.err.println("ecart "+ecart);
                Solde ss = soldeRepository.findFirstByOrderByIdDesc();
                if(ecart < 0){
                    int fin = ss.getSolde() + (ecart *-1);
                    System.err.println("fin 1: "+fin);
                    updateSolde(LocalDate.now(), fin);
                }else{
                    int fin = ss.getSolde() - ecart;
                    System.err.println("fin 2: "+fin);
                    updateSolde(LocalDate.now(), fin);
                }
                
                if(caisses.size() > 0){
                    for(int i =0; i< caisses.size(); i++){
                        if(ecart < 0){
                           int test = caisses.get(i).getAsNumber("solde").intValue() + (ecart *-1);
                           System.err.println("autres valeurs "+test);
                           Long ids = caisses.get(i).getAsNumber("id").longValue();
                           updateMultiSolde(ids, test); 
                           
//                           Solde ss = soldeRepository.findFirstByOrderByIdDesc();
//                           int fin = ss.getSolde() + ecart *(-1);
//                           updateSolde(LocalDate.now(), fin);
                        }else{
                            int test = caisses.get(i).getAsNumber("solde").intValue() - ecart;
                            Long ids = caisses.get(i).getAsNumber("id").longValue();
                            updateMultiSolde(ids, test);
//                            Solde ss = soldeRepository.findFirstByOrderByIdDesc();
//                            int fin = ss.getSolde() - ecart;
//                            updateSolde(LocalDate.now(), fin);
                        }

                    }                    
                }
                c.setDecaissement(caisse.getDecaissement());
                Caisse n = caisses2.get(0);
                c.setSolde(n.getSolde() - caisse.getDecaissement());
                c.setDate_modification(new Date());
                caisseRepository.save(c);
                return new ResponseEntity<>(c, HttpStatus.OK);
            }
            
            
            
            if((c.getDecaissement() == caisse.getDecaissement()) && (c.getEncaissement() != caisse.getEncaissement())){
                
                c.setCc(caisse.getCc());
                c.setMouvement(caisse.getMouvement());
                c.setPiece(caisse.getPiece());
                c.setProvisoir(caisse.isProvisoir());               
                
                int ecart = caisse.getEncaissement() - c.getEncaissement();
                System.err.println("ecart encaissement "+ecart);
                Solde ss = soldeRepository.findFirstByOrderByIdDesc();
                if(ecart < 0){
                    int fin = ss.getSolde() + (ecart);
                    System.err.println("fin 1: "+fin);
                    updateSolde(LocalDate.now(), fin);
                }else{
                    int fin = ss.getSolde() + ecart;
                    System.err.println("fin 2: "+fin);
                    updateSolde(LocalDate.now(), fin);
                }
                if(caisses.size() > 0){
                    for(int i =0; i< caisses.size(); i++){
                        if(ecart < 0){
                           int test = caisses.get(i).getAsNumber("solde").intValue() + (ecart);
                           System.err.println("autres valeurs "+test);
                           Long ids = caisses.get(i).getAsNumber("id").longValue();
                           updateMultiSolde(ids, test); 
                           
//                           Solde ss = soldeRepository.findFirstByOrderByIdDesc();
//                           int fin = ss.getSolde() + ecart;
//                            System.err.println("fin 1: "+fin);
//                           updateSolde(LocalDate.now(), fin);
                        }else{
                            int test = caisses.get(i).getAsNumber("solde").intValue() + ecart;
                            Long ids = caisses.get(i).getAsNumber("id").longValue();
                            updateMultiSolde(ids, test);
//                            Solde ss = soldeRepository.findFirstByOrderByIdDesc();
//                            int fin = ss.getSolde() + ecart;
//                            System.err.println("fin 2 "+ fin);
//                            updateSolde(LocalDate.now(), fin);
                        }

                    }                    
                }
                c.setEncaissement(caisse.getEncaissement());
                Caisse n = caisses2.get(0);
                c.setSolde(n.getSolde() + caisse.getEncaissement());
                c.setDate_modification(new Date());
                caisseRepository.save(c);
                return new ResponseEntity<>(c, HttpStatus.OK);
            }
            
            
            
            if((c.getDecaissement() != caisse.getDecaissement()) && (c.getEncaissement() != caisse.getEncaissement())){
                System.err.println("essayons la boucle");
                c.setCc(caisse.getCc());
                c.setMouvement(caisse.getMouvement());
                c.setPiece(caisse.getPiece());
                c.setProvisoir(caisse.isProvisoir());     
                
                if(caisse.getDecaissement() != 0){
                    int ecart = caisse.getDecaissement() - c.getDecaissement();
                    System.err.println("ecart "+ecart);
                    Solde ss = soldeRepository.findFirstByOrderByIdDesc();
                    if(ecart < 0){
                        int fin = ss.getSolde() + (ecart *-1) + c.getEncaissement();
                        System.err.println("fin 1: "+fin);
                        updateSolde(LocalDate.now(), fin);
                    }else{
                        int fin = ss.getSolde() - ecart  - c.getEncaissement();
                        System.err.println("fin 2: "+fin);
                        updateSolde(LocalDate.now(), fin);
                    }

                    if(caisses.size() > 0){
                        for(int i =0; i< caisses.size(); i++){
                            if(ecart < 0){
                               int test = caisses.get(i).getAsNumber("solde").intValue() + (ecart *-1) + c.getEncaissement();
                               System.err.println("autres valeurs "+test);
                               Long ids = caisses.get(i).getAsNumber("id").longValue();
                               updateMultiSolde(ids, test); 
                            }else{
                                int test = caisses.get(i).getAsNumber("solde").intValue() - ecart - c.getEncaissement();
                                Long ids = caisses.get(i).getAsNumber("id").longValue();
                                updateMultiSolde(ids, test);
                            }
                        }                    
                    }
                    Caisse n = caisses2.get(0);
                    c.setSolde(n.getSolde() - caisse.getDecaissement());
                }else{
                    int ecart = caisse.getEncaissement() - c.getEncaissement();
                System.err.println("ecart encaissement "+ecart);
                Solde ss = soldeRepository.findFirstByOrderByIdDesc();
                if(ecart < 0){
                    int fin = ss.getSolde() + (ecart) + c.getDecaissement();
                    System.err.println("fin 1: "+fin);
                    updateSolde(LocalDate.now(), fin);
                }else{
                    int fin = ss.getSolde() + ecart + c.getDecaissement();
                    System.err.println("fin 2: "+fin);
                    updateSolde(LocalDate.now(), fin);
                }
                if(caisses.size() > 0){
                    for(int i =0; i< caisses.size(); i++){
                        if(ecart < 0){
                           int test = caisses.get(i).getAsNumber("solde").intValue() + (ecart) + c.getDecaissement();
                           System.err.println("autres valeurs "+test);
                           Long ids = caisses.get(i).getAsNumber("id").longValue();
                           updateMultiSolde(ids, test);                            
                        }else{
                            int test = caisses.get(i).getAsNumber("solde").intValue() + ecart + c.getDecaissement();
                            Long ids = caisses.get(i).getAsNumber("id").longValue();
                            updateMultiSolde(ids, test);
                        }
                    }                    
                }
                Caisse n = caisses2.get(0);
                c.setSolde(n.getSolde() + caisse.getEncaissement());
                }                      
                
                c.setDate_modification(new Date());                
                c.setDecaissement(caisse.getDecaissement());
                c.setEncaissement(caisse.getEncaissement());
                caisseRepository.save(c);
                return new ResponseEntity<>(c, HttpStatus.OK);
            }
            
            
            return new ResponseEntity<>("modification", HttpStatus.OK);
        
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSolde(@PathVariable LocalDate id, int sold){
        Solde solde = soldeRepository.findByDate(id);
        solde.setSolde(sold);
        solde.setDate_modification(new Date());
        soldeRepository.save(solde);
        return new ResponseEntity<>(solde, HttpStatus.OK);
    }
    
    @PutMapping("/updateSoldeInstance/{id}")
    public ResponseEntity<?> updateMultiSolde(@PathVariable Long id, int sold){
        Caisse solde = caisseRepository.findById(id).get();
        solde.setSolde(sold);
//        solde.setDate_modification(new Date());
        caisseRepository.save(solde);
        return new ResponseEntity<>(solde, HttpStatus.OK);
    }
    
}
