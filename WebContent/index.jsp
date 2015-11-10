<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<link type='text/css' href='css/confirm.css' rel='stylesheet' media='screen' />


<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="js/jquery.simplemodal.js"></script>
<script type="text/javascript" src="js/confirm.js"></script>

<!DOCTYPE html>
<!-- <link rel="stylesheet" type="text/css" href="stylelogin.css"> -->
<link rel="stylesheet" type="text/css" href="style01.css">
<!-- <html>
<head> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> GOLD</title>

<img src="img/logo-cinquantaventi-w250b.png" style="width:150px;height:60px">

<h1 style="color: darkorange">Gestione Ore Lavoratori Domestici</h1>

<b>G.O.L.D. - Gestione Ore Lavoratori Domestici</b>, è un’applicazione web sviluppata da  <a href="http://www.5020.it">5020</a>  per gestire le presenze dei lavoratori domestici.<br>
E’ accessibile via Internet mediante browser.<br>
Permette la compilazione dei fogli presenza con il dettaglio delle ore lavorate e la loro tipologia (lavoro ordinario, straordinario, ferie, permessi, ecc.).<br/>
La riservatezza è assicurata in quanto l’accesso alle sue funzionalità è regolato da username e password.<br>
Semplice ed intuitiva da utilizzare.<br>
<br>
<s:url action="login.action" var="urlTag" ></s:url>
<s:a href="%{urlTag}">Accedi a GOLD</s:a>
