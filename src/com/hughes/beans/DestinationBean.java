package com.hughes.beans;

import java.util.*;

import javax.persistence.*;

import com.hughes.models.*;

public class DestinationBean {

  public static List<Destination> all(){
	EntityManagerFactory factory = null;
	EntityManager entity_manager = null;
	
	factory = Persistence.createEntityManagerFactory("Flights");
	entity_manager = factory.createEntityManager();
	
	return entity_manager.createQuery("SELECT d FROM Destination d", Destination.class).getResultList();
  }
  
  public static Destination get(Integer id){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    try {
      return entity_manager.find(Destination.class, id);
    } catch (Exception e) {
      return null;
    }
  }
  
  public static void save(Destination destination){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();

    entity_manager.getTransaction().begin();
    entity_manager.persist(destination);
    entity_manager.getTransaction().commit();
  }
  
  public static boolean update(Destination destination){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    try {
      Destination destination_to_update = entity_manager.find(Destination.class, destination.getId());
      
      entity_manager.getTransaction().begin();
      destination_to_update.setName(destination.getName());
      destination_to_update.setLat(destination.getLat());
      destination_to_update.setLng(destination.getLng());
      entity_manager.getTransaction().commit();
      
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
}
