package org.spider.bus.model.horario;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.MappedSuperclass;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.spider.bus.model.EntidadeAbstrata;

@MappedSuperclass
@SuppressWarnings("unchecked")
@Indexed
public class HorarioGenerico<T> extends EntidadeAbstrata {

	private static final long serialVersionUID = 1L;

	@Field
	private String horario;

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public List<T> converteParaLista(String horario) {
		List<T> horarios = new ArrayList<T>();
		Matcher horariosMatcher = Pattern.compile("((\\d{2})[:](\\d{2})[(]\\d[)]|(\\d{2})[:](\\d{2}))").matcher(horario);

		while ( horariosMatcher.find() ) {
			this.setHorario(horariosMatcher.group());
			horarios.add((T) this);
		}

		return horarios;
	}
}
