<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.Calendar"%>
<%@page import="br.unip.ccp42.apresentacao.*"%>
<%@page import="br.unip.ccp42.negocio.entidade.AplicacaoLink"%>
<%@page import="br.unip.ccp42.negocio.mi.Indutor"%>
<%@page import="br.unip.ccp42.dados.traducao.UsuarioTD"%>
<%@page import="br.unip.ccp42.negocio.entidade.Usuario"%>
<%@page import="br.unip.ccp42.negocio.mi.Pergunta"%>
<%@page import="br.unip.ccp42.dados.traducao.PerguntaTD"%>
<%@page import="br.unip.ccp42.negocio.mi.Opcao"%>
<jsp:useBean id="aplicacoesLista" scope="page"
	class="br.unip.ccp42.apresentacao.UsuarioPerfil">
</jsp:useBean>
<%@page import="br.unip.ccp42.negocio.mi.Classe"%>
<%@page import="br.unip.ccp42.dados.traducao.ClasseTD"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNIP CCP42 - Analisador Financeiro [Analisar Perfil
Aplica&ccedil;&atilde;o]</title>
<link href="../css/Estilo.css" type="text/css" rel="stylesheet">
</head>
<body>
<h1>Mater Perfil Aplica&ccedil;&atilde;o</h1>
<a href="ManterClassesNomeadas.jsp">Voltar</a>
<hr>
<%
	if (session.getAttribute("usuario") != null) {

		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if (!usuario.getNome().equalsIgnoreCase("admin")) {
			response.sendRedirect("../login/login.html");
		}
		
	} else {
		response.sendRedirect("../login/login.html");
	}
	try {
		
		String parametroCodigoAplicacao = request.getParameter("codigoAplicacao");
		
		if (parametroCodigoAplicacao == null ||
			parametroCodigoAplicacao.isEmpty()) {
			response.sendRedirect("../login/login.html");		 
		}
		
		int codigoAplicacao = Integer.parseInt(parametroCodigoAplicacao);
		
		Classe classe = (Classe)(new ClasseTD().obterPorCodigo(codigoAplicacao));
%>
<h2><%=classe.getNome()%></h2>

<form
	action="AtualizarPerfilAplicacao.jsp?codigoAplicacao=<%=parametroCodigoAplicacao%>"
	method="post">
<%
		/*
		 * Esta parte só será executada se estiver um usuário logado na sessão.
		 * Por isso não é necessário verificar se é nulo.
		 */
		
		PerguntaTD perguntaTD = new PerguntaTD();

		Pergunta pergunta = perguntaTD.obterProxima(classe);
%>

<table>
	<tr>
		<td colspan="2">
		<h3><%=pergunta.getNome()%></h3>
		</td>
	</tr>
	<%
		boolean primeira = true;

			for (Opcao opcao : pergunta.getOpcoes()) {
	%>
	<tr>
		<td><input id="opcao" name="opcao" value="<%=opcao.getCodigo()%>"
			type="radio" <%=primeira ? "checked=\"checked\"" : ""%> /></td>
		<td><%=opcao.getNome()%></td>
	</tr>
	<%
		primeira = false;
			}
	%>
	<tr>
		<td><input type="submit" value="Gravar" /></td>
		<td><a
			href="ManterAplicacoesPerguntaNaoSeAplica.jsp?codigoAplicacao=<%=parametroCodigoAplicacao%>&codigoPergunta=<%=pergunta.getCodigo()%>"
			title="Marcar pergunta como n&atilde;o se aplica.">
		N&atilde;o se Aplica </a></td>
	</tr>
</table>
</form>
<%
	} catch (Exception ex) {
%>
<label><b><%=ex.getMessage()%></b></label>
<%
	}
%>
<hr>
<a href="ManterClassesNomeadas.jsp">Voltar</a>
</body>
</html>