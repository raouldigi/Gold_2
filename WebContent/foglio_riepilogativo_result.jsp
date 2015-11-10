<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="bean.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<link rel="stylesheet" type="text/css" href="stylelogin.css">

<jsp:include page="menu_amministratore.jsp"></jsp:include>  
    <!--    <hr/>  -->

    <h1>FOGLIO RIEPILOGATIVO <s:property value="%{#request.foglioR[0].anno}"/> di  <s:property value="%{#request.foglioR[0].cognome}"/> <s:property value="%{#request.foglioR[0].nome}"/></h1>

<style>
    td.grass {
        font-weight: bold;
    }
</style>

<table  style="border-width: 1px" border = 0>
    <tbody>
        <tr>
            <td>Cognome</td><td class="grass"><s:property value="%{#request.foglioR[0].cognome}"/></td>
        </tr>
        <tr>
            <td>Nome</td><td class="grass"><s:property value="%{#request.foglioR[0].nome}"/></td>
        </tr>
        <tr>
            <td>Cognome dipendente</td><td class="grass"><s:property value="%{#request.foglioR[0].nomedip}"/></td>
        </tr>
        <tr>
            <td>Nome dipendente</td><td class="grass"> <s:property value="%{#request.foglioR[0].cognome_dip}"/></td>
        </tr>
        <tr>
            <td>Data Assunzione</td><td class="grass"> <s:property value="%{#request.foglioR[0].data_assunzione}"/></td>
        </tr>
    </tbody>
</table>

<br/>

<div class="CSSTableGenerator" >
<table>
      <tr>
            <td>MESE</td>
            <td>DATA INVIO</td>
        </tr>

        <s:iterator value="%{#request.foglioR}" status="status">
            <s:if test="#status.count==1">
                <s:set var="cognomedat" value="cognome"/>
                <s:set var="nomedat" value="nome"/>
                <s:set var="cognomedipe" value="cognome_dip"/>
                <s:set var="nomedipe" value="nomedip"/>
                <s:set var="dataassunz" value="data_assunz"/>
            </s:if>

            <tr>

                <s:if test="%{mese==1}" >
                    <td>GENNAIO</td>
                    <td><s:property value="data_confacli"/></td>
                </s:if>
                <s:if test="%{mese==2}" >
                    <td>FEBBRAIO </td>
                    <td><s:property value="data_confacli"/></td>
                </s:if>
                <s:if test="%{mese==3}" >
                    <td>MARZO</td>
                    <td><s:property value="data_confacli"/></td>
                </s:if>
                <s:if test="%{mese==4}" >
                    <td>APRILE</td>
                    <td><s:property value="data_confacli"/></td>
                </s:if>
                <s:if test="%{mese==5}" >
                    <td>MAGGIO</td>
                    <td><s:property value="data_confacli"/></td>
                </s:if>
                <s:if test="%{mese==6}" >
                    <td>GIUGNO</td>
                    <td><s:property value="data_confacli"/></td>
                </s:if>
                <s:if test="%{mese==7}" >
                    <td>LUGLIO: </td>
                    <td><s:property value="data_confacli"/></td>
                </s:if>
                <s:if test="%{mese==8}" >
                    <td>AGOSTO</td>
                    <td><s:property value="data_confacli"/></td>
                </s:if>
                <s:if test="%{mese==9}" >
                    <td>SETTEMBRE</td>
                    <td><s:property value="data_confacli"/></td>
                </s:if>
                <s:if test="%{mese==10}" >
                    <td>OTTOBRE</td>
                    <td><s:property value="data_confacli"/></td>
                </s:if>
                <s:if test="%{mese==11}" >
                    <td>NOVEMBRE</td>
                    <td><s:property value="data_confacli"/></td>
                </s:if>
                <s:if test="%{mese==12}" >
                    <td>DICEMBRE</td>
                    <td><s:property value="data_confacli"/></td>
                </s:if>

            </tr>

        </s:iterator>
    
</table>
</div>    
    
    
<br>

<br>
CUD: <input type="checkbox" name="CUD" value="ON" />
<br>
<br>
NOTE:<br>
<textarea name="NOTE" rows="9" cols="60">
</textarea>


<!-- ***************************************************************************************-->
<!--<h1>&nbsp;tabella appoggio select datori</h1>
<div style="height:200px; overflow:auto"> 
    <table style="width: 80%; border-bottom-color: gray; border-width: 2px;" border = 1>
        <thead>
            <tr>
                <th>Cognome</th>
                <th>Nome</th>
                <th>Cognome Dipendente</th>
                <th>Nome Dipendente</th>
                <th>Data Assunzione</th>
                <th>Data Conferma</th>
                <th>Mese</th>
                <th>conta</th>
            </tr>
        </thead>
<s:iterator value="%{#request.foglioR}" status="status">
    <tr class="<s:if test="#status.even">colore1</s:if><s:else>colore2</s:else>">
        <td><s:property value="cognome"/></td>
        <td><s:property value="nome"/></td>
        <td><s:property value="cognome_dip"/></td>
        <td><s:property value="nomedip"/></td>
        <td><s:property value="data_assunzione"/></td>
        <td><s:property value="data_confacli"/></td>
        <td><s:property value="mese"/></td>
        <td><s:property value="#status.count"/></td>
    </tr>
</s:iterator>
</table>
</div>
-->

