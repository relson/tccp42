function getXMLObject() // XML OBJECT
{
	var xmlHttp = false;
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP"); // For Old Microsoft
														// Browsers
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); // For Microsoft
																// IE 6.0+
		} catch (e2) {
			xmlHttp = false; // No Browser accepts the XMLHTTP Object then
								// false
		}
	}
	if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
		xmlHttp = new XMLHttpRequest(); // For Mozilla, Opera Browsers
	}
	return xmlHttp; // Mandatory Statement returning the ajax object created
}

var xmlhttp = new getXMLObject(); // xmlhttp holds the ajax object

function inserirUsuario() {
	
	var divMensagem = document.getElementById("lblMensagem");
	var img = document.getElementById("imgCarregando");
	
	if (!validar()){
		return false;
	}
	
	img.style.display = "block";
	divMensagem.innerHTML = "Aguarde...";
	
	if (xmlhttp) {
		var nome = document.getElementById("nome");
		var usuario = document.getElementById("usuario");
		var senha = document.getElementById("senha");
		var permiteNotificiacao = document.getElementById("permiteNotificiacao");
		
		xmlhttp.open("PUT", "../servlet/usuario", true); // getname will
																// be the
																// servlet name
		xmlhttp.onreadystatechange = handleServerResponse;
		xmlhttp.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
		xmlhttp.send("nome=" + nome.value + "&usuario=" + usuario.value + "&senha="+senha.value+"&permiteNotificiacao="+permiteNotificiacao.value);
	}

	return false;
}

function handleServerResponse() {
	
	var divMensagem = document.getElementById("lblMensagem");
	var img = document.getElementById("imgCarregando");
	
	if (xmlhttp.readyState == 4) {
		if (xmlhttp.status == 200) {
			try {
				
				img.style.display = "none";
				
				var msg = eval("(" + xmlhttp.responseText + ")");

				divMensagem.innerHTML = msg.descricao;

				if (!msg.erro){
					
					divMensagem.innerHTML = msg.descricao + "<p>Redirecionando para tela de login.</p>";
					
					setTimeout("window.location = \"../admin/administrar.html\"");
					
					/*if (msg.corpo == "admin")
						window.location = "../admin/administrar.html";
					else
						window.location = "../usuario/analisar-perfil-investidor.html";*/
				} 
			} catch (ex) {
				divMensagem.innerHTML = "Erro: " + e.message + "<br>Tipo:" + e.name;
			}
		} else {
			alert("Error during AJAX call. Please try again");
			img.style.display = "none";
			divMensagem.innerHTML = "Erro!";
		}
	} else if (xmlhttp.readyState == 2 || xmlhttp.readyState == 3) {
		
	}
}

function validar() {

	var divMensagem = document.getElementById("lblMensagem");
	
	try {

		var valido = true;
		var mensagem = "";

		var nomeTextBox = document.getElementById("nome");

		if (nomeTextBox.value == "") {
			mensagem += "<p>É necessário informar um <a title=\"Click para configurar o foco no campo\" href=\"javascript:document.getElementById('nome').focus();\"><strong>nome</strong></a> para o usuário.<p>";
			valido = false;
		}
		
		var usuarioTextBox = document.getElementById("usuario");

		if (usuarioTextBox.value == "") {
			mensagem += "<p>É necessário informar um <a title=\"Click para configurar o foco no campo\" href=\"javascript:document.getElementById('usuario').focus();\"><strong>login</strong></a> para o usuário.<p>";
			valido = false;
		}

		var senhaTextBox = document.getElementById("senha");

		if (senhaTextBox.value == "") {
			mensagem += "<p>É necessário informar uma <a title=\"Click para configurar o foco no campo\" href=\"javascript:document.getElementById('senha').focus();\"><strong>senha</strong></a> para o usuário.<p>";
			valido = false;
		}

		if (!valido) {
			divMensagem.innerHTML = mensagem;
		}

		/*setTimeout(
				'document.getElementById(\"imgCarregando\").style.display = \"none\";',
				3000)
		setTimeout(
				'document.getElementById(\"lblMensagem\").innerHTML = \"Sucesso!\";',
				3000)*/

		return valido;
	} catch (e) {
		divMensagem.innerHTML = "Erro: " + e.message + "<br>Tipo:" + e.name;
	}

	return false;
}