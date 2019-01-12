<?php
echo "host: " . $host;
$servername = $host;

function validarUtilizador($utilizador, $ppasse) {
	echo "servername: " . $servername;
	$connection = mysql_connect($host, $user, $pass) or die ("N&atilde;o foi poss&iacute;vel ligar ao servidor MySQL!");
	mysql_select_db($db) or die ("N&atilde;o foi poss&iacute;vel ligar &agrave; base de dados!");
	$query = "SELECT id, name FROM user WHERE userName = '$utilizador' AND password = '$ppasse'";
	$resultado = mysql_query($query) or die ("Erro na query: $query. ".mysql_error());
	
	while($row=mysql_fetch_array($res)) {
		$resultado[] = $row;
	}
	return	$resultado;
}
?>