package org.spider.bus.model.horario;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "segunda_domingo")
@XmlRootElement
public class SegundaADomingo extends HorarioGenerico<SegundaADomingo> {
	private static final long serialVersionUID = 1L;
}
