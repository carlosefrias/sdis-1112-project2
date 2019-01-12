<?php
include("header.php");
?>
<script type="text/javascript">
	function item(valor) {
		document.getElementById("desc").innerHTML = "Adquirir " + valor
				+ " pontos";
	}

	function itemcomprar(valor) {
		document.getElementById("desc").innerHTML = "Compra iniciada!";
		$.ajax({
					type : "POST",
					url : "comprar.php",
					data : {
						item : valor,
						user : $_SESSION['idutilizador']
					}
				})
				.done(function() {
							document.getElementById("desc").innerHTML = "Obrigada pela sua aquisição!";
						});
	}

	function limpar() {
		document.getElementById("desc").innerHTML = "";
	}
</script>
<section id="desc" style="float: right;">
</section>

<section style="float: none;">
<div class="alinhamento">
<table class="tabelaLista">
	<tr><td><a onmouseover="item(100)" onclick="itemcomprar(100)" onmouseout="limpar()">100pts</a></td></tr>
	<tr><td><a onmouseover="item(200)" onclick="itemcomprar(200)" onmouseout="limpar()">200pts</a></td></tr>
	<tr><td><a onmouseover="item(300)" onclick="itemcomprar(300)" onmouseout="limpar()">300pts</a></td></tr>
	<tr><td><a onmouseover="item(400)" onclick="itemcomprar(400)" onmouseout="limpar()">400pts</a></td></tr>
	<tr><td><a onmouseover="item(500)" onclick="itemcomprar(500)" onmouseout="limpar()">500pts</a></td></tr>
</table>
<p><a href="menu.php">Voltar ao Menu</a></p>
</div>
</section>
<?php
include("footer.php");
?>