/**
 * SlotServiceImplementation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.parking.services;

public interface SlotServiceImplementation extends java.rmi.Remote {
    public boolean setParkingSlotTaken(int parkingSlotID) throws java.rmi.RemoteException;
    public boolean setParkingSlotFree(int parkingSlotID) throws java.rmi.RemoteException;
}
