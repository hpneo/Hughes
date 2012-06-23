package com.hughes.beans;

import java.util.*;
import javax.persistence.*;

import com.hughes.models.*;

public class OriginBean {

	@SuppressWarnings("unchecked")
	public static List<Origin> all(){
	  EntityManagerFactory factory = null;
	  EntityManager entity_manager = null;
	  
	  factory = Persistence.createEntityManagerFactory("Flights");
	  entity_manager = factory.createEntityManager();
	  
	  return (List<Origin>) entity_manager.createQuery("SELECT o FROM Origin o").getResultList();
	}
	
}
