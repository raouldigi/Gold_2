<%@page import="bean.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<jsp:include page="menu_amministratore.jsp"></jsp:include>
    <!--<hr/>-->

<%@ taglib uri="/struts-tags" prefix="s"%>
<link rel="stylesheet" type="text/css" href="stylelogin.css">

<script type="text/javascript">
    function valida() {
        var e = document.getElementById("idcontract");

        /* alert(e.selectedIndex); */

        /* var idcontract = e.options[e.selectedIndex].value;
         e = document.getElementById("idmonth");
         var idmonth = e.options[e.selectedIndex].value;
         e = document.getElementById("idyear");
         var idyear = e.options[e.selectedIndex].value; */

        if (e.selectedIndex == -1) {
            alert("Nessun contratto selezionato/disponibile");
        } else {
            document.getElementById("foglioForm").submit();
        }
    }
</script>

<h1>
    &nbsp;
    <s:text name="sel_fp" />
</h1>
<table style="width: 400px; border: 1px solid #bbb">
    <tbody>
    <thead>
        <tr>
            <th>&nbsp;Fogli presenza in stato di INVIATO o APPROVATO</th>
        </tr>
    </thead>
    <tr>
        <td><s:form action="foglioPresenze" id="foglioForm">
                <s:hidden id="accessType" name="accessType" value="%{accessType}"/>
                <s:hidden id="email" name="email" value="%{email}"/>
                <s:property value="accessType"/>
                <s:select id="idcontract" label="N° Contratto" name="contratto"
                          list="operatorList" />
                <s:select id="idyear" label="Anno" name="anno"
                          list="#@java.util.LinkedHashMap@{
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
                          }" />
                <s:select id="idmonth" label="Mese" name="mese"
                          list="#@java.util.LinkedHashMap@{'1':'Gennaio','2':'Febbraio','3':'Marzo','4':'Aprile','5':'Maggio',
                          '6':'Giugno','7':'Luglio','8':'Agosto','9':'Settembre','10':'Ottobre','11':'Novembre','12':'Dicembre'}" />
                <br>
                <%-- <s:submit value="Visualizza Foglio Presenza" onclick="valida();"/> --%>
                <s:hidden id="confermato" name="confermato" />

            </s:form></td>
    </tr>
    <tr>
        <td align="right">
            <div>
                <br />
                <button class="myOKButton" value="Visualizza Foglio Presenza" onclick="valida()">Visualizza Foglio Presenza</button>
            </div>
        </td>
    </tr>

</tbody>
</table>


<br>

<h1>N.Contratto e Nominativo Lavoratore</h1>
<table
    style="width: 700px; background-color: highlight; border: 1px solid #bbb">
    <!--    <thead>
<th>N.Contratto e Nominativo</th>
<tr>-->
    <td><s:iterator value="nomedipeList">
            <s:property />
            <br>
        </s:iterator></td>
</tr>
</thead>
</table>
<br/>

<s:if test="!(dipendenti.isEmpty())"><jsp:include page="lista_lavorazione.jsp"></jsp:include></s:if>
<s:else><h4 style="color:red">Non sono presenti fogli presenza per il Datore di Lavoro selezionato</h4><hr/></s:else>



<c:if test="${confirm_message != null}">

    <div id='confirm'>
        <div style="top: 0px;" class='header'>
            <span>Conferma</span>
        </div>
        <div class='message'>"E' stato selezionato un nuovo Foglio
            Presenze per il contratto in questione. Conferma la creazione?"</div>
        <div class='buttons' style="width: 100%">
            <div class='no simplemodal-close'>No</div>
            <div class='yes'>Yes</div>
        </div>
    </div>

    <script>
        confirm("", function () {
            document.getElementById("confermato").setAttribute("value",
                    "confermato");
            document.getElementById("foglioForm").submit();
        });
    </script>

</c:if>
