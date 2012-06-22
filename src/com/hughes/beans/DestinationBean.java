package com.hughes.beans;

import java.util.List;

import javax.persistence.*;

import com.hughes.models.*;

public class DestinationBean {

	@SuppressWarnings("unchecked")
	public static List<Destination> all(){
	  EntityManagerFactory factory = null;
	  EntityManager entity_manager = null;
	  
	  factory = Persistence.createEntityManagerFactory("Flights");
	  entity_manager = factory.createEntityManager();
	  
	  return (List<Destination>)entity_manager.createQuery("SELECT d FROM Destination d").getResultList();
	}
}
