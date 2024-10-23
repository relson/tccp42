<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.unip.ccp42.negocio.entidade.Usuario"%>
<%@page import="br.unip.ccp42.dados.traducao.PerguntaTD"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.unip.ccp42.EntidadeBase"%>
<%@page import="br.unip.ccp42.negocio.mi.Pergunta"%>
<%@page import="br.unip.ccp42.negocio.mi.Opcao"%>
<%@page import="sun.misc.Perf.GetPerfAction"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNIP CCP42 - Analisador Financeiro [Página Administração - Manter Pergunta(Inserir)]</title>
<link href="Estilo.css" type="text/css" rel="stylesheet">
</head>
<body>
<h1>Manter Perguntas</h1>
<a href="ManterPerguntas.jsp">Voltar</a>
<hr>
<% 
	if (session.getAttribute("usuario") == null) {
		response.sendRedirect("login.html");
	} else {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if (!usuario.getNome().equalsIgnoreCase("admin")) {
			response.sendRedirect("login.html");
		}		
	}

	try
	{
		Pergunta pergunta = new Pergunta();
		
		String nome = request.getParameter("pergunta").toString();
		
		pergunta.setNome(nome);
		
		ArrayList<Opcao> opcoes = new ArrayList<Opcao>();
		
	 	for(int i = 1; i < 6; i++)
		{
	 		Opcao opcao = new Opcao();
	 		String nomeParametro = "opcao[" + i + "]";
	 		String nomeOpcao = request.getParameter(nomeParametro).toString();
	 		
	 		opcao.setNome(nomeOpcao);
	 		opcoes.add(opcao);	
		}
	 	
	 	pergunta.setOpcoes(opcoes);
	 	pergunta.novo();
	 	
	 	%>
	 		<h2>Pergunta Inserida com sucesso</h2>
	 	<% 
	}
	catch(Exception ex) {
		%>			
 			<h2><%=ex.getMessage()%></h2>
 		<%
	}
	%>
		<hr>
		<a href="ManterPerguntas.jsp">Voltar</a>		
</body>
</html>