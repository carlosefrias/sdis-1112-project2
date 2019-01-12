<?php
include "protected/db_spy.php";
$item = $_POST['item'];
$user = $_POST['user'];

$connection = mysql_connect($host, $user, $pass) or die ("N&atilde;o foi poss&iacute;vel ligar ao servidor MySQL!");
mysql_select_db($db) or die ("N&atilde;o foi poss&iacute;vel ligar &agrave; base de dados!");
$query = "UPDATE user SET points=points+$pontos WHERE userName = '$user'";
$resultado = mysql_query($query) or die ("Erro na query: $query. ".mysql_error());
echo "Points aquired";
?>