package org.spider.bus.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

@Entity(value = "horarios", noClassnameStored = true)
@XmlRootElement
public class Horario extends PojoAbstrato {

	private static final long serialVersionUID = 1L;

	@Embedded("segunda_sexta")
	private List<String> deSegundaSexta;

	@Embedded("segunda_sabado")
	private List<String> deSegundaSab;

	@Embedded("segunda_domingo")
	private List<String> deSegundaDom;

	@Embedded("sabados")
	private List<String> aosSabados;

	@Embedded("domingos_feriados")
	private List<String> domingosEFeriado;

	private String observacao;

	public Horario() {
	}

	public Horario(List<String> deSegundaSexta, List<String> deSegundaSab, List<String> deSegundaDom, List<String> aosSabados, List<String> domingosEFeriado,
			String observacao) {
		this.deSegundaSexta = deSegundaSexta;
		this.deSegundaSab = deSegundaSab;
		this.setDeSegundaDom(deSegundaDom);
		this.aosSabados = aosSabados;
		this.domingosEFeriado = domingosEFeriado;
		this.observacao = observacao;
	}

	public List<String> getDeSegundaSexta() {
		return deSegundaSexta;
	}

	public List<String> getAosSabados() {
		return aosSabados;
	}

	public List<String> getDomingosEFeriado() {
		return domingosEFeriado;
	}

	public String getObservacao() {
		return observacao;
	}

	public List<String> getDeSegundaSab() {
		return deSegundaSab;
	}

	public List<String> getDeSegundaDom() {
		return deSegundaDom;
	}

	public void setDeSegundaDom(List<String> deSegundaDom) {
		this.deSegundaDom = deSegundaDom;
	}

}
