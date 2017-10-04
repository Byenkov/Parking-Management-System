package com.parking.logic;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

@Stateless
public class CommunicateSenderBean implements CommunicateSender {

  @Resource(mappedName="java:/ConnectionFactory")
  private ConnectionFactory factory;

  @Resource(mappedName="java:/jms/queue/CommunicateQueue")
  private Queue target;

  // Sends the given string as text message:
  public void sendCommunicate(Notification message) throws JMSException {
    Connection con = factory.createConnection();
    try {
      Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
      MessageProducer producer = session.createProducer(target);
      producer.send(session.createObjectMessage(message));
    }
    finally {
      con.close();
    }
  }
}