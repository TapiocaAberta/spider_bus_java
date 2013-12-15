package org.spider.bus.model.horario;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "domingo_feriado")
@XmlRootElement
public class DomingosEFeriados extends HorarioGenerico<DomingosEFeriados> {
	private static final long serialVersionUID = 1L;
}
