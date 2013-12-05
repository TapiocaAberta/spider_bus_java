package org.spider.bus.sondas;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class RasparListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			System.out.println("######### LISTENER ######### ");
			RasparAgendador.inicia();
		} catch ( Exception e ) {
			System.out.println(e.getMessage());
		}
	}

}
