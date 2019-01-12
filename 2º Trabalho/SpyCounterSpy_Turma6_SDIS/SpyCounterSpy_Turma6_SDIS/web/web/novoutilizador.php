<?php 
include "protected/db_spy.php";
$utilizador_repetido = false;
if (isset($_POST['Criar'])) {
	$connection = mysql_connect($host, $user, $pass) or die ("N&atilde;o foi poss&iacute;vel ligar ao servidor MySQL!");
	mysql_select_db($db) or die ("N&atilde;o foi poss&iacute;vel ligar &agrave; base de dados!");
	$nome = $_POST['nome'];
	$utilizador = $_POST['utilizador'];
	$ppasse = $_POST['ppasse'];
	$mail = $_POST['mail'];
	$nascimento = $_POST['nascimento'];

	$query = "SELECT id FROM user WHERE userName='$utilizador'";
	$resultado = mysql_query($query) or die ("Erro na query: $query. ".mysql_error());
	if (mysql_num_rows($resultado) == 1) {
		$utilizador_repetido = true;
		echo "User repetido";
	}
	else {
		$query = "INSERT INTO user (name, birthdate, email, userName, password, points) VALUES ('$nome', '$nascimento', '$mail', '$utilizador', '$ppasse', 1000)";
		$resultado = mysql_query($query) or die ("Erro na query: $query. ".mysql_error());
		echo "<meta http-equiv=\"refresh\" content=\"0;URL=index.php\">";
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
		<?php 
		if($utilizador_repetido) {
			echo "<p><span class='alerta'>Utilizador já existe, por favor escolha outro!</span></p>";
		}
		?>

		<div class="alinhamento">
			<form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="post">
				<table>
					<caption>Por favor preencha os campos seguintes para se registar no
						jogo.</caption>
					<tr>
						<td>Nome:</td>
						<td><input type="text" size="50" name="nome" required="required" />
						</td>
					</tr>
					<tr>
						<td>Utilizador:</td>
						<td><input type="text" size="50" name="utilizador" id="utilizador"
							required="required" />
						</td>
					</tr>
					<tr>
						<td>Palavra passe:</td>
						<td><input type="password" size="50" name="ppasse" required="required" /></td>
					</tr>
					<tr>
						<td>Endereço electr&oacute;nico:</td>
						<td><input type="email" size="50" name="mail" required="required" /></td>
					</tr>
					<tr>
						<td>Data de nascimento:</td>
						<td><input type="date" size="50" name="nascimento" required="required" /></td>
					</tr>
					<tr>
						<td><input type="submit" name="Criar" value="Criar" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<?php
	include("footer.php");
	?>