package org.spider.bus.model.horario;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "segunda_sabado")
@XmlRootElement
public class SegundaASabado extends HorarioGenerico<SegundaASabado> {
	private static final long serialVersionUID = 1L;
}
