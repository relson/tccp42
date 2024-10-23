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
<title>UNIP CCP42 - Analisador Financeiro [P&aacute;gina Administra&ccedil;&atilde;o - Manter Pergunta(Excluir)]</title>
<link href="../css/Estilo.css" type="text/css" rel="stylesheet">
</head>
<body>
<h1>Manter Perguntas</h1>
<a href="ManterPerguntas.jsp">Voltar</a>
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

	try
	{
		Pergunta pergunta = new Pergunta();
		
		int codigoPergunta = Integer.parseInt(request.getParameter("codigoPergunta").toString());
		
		pergunta.setCodigo(codigoPergunta);
				
	 	pergunta.excluir();
	 	
	 	%>
	 		<h2>Pergunta Exclu&iacute;da com sucesso</h2>
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