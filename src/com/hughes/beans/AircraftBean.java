package com.hughes.beans;

import java.util.List;

import javax.persistence.*;

import com.hughes.models.*;

public class AircraftBean {

  public static List<Aircraft> all(){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    return entity_manager.createQuery("SELECT a FROM Aircraft a", Aircraft.class).getResultList();
  }
  
  public static Aircraft get(Integer id){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    try {
      return entity_manager.find(Aircraft.class, id);
    } catch (Exception e) {
      return null;
    }
  }

}
