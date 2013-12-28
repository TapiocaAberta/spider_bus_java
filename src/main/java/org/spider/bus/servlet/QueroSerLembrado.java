package org.spider.bus.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.spider.bus.business.usuario.UsuarioBusiness;
import org.spider.bus.pojo.usuario.UsuarioPojo;

@WebServlet("/queroSerLembrado")
public class QueroSerLembrado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	UsuarioBusiness business;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		UsuarioPojo usuarioPojo = new UsuarioPojo(nome, email);

		business.salvar(usuarioPojo);

		response.sendRedirect("index.html");
	}
}
