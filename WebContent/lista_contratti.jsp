<%@page import="bean.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<link rel="stylesheet" type="text/css" href="stylelogin.css">

<jsp:include page="menu_amministratore.jsp"></jsp:include>  
    <!--    <hr/>  -->

    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        </head>


        <h1>Modifica Contratto</h1>
        <div style="color: blue">
            Per modificare i dati di un contratto esistente inserisci il NUMERO CONTRATTO qui sotto oppure clicca sul NUMERO CONTRATTO (#) nella tabella in basso.<br>  
            Per inserire un <strong>NUOVO CONTRATTO</strong> seleziona prima un dipendente dalla sezione Anagrafica Dipendenti.
        </div>
        <br/>
    <s:form action="viewContratto">
        <s:textfield label="N° Contratto" name="n_contratto"/>
        <s:submit class="myOKButton" align="right" value="   MODIFICA   "></s:submit>
    </s:form>
    <hr/>        

    <table style="width: 80%; background-color: white">
        <tr>
            <td style="font-size: medium"><h1>Contratti</h1></td>
            <td><form action="listacontratti"><input type="submit" value="Ordina Tabella per COGNOME"></form></td>
            <td><form action="listacontrattiprofilo"><input type="submit" value="Ordina Tabella per PROFILO"></form></td>
        </tr>
    </table>
    <body>

        <div style="height:500px; overflow:auto"> 

            <table style="width: 80%; border-bottom-color: gray; border-width: 2px;" border = 0>
                <thead class="title">
                    <tr>
                        <th>#</th>
                        <th>Cognome</th>
                        <th>Nome</th>
                        <th>Cognome Dipendente</th>
                        <th>Nome Dipendente</th>
                        <th>Data Assunzione</th>
                        <th>Data Cessazione</th>
                        <th>Profilo</th>
                    </tr>
                </thead>

                <s:iterator value="%{#request.contratti}" var="ctr" status="status">
                    <tr class="<s:if test="#status.even">colore1</s:if>
                        <s:else>colore2</s:else>
                        <s:if test="#ctr.data_cessaz != null">colore3</s:if>" >
                            
                           

                            <td>

                            <s:url action="viewContratto" var="url" escapeAmp="false">                                                      
                                <s:param name="n_contratto" value="n_contratto"/>
                            </s:url>
                            <a href="<s:property value="#url"/>"><s:property value="n_contratto"/></a></td>
                        <td><s:property value="cognome"/></td>
                        <td><s:property value="nome"/></td>
                        <td><s:property value="cognome_dip"/></td>
                        <td><s:property value="nomedip"/></td>
                        <td><s:property value="data_assunzione"/></td>
                        <td><s:property value="data_cessaz"/></td>
                        <td><s:property value="prospetto_duff"/></td>
                    </tr>
                </s:iterator>
            </table>
        </div>



        <!--        <h3><font color="red">Dati in modifica</font></h3>
        
        <s:form action="">  
            <table style=" width: 50%;  border-bottom-color: gray; border-width: 1px;" border = 1 >
                <tbody>
                    <tr>
                        <td valign="top">
                            <table border="0">
                                <thead>
                                    <tr>
                                        <th align="left">Dati Contratto</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
            <s:textfield name="indirizzo_lavoro" label="Indirizzo di lavoro" size=""></s:textfield>
            <%--<s:textfield name="cap" label="CAP" size="" maxlength="5"></s:textfield>--%>
            <s:textfield name="citta" label="Città" size=""></s:textfield>
            <s:textfield name="data_assunz" label="Data Assunzione (gg/MM/aaaa)"  size="8" ></s:textfield>
            <s:textfield name="data_cessaz" label="Data Cessazione (gg/MM/aaaa)" size="8"></s:textfield>
            <s:select label="Giorno Riposo" 
                      headerKey="-1"
                      list="#@java.util.LinkedHashMap@{'1':'lun', '2':'mar', '3':'mer','4':'gio','5':'ven','6':'sab','7':'dom'}" 
                      name="giorno_riposo" 
                      value="1" />
            <s:textfield name="prospetto_duff" label="Prospetto D'Ufficio" size="20"></s:textfield>
            </td>
        </tr>
    </tbody>
</table>

</td>
<td valign="top">
<table border="0">
    <thead>
        <tr>
            <th align="left">Tipologia</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td align="left">

            <s:select label="Tipo Contratto" 
                      headerKey="-1"
                      list="#@java.util.LinkedHashMap@{'Tempo Indeterminato':'Tempo Indeterminato', 'Tempo Determinato':'Tempo Determinato','Part Time':'Part Time'}" 
                      name="contratto" 
                      value="1" />
            <s:select label="Categoria" 
                      headerKey="-1"
                      list="#@java.util.LinkedHashMap@{'C':'C', 'NC':'NC','CP':'CP','PN':'PN','ASN':'ASN'}" 
                      name="categoria" 
                      value="1" />
            <s:select label="Livello" 
                      headerKey="-1"
                      list="#@java.util.LinkedHashMap@{'B':'B', 'BS':'BS','CS':'CS','DS':'DS','U':'U'}" 
                      name="livello" 
                      value="1" />
        </td>
    </tr>

</tbody>

</table>
</td>

</tr>

</tbody>
</table>
<br>
            <s:submit class="myOKButton" align="left" value="          OK          "></s:submit>
        </s:form>
        -->