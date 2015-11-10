<%@page import="bean.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="logo.jsp"></jsp:include>  
    <!--    <hr/>  -->
<%@ taglib uri="/struts-tags" prefix="s" %> 

<link rel="stylesheet" type="text/css" href="stylelogin.css">


<br/>Sei entrato come:  <s:property value="%{#session.loggedUser.username}" />
<br/>Tipo utente: <s:property value="%{#session.loggedUser.tipoUtente}" /> 

<s:set var="tipout" value="%{#session.loggedUser.tipoUtente}" />
<br/>
<br/>

<table border="0" cellspacing="1" cellpadding="1">

    <tbody>
        <tr>
            <s:if  test="#tipout=='acli'">
                <td><form action="search">
                        <input type="submit" value="Stato Lavorazione Fogli">
                    </form></td>
                <td><form action="searchprocess">
                        <input type="submit" value="Fogli Presenza">
                    </form></td>
                <!--pulsanti visibili solo all'operatore acli *****************************************************-->

                <td><form action="anagraficadatorilavoro">
                        <input type="submit" value="Anagrafica Utenti Gold">
                    </form></td>
                <td><form action="anagraficadipendenti">
                        <input type="submit" value="Anagrafica Lavoratori">
                    </form> </td>
<!--                <td> <form action="contratto">
                        <input type="submit" value="Nuovo Contratto"> 
                    </form></td>-->
                <td> <form action="listacontratti">
                        <input type="submit" value="Lista Contratti"> 
                    </form></td>    
                <td><form action="fogliriepilogativi" >
                        <input type="submit" value="Fogli Riepilogativi">
                    </form></td>
                <!--                <td><form action="statistiche">
                                        <input type="submit" value="Statistiche">
                                    </form></td>-->
            </s:if>
            <s:else>
                <td><form action="search">
                        <input type="submit" value="Lista Lavoratori">
                    </form></td>
                <td><form action="searchprocess">
                        <input type="submit" value="Fogli Presenza">
                    </form></td>
                </s:else>

            <!--*************************************************************************************-->                 
            <td><form action="loginprocess">
                    <input type="hidden" name="logout" value="true">
                    <input type="submit" name="logoutButton" value="Logout">
                </form></td>

        </tr>
    </tbody>
</table>
<hr/>



<!--<a href="javascript:window.print()"><img src="img/printer-1xzw8z8.png" alt="print this page" id="print-button" /></a>
<br>

<SCRIPT LANGUAGE="JavaScript"> 
if (window.print) {
document.write('<form><input type=button name=print value="Stampa Pagina" onClick="window.print()"  src="img/printer-1xzw8z8.png" alt=""></form>');
}
</script>-->
