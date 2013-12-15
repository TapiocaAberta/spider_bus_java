package org.spider.bus.model.horario;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "sabados")
@XmlRootElement
public class AosSabados extends HorarioGenerico<AosSabados> {
	private static final long serialVersionUID = 1L;
}
