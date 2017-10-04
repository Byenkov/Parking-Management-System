package com.parking.services;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SlotService
{
  @WebMethod
  public boolean setParkingSlotFree(int paramInt);
  
  @WebMethod
  public boolean setParkingSlotTaken(int paramInt);
}