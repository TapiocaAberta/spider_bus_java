package org.spider.bus.pojo.usuario;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.utils.IndexDirection;
import org.spider.bus.pojo.PojoAbstrato;

@Entity(value = "quero_ser_lembrado", noClassnameStored = true)
public class UsuarioPojo extends PojoAbstrato {

	public UsuarioPojo() {
	}

	public UsuarioPojo(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

	private static final long serialVersionUID = 1L;

	private String nome;

	@Indexed(value = IndexDirection.ASC, name = "email", unique = true)
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
