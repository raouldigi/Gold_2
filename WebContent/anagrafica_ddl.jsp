<%@page import="bean.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<jsp:include page="menu_amministratore.jsp"></jsp:include>  
    <!--    <hr/>  -->

<%@ taglib uri="/struts-tags" prefix="s" %>  

<link rel="stylesheet" type="text/css" href="stylelogin.css">


<jsp:include page="lista_datori_anag.jsp"></jsp:include>  



<s:form action="updateanagraficaddl">  
    <h1>NUOVA UTENZA (datore di lavoro/operatore Acli) </h1>
    <table style="width: 40%; border-bottom-color: gray; border-width: 1px;" border = 1>
        <thead>
            <tr>
                <th>Dati Anagrafici</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td> 
                    <s:select  name="tipo_utente" label="Tipo Utenza" list="#@java.util.LinkedHashMap@{ 'ddl':'Datore di Lavoro','acli':'Operatore ACLI'}" value="ddl"></s:select>
                    <s:textfield name="cognome" label="Cognome" size="40" value=""></s:textfield>
                    <s:textfield name="nome" label="Nome"  size="40" value=""></s:textfield> 
                    <s:textfield name="indirizzo" label="Indirizzo" size="40" value=""></s:textfield>
                    <s:select  name="zona" label="Zona" value="--" list="#@java.util.LinkedHashMap@{ '':'--','VR00':'VR00 Verona','VR01':'VR01 Legnago','VR03':'VR03 Bovolone','VR04':'VR04 Villafranca','VR05':'VR05 San Massimo','VR09':'VR09 San Michele','VR12':'VR12 San Giovanni Lupatoto'}"></s:select>
                    <s:textfield name="telefono" label="Telefono" maxlength="10" value=""></s:textfield>
                    <s:textfield name="cellulare" label="Cellulare" maxlength="10" value=""></s:textfield>
                    <s:textfield name="email" label="Email" size="50" value=""></s:textfield>
                    </td>
            </tbody>
        </table>
        <br/>
        <table style="border-bottom-color: gray; border-width: 1px; background-color: #E8E8E8" border = 1>
            <thead>
                <tr>
                    <th>Accesso a GOLD </th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                    <s:textfield name="userid" label="Username" value=" " size="20" requiredLabel="true"></s:textfield>
                    <s:password name="password" label="Password" maxlength="20" requiredLabel="true"></s:password>
                    </td>
                </tr>
            </tbody>
        </table>
        <small><i>Utilizzare max 20 caratteri per la password</i></small>      
        <br/><p>
        <s:submit  class="myOKButton" align="left" value="       OK       "></s:submit>
        </p>
</s:form>
<hr/>
