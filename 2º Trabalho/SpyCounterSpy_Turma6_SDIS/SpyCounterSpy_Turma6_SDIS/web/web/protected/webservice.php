<?php
include "protected/db_spy.php";

// Distinguir o tipo de pedido.
$service= $_POST['pedido'];

// Autenticaчуo.
if($pedido == "new_user") {
	$utilizador = $_POST['utilizador'];
	$ppasse = $_POST['ppasse'];
	$nome = $_POST['nome'];
	$nascimento = $_POST['nascimento'];
	$mail = $_POST['mail'];

	$connection = mysql_connect($host, $user, $pass) or die ("N&atilde;o foi poss&iacute;vel ligar ao servidor MySQL!");
	mysql_select_db($db) or die ("N&atilde;o foi poss&iacute;vel ligar &agrave; base de dados!");

	$query = "INSERT INTO user (name, birthdate, email, userName, password, points) VALUES ('$nome', '$nascimento', '$mail', '$utilizador', '$ppasse', 1000)";
	$resultado = mysql_query($query) or die ("Erro na query: $query. ".mysql_error());
	if (mysql_num_rows($resultado) == 1) {
		echo "Utilizador criado com sucesso.";
	}
	else {
		echo "Utilizador nуo criado.";
	}
}




?>