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
<%@page import="br.unip.ccp42.negocio.entidade.AplicacaoLink"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNIP CCP42 - Analisador Financeiro [Inserir nova Aplica&ccedil;&atilde;o]</title>
<link href="../css/Estilo.css" type="text/css" rel="stylesheet">
</head>
<body>
<h1>Inserir Nova Aplica&ccedil;&atilde;o</h1>
<a href="ManterClassesNomeadas.jsp">Voltar</a>
<hr>
<% 	
	try
	{
		Classe classe = new Classe();
		
		String nome  = request.getParameter("aplicacao").toString();
		String url = request.getParameter("url").toString();
		
		classe.setNome(nome);
				
	 	classe.novo();
	 	
	 	AplicacaoLink aplicacaoLink = new AplicacaoLink();
	 	
	 	aplicacaoLink.setNome(nome);
	 	aplicacaoLink.setUrl(url);
	 	aplicacaoLink.setAplicacao(classe);
	 	
	 	aplicacaoLink.novo();
	 	
	 	%>
	 		<h2>Aplica&ccedil;&atilde;o Inserida com sucesso</h2>
	 	<% 
	}
	catch(Exception ex) {
		%>			
 			<h2><%=ex.getMessage()%></h2>
 		<%
	}
	%>
		<hr>
		<a href="ManterClassesNomeadas.jsp">Voltar</a>		
</body>
</html>