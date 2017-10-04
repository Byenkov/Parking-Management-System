package com.parking.logic;

import javax.ejb.Local;
import javax.jms.JMSException;

@Local
public interface CommunicateSender {
  void sendCommunicate(Notification message) throws JMSException;
}