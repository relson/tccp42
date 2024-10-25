<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.unip.ccp42.negocio.entidade.Usuario"%>
<%@page import="br.unip.ccp42.dados.traducao.PerguntaTD"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.unip.ccp42.EntidadeBase"%>
<%@page import="br.unip.ccp42.negocio.mi.Pergunta"%>
<%@page import="br.unip.ccp42.negocio.mi.Opcao"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNIP CCP42 - Analisador Financeiro [Página Administração - Manter Pergunta]</title>
<link href="Estilo.css" type="text/css" rel="stylesheet">
</head>
<body>
<% 
	if (session.getAttribute("usuario") == null) {
		response.sendRedirect("login.html");
	} else {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if (!usuario.getNome().equalsIgnoreCase("admin")) {
			response.sendRedirect("login.html");
		}		
	}

	int codigoPergunta = 0;

	if (request.getParameter("codigoPergunta") != null) {
		codigoPergunta = Integer.parseInt(request.getParameter("codigoPergunta").toString());		
	}

	if (codigoPergunta == 0) {
		%>
			<h1>Inserir Pergunta</h1>			
			<a href="ManterPerguntas.jsp">Voltar</a>
			<hr>
			<h2>Descrição Pergunta</h2>
			<form action="ManterPerguntaInserir.jsp" method="post">
			<input id="pergunta" name="pergunta" type="text" value="">
			<h3>Opções</h3>
		<%		
		for(int i = 1; i < 6; i++)
		{	
			%>
			<%=i%>.<input id="opcao[<%=i%>]" name="opcao[<%=i%>]" type="text" value=""><br>			
			<%
		}	
		%>
			<br>
			<input type="submit" value="Inserir">
			<input type="reset" value="Reiniciar"> <br>
			<hr>
			<a href="ManterPerguntas.jsp">Voltar</a> 
			</form>
		<%
	} else {
		Pergunta pergunta = (Pergunta)(new PerguntaTD().obterPorCodigo(codigoPergunta));
		%>
			<h1>Alterar Pergunta</h1>
			<a href="ManterPerguntas.jsp">Voltar</a>
			<hr>			
			<h2>Descrição Pergunta</h2>					
			<form action="ManterPerguntaAlterar.jsp" method="post">
			<input id="perguntaCodigo" name="perguntaCodigo" type="hidden" value="<%=pergunta.getCodigo()%>">
			<input id="pergunta" name="pergunta" type="text" value="<%=pergunta.getNome()%>">
			<h3>Opções</h3>
		<%
		int i = 0;
		for(Opcao opcao:pergunta.getOpcoes())
		{	
			%>
			<%=++i%>.<input id="opcao[<%=opcao.getCodigo()%>]" name="opcao[<%=opcao.getCodigo()%>]" type="text" value="<%=opcao.getNome()%>"><br>			
			<%
		}	
		%>
			<br>
			<input type="submit" value="Alterar">
			<input type="reset" value="Reverter" alt="Reverter aos valores originais"> <br>
			<hr>
			<a href="ManterPerguntas.jsp">Voltar</a> 
			</form>
		<%
	}	
%>
</body>
</html>