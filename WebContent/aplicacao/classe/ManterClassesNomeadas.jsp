<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.unip.ccp42.negocio.entidade.Usuario"%>
<%@page import="br.unip.ccp42.dados.traducao.PerguntaTD"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.unip.ccp42.EntidadeBase"%>
<%@page import="br.unip.ccp42.negocio.mi.Pergunta"%>
<%@page import="br.unip.ccp42.dados.traducao.ClasseTD"%>
<%@page import="br.unip.ccp42.negocio.mi.Classe"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNIP CCP42 - Analisador Financeiro [Página Administração - Manter Aplica&ccedil;&atilde;o]</title>
<link href="../css/Estilo.css" type="text/css" rel="stylesheet">
</head>
<body>
<h1>Manter Aplica&ccedil;&atilde;o</h1>
<a href="../admin/Administrar.jsp">Voltar</a>
<h3>
<a href="ManterAplicacoesInserir.html">Inserir Nova Aplica&ccedil;&atilde;o</a>
</h3>
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

	ArrayList<EntidadeBase> classes = new ClasseTD().obterTodos();
	
	for(EntidadeBase entidade:classes)
	{
		Classe classe = (Classe)entidade;
		%>
		<a href="AnalisarPerfilAplicacao.jsp?codigoAplicacao=<%=classe.getCodigo()%>">Editar</a>&nbsp;-&nbsp;<a href="ManterClassesNomeadasReclassificar.jsp?codigoAplicacao=<%=classe.getCodigo()%>">Reclassificar</a>&nbsp;-&nbsp;<a href="ManterClassesNomeadasExcluir.jsp?codigoAplicacao=<%=classe.getCodigo()%>">Excluir</a>&nbsp;-&nbsp;<%=classe.getNome()%><br/>
		<%
	}
%>
<br>
<hr>
<h3>
<a href="ManterAplicacoesInserir.html">Inserir Nova Aplica&ccedil;&atilde;o</a></h3>
<a href="../admin/Administrar.jsp">Voltar</a>

</body>
</html>