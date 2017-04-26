package com.ejb;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: MessageConsumer
 */
@MessageDriven(mappedName="jms/newone")
public class MessageConsumer implements MessageListener {

	/**
	 * Default constructor. 
	 */
	public MessageConsumer() {
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		try {
			TextMessage msg = (TextMessage) message;
			System.out.println("Received: "+msg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}


