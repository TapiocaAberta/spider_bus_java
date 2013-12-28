package org.spider.bus.pojo;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity(value = "conducao", noClassnameStored = true)
@XmlRootElement
public class HoraItinerarioOnibus extends PojoAbstrato {

	private static final long serialVersionUID = 1L;

	public HoraItinerarioOnibus() {
	}

	public HoraItinerarioOnibus(String numero, String nome, String sentido, String itinerario, Horario horarios, String tipo) {
		this.numero = numero;
		this.nome = nome;
		this.sentido = sentido;
		this.itinerario = itinerario;
		this.horarios = horarios;
		this.tipo = tipo;
	}

	@JsonIgnore
	private String tipo;

	private String numero;
	private String nome;
	private String sentido;
	private String itinerario;

	@Reference("horarios")
	private Horario horarios;

	public String getNumero() {
		return numero;
	}

	public String getNome() {
		return nome;
	}

	public String getSentido() {
		return sentido;
	}

	public String getItinerario() {
		return itinerario;
	}

	public Horario getHorarios() {
		return horarios;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSentido(String sentido) {
		this.sentido = sentido;
	}

	public void setItinerario(String itinerario) {
		this.itinerario = itinerario;
	}

	public void setHorarios(Horario horarios) {
		this.horarios = horarios;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
