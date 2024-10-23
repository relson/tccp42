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
<title>UNIP CCP42 - Analisador Financeiro [Cadastrar novo Usu&aacute;rio]</title>
<link href="../css/Estilo.css" type="text/css" rel="stylesheet">
</head>
<body>
<h1>Cadastrar Novo Usu&aacute;rio</h1>
<a href="../login/login.html">Voltar</a>
<hr>
<% 	
	try
	{
		Usuario usuario = new Usuario();
		
		String nome  = request.getParameter("usuario").toString();
		String senha = request.getParameter("senha").toString();
		
		usuario.setNome(nome);
		usuario.setSenha(senha);
		
	 	usuario.novo();
	 	
	 	%>
	 		<h2>Usu&aacute;rio Inserido com sucesso</h2>
	 	<% 
	}
	catch(Exception ex) {
		%>			
 			<h2><%=ex.getMessage()%></h2>
 		<%
	}
	%>
		<hr>
		<a href="../login/login.html">Voltar</a>		
</body>
</html>