package com.parking.crud;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@javax.persistence.Table(name="parkingslots")
@DynamicUpdate
public class ParkingSlotPOJO
{
  private int id;
  private int zone;
  private String street;
  
  public ParkingSlotPOJO() {}
  
  @javax.persistence.Id
  @Column(name="id")
  @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
  public int getId()
  {
    return id;
  }
  
  public void setId(int id) { this.id = id; }
  
  @Column(name="zone")
  public int getZone()
  {
    return zone;
  }
  
  public void setZone(int zone) { this.zone = zone; }
  
  @Column(name="street")
  public String getStreet()
  {
    return street;
  }
  
  public void setStreet(String street) { this.street = street; }
}