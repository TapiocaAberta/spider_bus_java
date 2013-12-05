package org.spider.bus.sondas;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;

public class RasparListener implements ServletContextListener {
	
	@Inject
	protected Logger log;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			log.info("######### LISTENER ######### ");
			RasparAgendador.inicia();
		} catch ( Exception e ) {
			log.error(e.getMessage());
		}
	}

}
