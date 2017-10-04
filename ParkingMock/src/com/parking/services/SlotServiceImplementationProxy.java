package com.parking.services;

public class SlotServiceImplementationProxy implements com.parking.services.SlotServiceImplementation {
  private String _endpoint = null;
  private com.parking.services.SlotServiceImplementation slotServiceImplementation = null;
  
  public SlotServiceImplementationProxy() {
    _initSlotServiceImplementationProxy();
  }
  
  public SlotServiceImplementationProxy(String endpoint) {
    _endpoint = endpoint;
    _initSlotServiceImplementationProxy();
  }
  
  private void _initSlotServiceImplementationProxy() {
    try {
      slotServiceImplementation = (new com.parking.services.SlotServiceImplementationServiceLocator()).getSlotServiceImplementation();
      if (slotServiceImplementation != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)slotServiceImplementation)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)slotServiceImplementation)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (slotServiceImplementation != null)
      ((javax.xml.rpc.Stub)slotServiceImplementation)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.parking.services.SlotServiceImplementation getSlotServiceImplementation() {
    if (slotServiceImplementation == null)
      _initSlotServiceImplementationProxy();
    return slotServiceImplementation;
  }
  
  public boolean setParkingSlotTaken(int parkingSlotID) throws java.rmi.RemoteException{
    if (slotServiceImplementation == null)
      _initSlotServiceImplementationProxy();
    return slotServiceImplementation.setParkingSlotTaken(parkingSlotID);
  }
  
  public boolean setParkingSlotFree(int parkingSlotID) throws java.rmi.RemoteException{
    if (slotServiceImplementation == null)
      _initSlotServiceImplementationProxy();
    return slotServiceImplementation.setParkingSlotFree(parkingSlotID);
  }
  
  
}