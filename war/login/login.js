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

function efetuarLogin() {
	
	var divMensagem = document.getElementById("lblMensagem");
	var img = document.getElementById("imgCarregando");
	
	if (!validarLogin()){
		return false;
	}
	
	img.style.display = "block";
	divMensagem.innerHTML = "Aguarde...";
	
	if (xmlhttp) {
		var usuario = document.getElementById("usuario");
		var senha = document.getElementById("senha");
		xmlhttp.open("POST", "../servlet/login", true); // getname will
																// be the
																// servlet name
		xmlhttp.onreadystatechange = handleServerResponse;
		xmlhttp.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded');
		xmlhttp.send("usuario=" + usuario.value + "&senha="+senha.value);
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
					
					if (msg.corpo == "admin")
						window.location = "../admin/administrar.html";
					else
						window.location = "../usuario/analisar-perfil-investidor.html";
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

function validarLogin() {

	var divMensagem = document.getElementById("lblMensagem");
	
	try {

		var valido = true;
		var mensagem = "";

		var usuarioTextBox = document.getElementById("usuario");

		if (usuarioTextBox.value == "") {
			mensagem += "É necessário informar um nome de usuário.";
			valido = false;
		}

		var senhaTextBox = document.getElementById("senha");

		if (senhaTextBox.value == "") {
			mensagem += "É necessário informar uma senha.";
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