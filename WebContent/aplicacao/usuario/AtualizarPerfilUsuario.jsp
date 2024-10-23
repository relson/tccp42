<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="br.unip.ccp42.dados.traducao.OpcaoTD"%>
<%@page import="br.unip.ccp42.negocio.mi.Opcao"%>
<%@page import="br.unip.ccp42.dados.traducao.UsuarioTD"%>
<%@page import="br.unip.ccp42.negocio.entidade.Usuario"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNIP CCP42 - Analisador Financeiro [Atualizando Perfil...]</title>
<link href="Estilo.css" type="text/css" rel="stylesheet">
</head>
<body>
<%
	// Verifica se tem um usuário configurado na sessão
	if (session.getAttribute("usuario") != null)
	{
		if (request.getParameter("opcao") != null){
			
			Usuario usuario = (Usuario)session.getAttribute("usuario");
			int codigoOpcao = Integer.parseInt(request.getParameter("opcao").toString());			
			
			OpcaoTD opcaoTD = new OpcaoTD();			
			Opcao opcao = (Opcao)opcaoTD.obterPorCodigo(codigoOpcao);
			
			UsuarioTD usuarioTD = new UsuarioTD();
			
			try {
				usuarioTD.inserirOpcaoPerfil(usuario, opcao);
			} catch (Exception ex) {
				%>
				<b>
				<%=ex.getMessage()%>
				<br />
				<br />
				<a href="AnalisarPerfil.jsp">Voltar</a>
				</b>				
				<%
			}
		}
		response.sendRedirect("AnalisarPerfil.jsp");
	} else {
		response.sendRedirect("login.html");
	}
%>
</body>
</html>