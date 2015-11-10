<%@page import="bean.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="menu_amministratore.jsp"></jsp:include>  


<%@ taglib uri="/struts-tags" prefix="s" %>  

<link rel="stylesheet" type="text/css" href="stylelogin.css">

<jsp:include page="lista_dipendenti_anag.jsp"></jsp:include> 

<s:form action="updateanagraficadipendenti">  

    <h1>NUOVO LAVORATORE</h1>
    <table style="width: 40%; border-bottom-color: gray; border-width: 1px;" border = 1>
        <thead>
            <tr>
                <th>Dati Anagrafici</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    <s:textfield name="cognome" label="Cognome" size="40"></s:textfield>
                    <s:textfield name="nome" label="Nome"  size="40" ></s:textfield>  
                    <s:textfield name="indirizzo" label="Indirizzo" size="60"></s:textfield>
                    <s:textfield name="telefono" label="Telefono" ></s:textfield>
                    <s:textfield name="cellulare" label="Cellulare"></s:textfield>
                </tr> 
            </tbody>
        </table>
                <br>
    <s:submit class="myOKButton" align="left" value="          OK          "></s:submit> </td> 
    <br/>

<!--  <a href="contrattoaction.action?nome=&cognome=<s:property value="cognome"/>&telefono=<s:property value="telefono"/>&cellulare=<s:property value="cellulare"/>">Vai al contratto</a>-->

<hr/>  

</s:form>




