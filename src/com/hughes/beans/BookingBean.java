package com.hughes.beans;

import java.util.List;

import javax.persistence.*;

import com.hughes.models.*;

public class BookingBean {

  public static List<Booking> all(){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    return entity_manager.createQuery("SELECT b FROM Booking b", Booking.class).getResultList();
  }

  public static void save(Booking booking){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();

    entity_manager.getTransaction().begin();
    entity_manager.persist(booking);
    entity_manager.getTransaction().commit();
    
    Flight flight = booking.getFlight();
    
    int available_seats = flight.getAvailableSeats() - 1;
    
    flight.setAvailableSeats(available_seats);
    
    FlightBean.update(flight);
  }
  
}
