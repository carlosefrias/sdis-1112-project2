<?php
include("protected/db_spy.php");
$dados_invalidos = false;

if (isset($_POST['Alterar'])) {
	$connection = mysql_connect($host, $user, $pass) or die ("N&atilde;o foi poss&iacute;vel ligar ao servidor MySQL!");
	mysql_select_db($db) or die ("N&atilde;o foi poss&iacute;vel ligar &agrave; base de dados!");
	$nome = $_POST['nome'];
	$utilizador = $_POST['utilizador'];
	$ppasse = $_POST['ppasse'];
	$mail = $_POST['mail'];
	$nascimento = $_POST['nascimento'];

	$query = "SELECT id FROM user WHERE userName='$utilizador' AND name='$nome' AND email='$mail' AND birthdate='$nascimento'";
	$resultado = mysql_query($query) or die ("Erro na query: $query. ".mysql_error());
	if (mysql_num_rows($resultado) == 1) {
		$linha = mysql_fetch_row($resultado);
		$query = "UPDATE user SET password='$ppasse' WHERE id ='$linha[0]'";
		$resultado = mysql_query($query) or die ("Erro na query: $query. ".mysql_error());
		echo "<meta http-equiv=\"refresh\" content=\"0;URL=index.php\">";
	}
	else {
		$dados_invalidos = true;
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
			<?php 
			if($dados_invalidos) {
				echo "<p><span class=\"alerta\">Dados inválidos!</span></p>";
			}
			?>
			<form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="post">
				<table>
					<caption>Preencha todos os campos conforme os dados do seu registo
						para definir a nova password.</caption>
					<tr>
						<td>Utilizador:</td>
						<td><input type="text" name="utilizador" required="required" />
						</td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" name="nome" required="required" />
						</td>
					</tr>
					<tr>
						<td>Endereço electr&oacute;nico:</td>
						<td><input type="email" name="mail" required="required" /></td>
					</tr>
					<tr>
						<td>Data de nascimento:</td>
						<td><input type="date" name="nascimento" required="required" /></td>
					</tr>
					<tr>
						<td>Nova Palavra passe:</td>
						<td><input type="password" name="ppasse" required="required" /></td>
					</tr>
					<tr>
						<td><input type="submit" name="Alterar" value="Alterar password" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<?php
include("footer.php");
?>