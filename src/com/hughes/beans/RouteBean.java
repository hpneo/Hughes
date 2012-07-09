package com.hughes.beans;

import java.util.*;

import javax.persistence.*;

import com.hughes.models.*;

public class RouteBean {

  public static List<Route> all(){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    return entity_manager.createQuery("SELECT o FROM Route o", Route.class).getResultList();
  }
  
  public static Route get(Integer id) {
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    try {
      return entity_manager.find(Route.class, id);
    } catch (Exception e) {
      return null;
    }
  }
  
  public static void save(Route route){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();

    entity_manager.getTransaction().begin();
    entity_manager.persist(route);
    entity_manager.getTransaction().commit();
  }
  
  public static boolean update(Route route){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    try {
      Route route_to_update = entity_manager.find(Route.class, route.getId());
      
      entity_manager.getTransaction().begin();
      route_to_update.setOrigin(route.getOrigin());
      route_to_update.setDestination(route.getDestination());
      entity_manager.getTransaction().commit();
      
      return true;
    } catch (Exception e) {
      return false;
    }
  }
  
}
