package com.hughes.beans;

import java.util.*;

import javax.persistence.*;

import com.hughes.models.*;

public class BookingBean {

  public static void save(Booking booking){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();

    entity_manager.getTransaction().begin();
    entity_manager.persist(booking);
    entity_manager.getTransaction().commit();
  }
  
}
