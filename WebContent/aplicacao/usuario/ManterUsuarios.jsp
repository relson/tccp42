<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.unip.ccp42.negocio.entidade.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.unip.ccp42.dados.traducao.UsuarioTD"%>
<%@page import="br.unip.ccp42.EntidadeBase"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNIP CCP42 - Analisador Financeiro [P&aacute;gina Administra&ccedil;&atilde;o - Manter Usu&aacute;rios]</title>
<link href="../css/Estilo.css" type="text/css" rel="stylesheet">
</head>
<body>
<h1>Manter Usu&aacute;rios</h1>
<a href="../admin/Administrar.jsp">Voltar</a>
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

	ArrayList<EntidadeBase> usuarios = new UsuarioTD().obterTodos();
	
	for(EntidadeBase entidade:usuarios)
	{
		Usuario usuario = (Usuario)entidade;
		%>
		<a href="ManterUsuariosExcluir.jsp?codigoUsuario=<%=usuario.getCodigo()%>">Excluir</a>&nbsp;-&nbsp;<%=usuario.getNome()%><br/>
		<%
	}
%>
<hr>
<a href="../admin/Administrar.jsp">Voltar</a>
</body>
</html>	