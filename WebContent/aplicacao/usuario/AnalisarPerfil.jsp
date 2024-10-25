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


<%@page import="br.unip.ccp42.negocio.mi.Hipotese"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UNIP CCP42 - Analisador Financeiro [Analisar Perfil]</title>
<link href="../css/Estilo.css" type="text/css" rel="stylesheet">
</head>
<body>
	<fieldset >	
	<%-- Fazer um ForEach Nas Opções de Menu --%>
	<legend>Menu</legend>
	<center>
	<span><a href="ManterDadosCadastrais.jsp">Manter Dados Cadastrais</a></span>
	<span><a href="ConsultarSimulacoes.jsp">Consultar
	Simula&ccedil;&otilde;es</a></span>
	<span><a href="ManterPerfil.jsp">Manter Perfil</a></span>
	</center>
</fieldset>

<%
	Usuario usuario = null;
	
 	if (session.getAttribute("usuario") != null) {
 		usuario = (Usuario)session.getAttribute("usuario");
 		//aplicacoesLista		
 		
 %>
 <hr style="border-style: dotted;">
 <h3>Investidor: <%=usuario.getNome()%></h3>
 
 <hr style="border-style: dotted;">
 
 <%
 	} else {
 		response.sendRedirect("../login/login.html");
 	}
	%>
	<% 
			try {
		%>
		<form action="AtualizarPerfilUsuario.jsp" method="post">		
			<%
			
			/*
			 * Esta parte só será executada se estiver um usuário logado na sessão.
			 * Por isso não é necessário verificar se é nulo.
			 */
			usuario = (Usuario)session.getAttribute("usuario");
			
			PerguntaTD perguntaTD = new PerguntaTD();
					
			Pergunta pergunta = perguntaTD.obterProxima(usuario);						
			%>
			<fieldset style="display: block;">
				<legend ><%=pergunta.getNome()%></legend>
				<%
				boolean primeira = true;
				
				for(Opcao opcao:pergunta.getOpcoes())
				{ 
				%>
				<div>
				<label>
					<input id="opcao" name="opcao" value="<%=opcao.getCodigo()%>"  type="radio"  <%=primeira?"checked=\"checked\"":""%> />
					<%=opcao.getNome()%>					
				</label>
				</div>
				<%
					primeira = false;
				}
				%>
				<input type="submit" value="Gravar"/>
			</fieldset>
		</form> <% 
		} catch (Exception ex) {
				%>
					<label><b><%=ex.getMessage()%></b></label>
				<%
			} 
		%>		
		<hr style="border-style: dotted;">
		<h3>Lista de aplica&ccedil;&otilde;es para o perfil</h3>
		<hr style="border-style: dotted;">		
		<%
			Indutor indutor = new Indutor(usuario);
			
			for (Hipotese hipotese:indutor.getHipotese()) {
				AplicacaoLink aplicacao = hipotese.getAplicacaoLink();
				
		%> <a href="<%=aplicacao.getUrl()%>"> <%=aplicacao.getNome()%> </a>
		<br />		
		<%
			}			
		%>
		
</body>
</html>