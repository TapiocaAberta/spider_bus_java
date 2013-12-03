package org.spider.bus.pojo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.DateTime;

@XmlRootElement
public class HoraItinerarioOnibus implements Serializable {

	private static final long serialVersionUID = 1L;

	public HoraItinerarioOnibus() {
	}

	public HoraItinerarioOnibus(String numero, String nome, String sentido, String itinerario, Horario horarios) {
		this.numero = numero;
		this.nome = nome;
		this.sentido = sentido;
		this.itinerario = itinerario;
		this.horarios = horarios;
	}

	@JsonIgnore
	private ObjectId _id;

	@JsonIgnore
	private DateTime data_criacao;

	@JsonIgnore
	private boolean ativo;

	@JsonIgnore
	private String tipo;

	private String numero;
	private String nome;
	private String sentido;
	private String itinerario;
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

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public DateTime getData_criacao() {
		return data_criacao;
	}

	public void setData_criacao(DateTime data_criacao) {
		this.data_criacao = data_criacao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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
