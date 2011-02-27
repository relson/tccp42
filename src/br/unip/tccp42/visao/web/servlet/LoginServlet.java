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
public class LoginServlet extends HttpServlet {

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

			String login = req.getParameter("usuario");
			String senha = req.getParameter("senha");
			UsuarioDao dao = new UsuarioDao();
			Usuario usuario = dao.efetuarLogin(login, senha);
			
			req.getSession().setAttribute("usuario", usuario);
			
			mensagem.setErro(false);
			mensagem.setDescricao(StringEscapeUtils.escapeHtml("Sucesso!"));
			mensagem.setCorpo(usuario.getLogin());
			
		} catch (Exception e) {
			mensagem.setErro(true);
			mensagem.setDescricao(StringEscapeUtils
					.escapeHtml("Usuário ou senhas inválidas.\n" + e.getMessage()));
			mensagem.setCorpo(StringEscapeUtils.escapeHtml(e.getMessage()));
		}

		Gson gson = new Gson();
		String jsonString = gson.toJson(mensagem);
		resp.getWriter().write(jsonString);
	}

}
