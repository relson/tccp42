<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.unip.ccp42.negocio.entidade.Usuario"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNIP CCP42 - Analisador Financeiro [Página Administração]</title>
<link href="../css/Estilo.css" type="text/css" rel="stylesheet">
</head>
<body>
<h1>Painel Administrativo</h1>
<%
	if (session.getAttribute("usuario") == null) {
		response.sendRedirect("../login/login.html");
	} else {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if (!usuario.getNome().equalsIgnoreCase("admin")) {
			response.sendRedirect("../login/login.html");
		}		
	}
%>
<a href="../login/login.html">Voltar</a>
<hr>
<div align="center" style="background-color: rgb(255, 255, 255); border-color: rgb( 230, 230, 230);">
<a href="../pergunta/ManterPerguntas.jsp">Manter Perguntas</a> <br/>
<a href="../classe/ManterClassesNomeadas.jsp">Manter Aplica&ccedil;&atilde;o</a> <br/>
<a href="../usuario/ManterUsuarios.jsp">Manter Usu&aacute;rios</a> <br/>
</div>
<hr>
<a href="../login/login.html">Voltar</a>
</body>
</html>