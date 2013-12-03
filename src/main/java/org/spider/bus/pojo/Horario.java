package org.spider.bus.pojo;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Horario implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<String> deSegundaSexta;
	private List<String> deSegundaSab;
	private List<String> deSegundaDom;
	private List<String> aosSabados;
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
