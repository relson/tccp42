<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.unip.ccp42.negocio.entidade.Usuario"%>
<%@page import="br.unip.ccp42.dados.traducao.PerguntaTD"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.unip.ccp42.EntidadeBase"%>
<%@page import="br.unip.ccp42.negocio.mi.Pergunta"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNIP CCP42 - Analisador Financeiro [P&aacute;gina Administra&ccedil;&atilde;o - Manter Perguntas]</title>
<link href="../css/Estilo.css" type="text/css" rel="stylesheet">
</head>
<body>
<h1>Manter Perguntas</h1>
<a href="../admin/Administrar.jsp">Voltar</a>
<h3><a href="ManterPerguntaEditar.jsp">Inserir Nova Pergunta</a></h3>
<hr>
<% 
	if (session.getAttribute("usuario") == null) {
		response.sendRedirect("../login/login.html");
	} else {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if (!usuario.getNome().equalsIgnoreCase("admin")) {
			response.sendRedirect("../login/login.html");
		}		
	}

	ArrayList<EntidadeBase> perguntas = new PerguntaTD().obterTodos();
	
	for(EntidadeBase entidade:perguntas)
	{
		Pergunta pergunta = (Pergunta)entidade;
		%>
		<a href="ManterPerguntaEditar.jsp?codigoPergunta=<%=pergunta.getCodigo()%>">Editar</a>&nbsp;-&nbsp;<a href="ManterPerguntaExcluir.jsp?codigoPergunta=<%=pergunta.getCodigo()%>">Excluir</a>&nbsp;-&nbsp;<%=pergunta.getNome()%><br/>
		<%
	}
%>
<hr>
<h3><a href="ManterPerguntaEditar.jsp">Inserir Nova Pergunta</a></h3>
<a href="../admin/Administrar.jsp">Voltar</a>
</body>
</html>