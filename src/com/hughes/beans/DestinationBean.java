package com.hughes.beans;

import java.util.*;

import javax.persistence.*;

import com.hughes.models.*;

public class DestinationBean {

  @SuppressWarnings("unchecked")
  public static List<Destination> all(){
	EntityManagerFactory factory = null;
	EntityManager entity_manager = null;
	
	factory = Persistence.createEntityManagerFactory("Flights");
	entity_manager = factory.createEntityManager();
	
	return (List<Destination>) entity_manager.createQuery("SELECT d FROM Destination d").getResultList();
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
}
