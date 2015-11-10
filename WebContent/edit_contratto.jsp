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
        N. contratto: <strong><s:property value="%{#request.contratto.idcontratto}"/></strong><br/>
    DATORE (cognome-nome) : <strong><s:property value="%{#request.contratto.cognomeddl}"/></strong>
    <strong><s:property value="%{#request.contratto.nomeddl}"/></strong><br/>
    LAVORATORE (cognome-nome): <strong><s:property value="%{#request.contratto.cognome}"/></strong>
    <strong><s:property value="%{#request.contratto.nome}"/></strong>
    <br/>

    <s:form action="updatecontratto">
        <s:hidden  name="idcontratto" value="%{#request.contratto.idcontratto}"/>
        <s:hidden name="cognomeddl" value="%{#request.contratto.cognomeddl}"/>
        <s:hidden name="nomeddl" value="%{#request.contratto.nomeddl}"/>
        <s:hidden name="cognome" value="%{#request.contratto.cognome}"/>
        <s:hidden name="nome" value="%{#request.contratto.nome}"/>

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
                                        <s:textfield name="indirizzolav" label="Indirizzo di lavoro" value="%{#request.contratto.indirizzolav}"></s:textfield>
                                        <s:select 	label="Città" 
                                    			list="sceltaCitta" 
                                    			name ="citta"
                                    			headerKey="-1" 
                                    			headerValue="Scegliere una città"
                                    			value="%{#request.contratto.citta}"/>
<%--                                         <s:textfield name="citta" label="Città" value="%{#request.contratto.citta}"></s:textfield> --%>
                                        <s:textfield name="data_assunz" label="Data Assunzione (gg/MM/aaaa)"  size="8" value="%{#request.contratto.data_assunz}"></s:textfield>
                                        <%--<s:datetextfield name="data_assunz" label="data ass" value="%{#request.contratto.data_assunz}" format="dd/MM/yyyy" />--%>
                                        <s:textfield name="data_cessaz" label="Data Cessazione (gg/MM/aaaa)" size="8" value="%{#request.contratto.data_cessaz}"></s:textfield>
                                        <s:select label="Giorno Riposo" 
                                                  headerKey="-1"
                                                  list="#@java.util.LinkedHashMap@{'1':'lun', '2':'mar', '3':'mer','4':'gio','5':'ven','6':'sab','7':'dom'}" 
                                                  name="giorno_riposo" 
                                                  value="%{#request.contratto.giorno_riposo}" />
                                        <s:textfield name="prospetto_duff" label="Prospetto D'Ufficio" size="20" value="%{#request.contratto.prospetto_duff}"></s:textfield>
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
                                                  name="tipo_contratto" 
                                                  value="%{#request.contratto.tipo_contratto}" />
                                        <s:select label="Categoria" 
                                                  headerKey="-1"
                                                  list="#@java.util.LinkedHashMap@{'C':'C', 'NC':'NC','CP':'CP','PN':'PN','ASN':'ASN'}" 
                                                  name="categoria" 
                                                  value="%{#request.contratto.categoria}" />
                                        <s:select label="Livello" 
                                                  headerKey="-1"
                                                  list="#@java.util.LinkedHashMap@{'B':'B', 'BS':'BS','CS':'CS','DS':'DS','U':'U'}" 
                                                  name="livello" 
                                                  value="%{#request.contratto.livello}" />
                                    </td>
                                </tr>

                            </tbody>

                        </table>
                    </td>
                </tr>

            </tbody>
        </table>
        <table  class="tableButton">
            <tbody>
                <tr>
                    <td>
                        <table  class="tableButton">
                            <tbody>
                                <tr>
                                    <td><s:submit class="myCancelButton" value="          Annulla          " action="listacontratti"/></td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                    <td>
                        <table  class="tableButton">
                            <tbody>
                                <tr>
                                    <td> <s:submit class="myOKButton" value="          Modifica          "></s:submit></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>
    </s:form>
</body>
</html>
