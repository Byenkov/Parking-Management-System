package com.parking.logic;

import java.util.ArrayList;

import javax.ejb.Stateless;

@Stateless
public class ParkingZoneBean implements ParkingZone
{
  @javax.ejb.EJB
  Manager manager;
  
  public ParkingZoneBean() {}
  
  public ArrayList<ParkingSlot> getParkingSlots(int zone)
  {
    ArrayList<ParkingSlot> parkingSlots = manager.getSlots();
    ArrayList<ParkingSlot> myParkingSlots = new ArrayList();
    for (int i = 0; i < parkingSlots.size(); i++) {
      if (((ParkingSlot)parkingSlots.get(i)).getSector() == zone) myParkingSlots.add((ParkingSlot)parkingSlots.get(i));
    }
    return myParkingSlots;
  }
  
  public Report getReport(){
	  return manager.getReport();
  }
}