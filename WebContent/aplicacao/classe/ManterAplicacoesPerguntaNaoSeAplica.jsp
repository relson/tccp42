<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.unip.ccp42.negocio.entidade.Usuario"%>
<%@page import="br.unip.ccp42.dados.traducao.PerguntaTD"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.unip.ccp42.EntidadeBase"%>
<%@page import="br.unip.ccp42.negocio.mi.Pergunta"%>
<%@page import="br.unip.ccp42.negocio.mi.Opcao"%>
<%@page import="sun.misc.Perf.GetPerfAction"%>
<%@page import="br.unip.ccp42.negocio.mi.Classe"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNIP CCP42 - Analisador Financeiro [P&aacute;gina Administra&ccedil;&atilde;o - Manter Aplica&ccedil;&atilde;o(Excluir)]</title>
<link href="../css/Estilo.css" type="text/css" rel="stylesheet">
</head>
<body>
<% 
	if (session.getAttribute("usuario") == null) {
		response.sendRedirect("../login/login.html");
	} else {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if (!usuario.getNome().equalsIgnoreCase("admin")) {
			response.sendRedirect("../login/login.html");
		}		
	}
	try
	{
		Classe classe = new Classe();
			
		int codigoAplicacao = Integer.parseInt(request.getParameter("codigoAplicacao").toString());
		
		classe.setCodigo(codigoAplicacao);
		
		%>
		<h1>Manter Aplica&ccedil;&atilde;o</h1>
		<a href="AnalisarPerfilAplicacao.jsp?codigoAplicacao=<%=codigoAplicacao%>">Voltar</a>
		<hr>
		<%
		
		
		int codigoPergunta = Integer.parseInt(request.getParameter("codigoPergunta").toString());
		Pergunta pergunta = new Pergunta();
		
		pergunta.setCodigo(codigoPergunta);
		
		pergunta.configurarNaoSeAplica(classe);
		%>
		<h2>Pergunta marcada como n&atilde;o se aplica&ccedil;&atilde;o com sucesso</h2>
 		<hr>
		<a href="AnalisarPerfilAplicacao.jsp?codigoAplicacao=<%=codigoAplicacao%>">Voltar</a>
	 		
	 	<% 
	}
	catch(Exception ex) {
		%>			
 			<h2><%=ex.getMessage()%></h2>
 		<%
	}
	%>
</html>