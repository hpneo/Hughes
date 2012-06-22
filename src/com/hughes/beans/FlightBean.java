package com.hughes.beans;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FlightBean {

  public static void search(String flight_type,
      Integer origin_id,
      Integer destination_id,
      Date output_date,
      Date arrival_date,
      String flight_class,
      Integer passages_count){
    
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    String conditions = "";
    
    entity_manager.createQuery("SELECT f FROM Flight f WHERE "+conditions).getResultList();
  }

}
