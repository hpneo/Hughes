package com.hughes.beans;

import java.text.*;
import java.util.*;

import com.hughes.models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class FlightBean {

  public static List<Flight> search(String flight_type,
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
    
    SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");

    System.out.println(date_format.format(output_date));
    System.out.println(date_format.format(arrival_date));

    conditions = " (r.origin.id = " + origin_id + " AND r.destination.id = " + destination_id + " AND f.outputDate = '" + date_format.format(output_date) + "')";
    
    if(flight_type == "bi"){
      conditions += " OR (r.origin.id = " + destination_id + " AND r.destination.id = " + origin_id + " AND f.outputDate = '" + date_format.format(arrival_date) + "')";
    }

    conditions += " AND f.availableSeats >= " + passages_count;
    conditions += " AND f.flightClass = '" + flight_class + "'";
    
    TypedQuery<Flight> query = entity_manager.createQuery("SELECT f FROM Flight f INNER JOIN f.frequency fr INNER JOIN fr.route r WHERE " + conditions, Flight.class);
    
    return query .getResultList();
  }

}
