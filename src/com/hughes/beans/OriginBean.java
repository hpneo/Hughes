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

  public static Origin get(Integer id){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;

    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();

    try {
      return entity_manager.find(Origin.class, id);
    } catch (Exception e) {
      return null;
    }
  }

  public static void save(Origin origin){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;

    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();

    entity_manager.getTransaction().begin();
    entity_manager.persist(origin);
    entity_manager.getTransaction().commit();
  }

  public static boolean update(Origin origin){
    EntityManagerFactory factory = null;
    EntityManager entity_manager = null;

    factory = Persistence.createEntityManagerFactory("Flights");
    entity_manager = factory.createEntityManager();

    try {
      Origin origin_to_update = entity_manager.find(Origin.class, origin.getId());

      entity_manager.getTransaction().begin();
      origin_to_update.setName(origin.getName());
      origin_to_update.setLat(origin.getLat());
      origin_to_update.setLng(origin.getLng());
      entity_manager.getTransaction().commit();

      return true;
    } catch (Exception e) {
      return false;
    }
  }

}
