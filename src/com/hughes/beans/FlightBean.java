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

  public static Flight get(Integer id){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    try {
      return entity_manager.find(Flight.class, id);
    } catch (Exception e) {
      return null;
    }
  }
  
  public static Collection<Flight> get(Object[] ids){
    Collection<Flight> collection = new Vector<Flight>();
    for(int i=0; i<ids.length; i++){
      Integer id = Integer.parseInt(ids[i].toString());
      
      collection.add(get(id));
    }
    
    return collection;
  }
  
  public static String getOuputDate(Flight flight){
    SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat time_format = new SimpleDateFormat("hh:mm a");
    
    return date_format.format(flight.getOutputDate()) + " " + time_format.format(flight.getFrequency().getOutput());
  }
  
  public static String getArrivalDate(Flight flight){
    SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat time_format = new SimpleDateFormat("hh:mm a");
    
    Date arrivalDate = new Date();
    Date outputDate = getOutpuDate(flight);
    
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.setTime(outputDate);
    
    calendar.add(Calendar.MINUTE, flight.getFrequency().getDuration());
    arrivalDate = calendar.getTime();
    
    return date_format.format(arrivalDate) + " " + time_format.format(arrivalDate);
  }
  
  private static Date getOutpuDate(Flight flight){
    Date outputDate = new Date();

    GregorianCalendar outputCalendar = new GregorianCalendar();
    outputCalendar.setTime(flight.getOutputDate());
    
    GregorianCalendar outputTimeCalendar = new GregorianCalendar();
    outputTimeCalendar.setTime(flight.getFrequency().getOutput());
    
    outputCalendar.set(Calendar.HOUR_OF_DAY, outputTimeCalendar.get(Calendar.HOUR_OF_DAY));
    outputCalendar.set(Calendar.MINUTE, outputTimeCalendar.get(Calendar.MINUTE));
    
    outputDate = outputCalendar.getTime();
    
    return outputDate;
  }
  
  public static boolean update(Flight flight){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;
    
    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();
    
    try {
      Flight flight_to_update = entity_manager.find(Flight.class, flight.getId());
      
      entity_manager.getTransaction().begin();
      flight_to_update.setCode(flight.getCode());
      flight_to_update.setAvailableSeats(flight.getAvailableSeats());
      flight_to_update.setFlightClass(flight.getFlightClass());
      flight_to_update.setOutputDate(flight.getOutputDate());
      flight_to_update.setPrice(flight.getPrice());
      entity_manager.getTransaction().commit();
      
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
