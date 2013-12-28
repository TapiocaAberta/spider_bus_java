package org.spider.bus.pojo;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.DateTime;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

public class PojoAbstrato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Property("id")
	@JsonIgnore
	protected ObjectId id;

	@JsonIgnore
	private String dataCriacao = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");

	@JsonIgnore
	private String dataAlteracao = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");

	@JsonIgnore
	private boolean ativo = true;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(String dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

}
