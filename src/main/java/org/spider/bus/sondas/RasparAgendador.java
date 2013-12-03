package org.spider.bus.sondas;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class RasparAgendador {

	static final String SEGUNDO = "00";
	static final String MINUTO = "00";
	static final String HORA = "21";
	static final String DIA_MES = "02";
	static final String MES = "*"; // * = TODOS MESES
	static final String DIA_SEMANA = "?"; // ? = NÃO ESPECÍFICO

	public static void inicia() throws Exception {
		JobDetail job = JobBuilder.newJob(Raspar.class).withIdentity("tarefaRasparDados", "grupo1").build();
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("gatilhoRaspar", "group1")
				.withSchedule(CronScheduleBuilder.cronSchedule(SEGUNDO + " " + MINUTO + " " + HORA + " " + DIA_MES + " " + MES + " " + DIA_SEMANA)).build();
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);

	}
}
