package com.parking.alert;

import javax.ejb.ActivationConfigProperty;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.parking.logic.Notification;

import javax.ejb.*;

@MessageDriven(                                     // Message-driven bean (MDB)
  activationConfig = { @ActivationConfigProperty(   // activation configuration (more)
    propertyName = "destinationType", 
    propertyValue = "javax.jms.Queue"
    ),
    @ActivationConfigProperty(
    propertyName = "destination", 
    propertyValue = "java:/jms/queue/CommunicateQueue"         // destination's JNDI name
  ) })
public class AlertMDB implements MessageListener {
	
  @EJB
  private Notifications notifications;

  public void onMessage(Message message) {          // process message here
	  System.out.println("xxx");
	  if (message instanceof ObjectMessage) {
    	  Object object;
		try {
			object = ((ObjectMessage) message).getObject();
	    	  System.out.println("Sending info on sector: " + ((Notification) object).getZone());
	    	  notifications.addNotification((Notification) object);
		} catch (JMSException e) {
			e.printStackTrace();
		}
      }
  }
}