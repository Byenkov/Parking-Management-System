package com.parking.crud;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class DAO
{
  protected SessionFactory sessionFactory;
  
  public DAO() {}
  
  protected void setup()
  {
    StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
      .configure()
      .build();
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    } catch (Exception ex) {
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }
  
  protected void exit() {
    sessionFactory.close();
  }
  
  public void create(int zone, String street) {
    setup();
    ParkingSlotPOJO parkingSlot = new ParkingSlotPOJO();
    
    parkingSlot.setStreet(street);
    parkingSlot.setZone(zone);
    
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    session.save(parkingSlot);
    
    session.getTransaction().commit();
    session.close();
    exit();
  }
  
  public List<ParkingSlotPOJO> read() {
    try {
      setup();
      Session session = sessionFactory.openSession();
      List<ParkingSlotPOJO> parkingSlotList = session.createCriteria(ParkingSlotPOJO.class).list();
      
      session.close();
      exit();
      return parkingSlotList;
    }
    finally {
      sessionFactory.close();
    }
  }
  
  public void update(int id, int zone, String street) {
    setup();
    
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    
    ParkingSlotPOJO parkingSlot = (ParkingSlotPOJO)session.find(ParkingSlotPOJO.class, Integer.valueOf(id));
    
    parkingSlot.setZone(zone);
    parkingSlot.setStreet(street);
    session.update(parkingSlot);
    
    session.getTransaction().commit();
    session.close();
    exit();
  }
  
  public void delete(int id) {
    setup();
    ParkingSlotPOJO parkingSlot = new ParkingSlotPOJO();
    parkingSlot.setId(id);
    
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    
    session.delete(parkingSlot);
    
    session.getTransaction().commit();
    session.close();
    exit();
  }
}