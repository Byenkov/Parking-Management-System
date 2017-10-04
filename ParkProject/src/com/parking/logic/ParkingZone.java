package com.parking.logic;

import java.util.ArrayList;

import javax.ejb.Remote;

@Remote
public interface ParkingZone {
	public ArrayList<ParkingSlot> getParkingSlots(int zone);
	public Report getReport();
}