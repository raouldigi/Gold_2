<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="bean.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<link rel="stylesheet" type="text/css" href="stylelogin.css">

<jsp:include page="menu_amministratore.jsp"></jsp:include>  
<!--    <hr/>  -->

<jsp:include page="lista_datori_contratti.jsp"></jsp:include>  
    <br>
        <hr/>  
    <h1>Seleziona foglio riepilogativo</h1>

<s:form action="updateFoglioR">
    <table border="0">
        <tbody>
            <td><s:select label="Anno"  name="anno" list="#@java.util.LinkedHashMap@{
                          '2015':'2015',
                          '2016':'2016',
                          '2017':'2017',
                          '2018':'2018',
                          '2019':'2019',
                          '2020':'2020',
                          '2021':'2021',
                          '2022':'2022',
                          '2023':'2023',
                          '2024':'2024',
                          '2025':'2025',
                          '2026':'2026',
                          '2027':'2027',
                          '2028':'2028',
                          '2029':'2029',
                          '2030':'2030'
                          }"/>
                </td>
            <tr>
                <td><s:textfield label="Cognome" name="cognome" size="" /><s:textfield label="Nome" name="nome" size="" /></td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;<b>Oppure</b> <s:textfield label="N° Contratto" name="idcontratto"  value="0"/></td>
            </tr>
            <tr><s:submit class="myOKButton" align="right" value="   Foglio Riepilogativo   "></s:submit></tr>
        </tbody>
        
    </table>

</s:form>
<hr/>