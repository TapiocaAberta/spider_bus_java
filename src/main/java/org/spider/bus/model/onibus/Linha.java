package org.spider.bus.model.onibus;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.spider.bus.model.EntidadeAbstrata;
import org.spider.bus.model.horario.Horarios;

@Entity(name = "linha")
@XmlRootElement
@Indexed
public class Linha extends EntidadeAbstrata {
	public Linha() {
	}

	public Linha(String tipo, String numero, String nome, String sentido, String itinerario, Horarios horarios) {
		this.tipo = tipo;
		this.numero = numero;
		this.nome = nome;
		this.sentido = sentido;
		this.itinerario = itinerario;
		this.horarios = horarios;
	}

	private static final long serialVersionUID = 1L;

	@Field
	private String tipo;

	@Field
	private String numero;

	@Field
	private String nome;

	@Field
	private String sentido;

	@Field
	private String itinerario;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Horarios horarios;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSentido() {
		return sentido;
	}

	public void setSentido(String sentido) {
		this.sentido = sentido;
	}

	public String getItinerario() {
		return itinerario;
	}

	public void setItinerario(String itinerario) {
		this.itinerario = itinerario;
	}

}
