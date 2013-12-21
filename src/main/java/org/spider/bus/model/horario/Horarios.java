package org.spider.bus.model.horario;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.spider.bus.model.EntidadeAbstrata;

@Entity(name = "horarios")
@XmlRootElement
public class Horarios extends EntidadeAbstrata {
	private static final long serialVersionUID = 1L;

	public Horarios() {
	}

	public Horarios(List<SegundaASexta> deSegundaSexta, List<SegundaASabado> deSegundaSab, List<SegundaADomingo> deSegundaDom, List<AosSabados> aosSabados,
			List<DomingosEFeriados> domingosEFeriado, String observacao) {
		this.deSegundaSexta = deSegundaSexta;
		this.deSegundaSab = deSegundaSab;
		this.deSegundaDom = deSegundaDom;
		this.aosSabados = aosSabados;
		this.domingosEFeriado = domingosEFeriado;
		this.observacao = observacao;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<SegundaASexta> deSegundaSexta;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<SegundaASabado> deSegundaSab;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<SegundaADomingo> deSegundaDom;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<AosSabados> aosSabados;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<DomingosEFeriados> domingosEFeriado;

	private String observacao;

	public List<SegundaASexta> getDeSegundaSexta() {
		return deSegundaSexta;
	}

	public void setDeSegundaSexta(List<SegundaASexta> deSegundaSexta) {
		this.deSegundaSexta = deSegundaSexta;
	}

	public List<SegundaASabado> getDeSegundaSab() {
		return deSegundaSab;
	}

	public void setDeSegundaSab(List<SegundaASabado> deSegundaSab) {
		this.deSegundaSab = deSegundaSab;
	}

	public List<SegundaADomingo> getDeSegundaDom() {
		return deSegundaDom;
	}

	public void setDeSegundaDom(List<SegundaADomingo> deSegundaDom) {
		this.deSegundaDom = deSegundaDom;
	}

	public List<AosSabados> getAosSabados() {
		return aosSabados;
	}

	public void setAosSabados(List<AosSabados> aosSabados) {
		this.aosSabados = aosSabados;
	}

	public List<DomingosEFeriados> getDomingosEFeriado() {
		return domingosEFeriado;
	}

	public void setDomingosEFeriado(List<DomingosEFeriados> domingosEFeriado) {
		this.domingosEFeriado = domingosEFeriado;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
