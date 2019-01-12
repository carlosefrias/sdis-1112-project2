<?php
session_start();
include ("protected/db_spy.php");

$loginFailed = "";
if (!isset($_POST['Autenticar'])) {
	$_SESSION['sessao']++;
}
else {
	$utilizador = $_POST['utilizador'];
	$ppasse = $_POST['ppasse'];
	$connection = mysql_connect($host, $user, $pass) or die ("N&atilde;o foi poss&iacute;vel ligar ao servidor MySQL!");
	mysql_select_db($db) or die ("N&atilde;o foi poss&iacute;vel ligar &agrave; base de dados!");
	$query = "SELECT id, name FROM user WHERE userName = '$utilizador' AND password = '$ppasse'";
	$resultado = mysql_query($query) or die ("Erro na query: $query. ".mysql_error());
	if (mysql_num_rows($resultado) == 1) {
		$linha = mysql_fetch_row($resultado);
		$_SESSION['idutilizador'] = $linha[0];
		$_SESSION['nome'] = $linha[1];
		$_SESSION['utilizador'] = $_POST['utilizador'];
		echo "<meta http-equiv=\"refresh\" content=\"0;URL=menu.php\">";
	}
	else {
		$loginFailed = "Autenticação falhou!";
	}
}
?>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="decor.css" />
<title>Spy Counter Spy</title>
<script src="jquery.js"></script>
</head>
<body>
	<div class="global">
		<header>
			<div style="text-align: center">
				<h1>Spy Counter Spy</h1>
				<br />
			</div>
		</header>

		<div class="alinhamento">
			<span class="alerta"><?php echo $loginFailed;?> </span>
			<form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="post">
				<table>
					<tr>
						<td>Utilizador:</td>
						<td><input type="text" name="utilizador"
							value="<?php echo $utilizador;?>" required="required" />
						</td>
					</tr>
					<tr>
						<td>Palavra passe:</td>
						<td><input type="password" name="ppasse" required="required" /></td>
					</tr>
					<tr>
						<td><input type="submit" name="Autenticar" value="Autenticar" /></td>
					</tr>
					<tr>
						<td><a href="novoutilizador.php">Registar-se.</a></td>
					</tr>
					<tr>
						<td><a href="resetpass.php">Reset de password.</a></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<?php
	include("footer.php");
	?>