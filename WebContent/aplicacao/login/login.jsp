<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.unip.ccp42.negocio.entidade.Usuario"%>
<%@page import="br.unip.ccp42.dados.traducao.UsuarioTD"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNIP CCP42 - Analisador Financeiro [Login]</title>
<link href="../css/Estilo.css" type="text/css" rel="stylesheet">
</head>
<body>
<%

	try {
		
		String nomeUsuario = request.getParameter("usuario");
		String senha = request.getParameter("senha");
		
		Usuario usuario = new UsuarioTD().efetuarLogin(nomeUsuario, senha);
		
		session.setAttribute("usuario", usuario);
		
		/*
		 * Se for o usuário administrador redireciona para
		 * pagina administrativas.
		 */ 
		if (usuario.getNome().equalsIgnoreCase("admin"))
			response.sendRedirect("../admin/Administrar.jsp");
		else
			response.sendRedirect("../usuario/AnalisarPerfil.jsp");
		
	} catch (Exception e) {
		%> 	
			<center>		
			<h2>			
			<%=e.getMessage()%>
			</h2>
			<a href="../usuario/ManterUsuariosInserir.html">Novo Usu&aacute;rio</a>
			<br>
			<a href="../login/login.html">Voltar</a>
			</center>
		<%			
	}	 

%>
</body>
</html>