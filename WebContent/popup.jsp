
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="/struts-tags" prefix="s" %>  
<link rel="stylesheet" type="text/css" href="stylelogin.css">

 <script>
 	function findWindow(name) {
	    return windows[name];
	}
 
	function closeAndRefresh(){
		
		//window.open("", window.location.href.split("parentWindow=")[1], '', true).location.reload(true);
		//window.setInterval(function(){alert('fatto');}, 3000);
		
		window.open("", window.location.href.split("parentWindow=")[1], '', true).location.href = window.location.href;
		
		//document.getElementById("myForm").submit;
		
		//alert(parent.location.href);
		
		//parent.document.getElementById(IdRefre)
		
		
		//window.open(parent.location.href, window.location.href.split("parentWindow=")[1]);
		
		
		
		//window.close();
		
		
		
		//windows["win1"].reload();
		//window.alert(findWindow('win1'));
		 // or self.close();
		 
		//window.setTimeout()(function (){alert("Aggiornamento effettuato!")}, 100); 
		
		// ((Window)document.getElementsByName("win1")).location.reload();
		
		//window.close();
		
		//window.parent.location.reload();
	}
	
	function chiudiPopup(){
		// window.open("", window.location.href.split("parentWindow=")[1], '', true).location.reload(true);
		window.close();
	}
	
</script>



<s:if test="%{#request.close != 'close'}">

POPUP per aggiornare i dati del giorno selezionato.
<br/>

<s:form id="myForm" action="insertValueDay" onsubmit="return closeAndRefresh();">
	<table>
		<thead>
			<tr>
				<th><s:label value="N. Ore"></s:label></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<s:set var="day" value="%{#request.day}"></s:set> 
					<s:textfield name="lav_ord" label="Lavoro Ordinario" value="%{#day.lav_ord}"></s:textfield>
				</td>
			</tr>
			<tr>
				<td><s:textfield name="lav_straord" label="Lavoro Strordinario" value="%{#day.lav_straord}"></s:textfield></td>
			</tr>
			<tr>
				<td><s:textfield name="ferie" label="Ferie" value="%{#day.ferie}"></s:textfield></td>
			</tr>
			<tr>
				<td><s:textfield name="perm_retrib" label="Permesso Retribuito" value="%{#day.perm_retrib}"></s:textfield></td>
			</tr>
			<tr>
				<td><s:textfield name="perm_non_retrib" label="Permesso Non Retribuito" value="%{#day.perm_non_retrib}"></s:textfield></td>
			</tr>
			<tr>
				<td><s:textfield name="malattia" label="Malattia" value="%{#day.malattia}"></s:textfield></td>
			</tr>
			<tr>
				<td><s:textfield name="assenza" label="Assenza non retribuita" value="%{#day.assenza}"></s:textfield></td>
			</tr>
                        <tr style="display: none">
                            <td ><s:textfield  cssStyle="display:none;" name="idcontratto" value="%{#day.idcontratto}"></s:textfield>></td>
			</tr>
                        <tr style="display: none">
                            <td><s:textfield  cssStyle="display:none;" name="idsituazione_presenze" value="%{#day.idsituazione_presenze}"></s:textfield>></td>
			</tr>
                        <tr style="display: none">
                            <td ><s:textfield cssStyle="display:none;" name="idfogli_presenze" value="%{#day.idfogli_presenze}"></s:textfield>></td>
			</tr>
			<tr>
				<td><br/><s:submit value="Inserisci Ore"></s:submit></td>      
			</tr>
		</tbody>
	</table>
</s:form>

</s:if>
<s:else>
	<h2>Aggiornamento Effettuato!</h2>
	<br/>
    <div><s:submit type="button" value="Chiudi" onclick="chiudiPopup();"/></div>
</s:else>
