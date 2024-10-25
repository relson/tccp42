<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="br.unip.ccp42.dados.traducao.OpcaoTD"%>
<%@page import="br.unip.ccp42.negocio.mi.Opcao"%>
<%@page import="br.unip.ccp42.dados.traducao.UsuarioTD"%>
<%@page import="br.unip.ccp42.negocio.entidade.Usuario"%>
<%@page import="br.unip.ccp42.negocio.mi.Classe"%>
<%@page import="br.unip.ccp42.dados.traducao.ClasseTD"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNIP CCP42 - Analisador Financeiro [Atualizando Perfil...]</title>
<link href="../css/Estilo.css" type="text/css" rel="stylesheet">
</head>
<body>
<%
	int codigoAplicacao = 0;
	// Verifica se tem um usuário configurado na sessão
	if (session.getAttribute("usuario") != null)
	{
		if (request.getParameter("opcao") != null){
			
			if ( request.getParameter("codigoAplicacao") != null ) {
			
				codigoAplicacao = Integer.parseInt(request.getParameter("codigoAplicacao").toString());
				 
				int codigoOpcao = Integer.parseInt(request.getParameter("opcao").toString());			
			
				OpcaoTD opcaoTD = new OpcaoTD();			
				Opcao opcao = (Opcao)opcaoTD.obterPorCodigo(codigoOpcao);
			
				ClasseTD classeTD = new ClasseTD();
				Classe classe = (Classe)classeTD.obterPorCodigo(codigoAplicacao);
				try {
					classeTD.inserirOpcaoPerfil(classe, opcao);
				} catch (Exception ex) {
				%>
					<b>
					<%=ex.getMessage()%>
					<br />
					<br />
					<a href="AnalisarPerfilAplicacao.jsp?codigoAplicacao=<%=codigoAplicacao%>">Voltar</a>
					</b>				
					<%
				}
			}
		}
		String redirecionar = "AnalisarPerfilAplicacao.jsp?codigoAplicacao=" + codigoAplicacao;
		response.sendRedirect(redirecionar);
	} else {
		response.sendRedirect("../login/login.html");
	}
%>
</body>
</html>