<?php
session_start();
if(!$_SESSION['idutilizador'])
{
	session_destroy();
	header("location:login.php");
}
include ("protected/db_spy.php");
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
	<div class="sair">
		<a href="logout.php">Sair</a>
	</div>
	
	<header>
		<div style="text-align: center">
			<h1>Spy Counter Spy</h1>
			<br />
		</div>
	</header>