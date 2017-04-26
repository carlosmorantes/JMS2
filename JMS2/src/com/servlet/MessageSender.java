package com.servlet;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sendMessage")
public class MessageSender extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Context ctx = new InitialContext();

			Queue queue = (Queue) ctx.lookup("jms/newone");

			ConnectionFactory cf = (ConnectionFactory) ctx.lookup("jms/ConnFact");

			Connection connection = cf.createConnection();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			MessageProducer messageProducer = session.createProducer(queue);

			TextMessage message = session.createTextMessage("Send message to jms/message from servlet: "+new java.util.Date());

			messageProducer.send(message);

			resp.getWriter().println("Message sent");

			connection.close();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
