package org.spider.bus.model.horario;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "segunda_sexta")
@XmlRootElement
public class SegundaASexta extends HorarioGenerico<SegundaASexta> {
	private static final long serialVersionUID = 1L;
}
