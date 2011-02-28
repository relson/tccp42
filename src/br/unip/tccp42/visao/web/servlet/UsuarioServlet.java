package br.unip.tccp42.visao.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;

import br.unip.tccp42.controle.web.helper.Mensagem;
import br.unip.tccp42.modelo.entidade.Usuario;
import br.unip.tccp42.modelo.entidade.dao.UsuarioDao;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class UsuarioServlet extends HttpServlet {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.setHeader("Cache-Control", "no-cache");

		Mensagem mensagem = new Mensagem();

		try {

			Long id = Long.valueOf(req.getParameter("id"));
			String nome = req.getParameter("nome");
			String login = req.getParameter("usuario");
			String senha = req.getParameter("senha");
			Boolean recebeNotificacao = Boolean.valueOf(req
					.getParameter("permiteNotificiacao"));

			UsuarioDao dao = new UsuarioDao();

			Usuario usuario = new Usuario(id, nome, login, senha,
					recebeNotificacao);

			dao.atualizar(usuario);

			//req.getSession().setAttribute("usuario", usuario);

			mensagem.setErro(false);
			mensagem.setDescricao(StringEscapeUtils.escapeHtml("Sucesso!"));
			mensagem.setCorpo(usuario.getLogin());

		} catch (Exception e) {
			mensagem.setErro(true);
			mensagem.setDescricao(StringEscapeUtils.escapeHtml(e.getMessage()));
			mensagem.setCorpo(StringEscapeUtils.escapeHtml(e.getMessage()));
		}

		Gson gson = new Gson();
		String jsonString = gson.toJson(mensagem);
		resp.getWriter().write(jsonString);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPut(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		resp.setHeader("Cache-Control", "no-cache");

		Mensagem mensagem = new Mensagem();

		try {

			String nome = req.getParameter("nome");
			String login = req.getParameter("usuario");
			String senha = req.getParameter("senha");
			Boolean recebeNotificacao = Boolean.valueOf(req
					.getParameter("permiteNotificiacao").equalsIgnoreCase("on"));

			UsuarioDao dao = new UsuarioDao();

			Usuario usuario = new Usuario(null, nome, login, senha,
					recebeNotificacao);

			dao.inserir(usuario);

			req.getSession().setAttribute("usuario", usuario);

			mensagem.setErro(false);
			mensagem.setDescricao(StringEscapeUtils.escapeHtml("Sucesso!"));
			mensagem.setCorpo(usuario.getLogin());

		} catch (Exception e) {
			mensagem.setErro(true);
			mensagem.setDescricao(StringEscapeUtils.escapeHtml(e.getMessage()));
			mensagem.setCorpo(StringEscapeUtils.escapeHtml(e.getMessage()));
		}

		Gson gson = new Gson();
		String jsonString = gson.toJson(mensagem);
		resp.getWriter().write(jsonString);
	}
}
