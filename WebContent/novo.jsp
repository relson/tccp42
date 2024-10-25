<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
	<style>
		* {
			margin: 0;
			padding: 0;
		}
		
		body {
			background: #242424;
			margin: 20px;
		}
		
		fieldset {
			border: none;
		}
		
		form fieldset legend {
			font: bold 23px verdana, arial, tahoma;
			color: orange;
			margin: 0 0 10px;
		}
		
		form fieldset label {
			color: #FFF;
			font: 20px arial, tahoma, sans-serif;
			display: block;			
			width:240px;
			float: left;
		}
		
		form fieldset label.labmensagem {
			width: 470px;
		}
		
		form fieldset label.labmensagem textarea {
			width: 455px;
			height:200px;
			font: 13px arial, tahoma, sans-serif;
			padding: 3px;
		}
		
		form fieldset label input {
			width: 220px;
			border: 1px solid #FFF;
			padding: 3px;
		}
		
		form fieldset input.botao {
			float: right;
			margin: 20px 40px;
			border: 1px solid #FFF;
			background:black;
			color:#FFF;
			padding:5px;
			font: 13px verdana, arial, tahoma, sans-serif;
			cursor: pointer;
		}
		
	</style>
</head>
<body>
	<form>
		<fieldset>
			<legend>Fale conosco!</legend>
			<label>Nome: <input type="text"/></label>
			<label>Email: <input type="text"/></label>
			<label>Empresa: <input type="text"/></label>
			<label>Telefone: <input type="text"/></label>
			<label class="labmensagem">Mensagem: <textarea></textarea></label>
			<input type="button" class="botao" value="Enviar" />
		</fieldset>
	</form>
</body>
</html>