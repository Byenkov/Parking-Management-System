package com.parking.services;

import com.parking.logic.Manager;
import java.io.PrintStream;
import java.io.Serializable;

import javax.ejb.Stateless;
import javax.jws.WebService;

@WebService(endpointInterface="com.parking.services.SlotService")
@Stateless
public class SlotServiceImplementation implements SlotService, Serializable
{
  private static final long serialVersionUID = 1L;
  @javax.ejb.EJB
  Manager manager;
  
  public SlotServiceImplementation() {}
  
  public boolean setParkingSlotFree(int parkingSlotID)
  {
    System.out.println("Parking slot free: " + parkingSlotID);
    manager.carLeft(parkingSlotID);
    return false;
  }
  
  public boolean setParkingSlotTaken(int parkingSlotID)
  {
    System.out.println("Parking slot taken: " + parkingSlotID);
    manager.carDetected(parkingSlotID);
    return true;
  }
}