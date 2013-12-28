package org.spider.bus.business.usuario;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.spider.bus.dao.usuario.UsuarioDao;
import org.spider.bus.pojo.usuario.UsuarioPojo;

public class UsuarioBusiness {

	@Inject
	UsuarioDao dao;

	@Inject
	protected Logger log;

	public void salvar(UsuarioPojo usuario) {
		try {
			dao.salvar(usuario);
			log.info("Usuario Salvo com sucesso");
		} catch ( Exception e ) {
			e.printStackTrace();
			log.info("Erro ao salvar usuario");
		}
	}

}
