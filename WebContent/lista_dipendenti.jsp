<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="menu_amministratore.jsp"></jsp:include>  

    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        </head>
<!--    <c:if test="${not empty ddl && ddl=='true' }"><h1>Lista Datori Lavoro</h1></c:if>
    <c:if test="${not empty ddl && ddl=='f' }"><h1>Lista Lavoratori</h1></c:if>    -->

        <body>

        <s:form name="SearchForm" action="search" >

            <table style=" width: 60%;  border-bottom-color: gray; border-width: 2px;" border = 1 >

                <tbody>
                    <tr>

                        <c:if test="${empty ddl}">
                            <td align="center">
                                <table  >
                                    <thead>
                                        <tr><th></th><th><b>Datore di lavoro</b></th></tr>
                                        <tr></tr>
                                    </thead>
                                    <tbody>
                                        <s:textfield label="Cognome" name="searchDatSur"/>
                                        <s:textfield label="Nome" name="searchDatName"/>
                                        <s:textfield label="Zona" name="searchZone"/>
                                    </tbody>
                                </table>
                            </td>
                        </c:if>
                        <c:if test="${not empty ddl && ddl=='true' }">
                            <td align="center" style="display: none;">
                                <table  >
                                    <thead>
                                        <tr><th></th><th><b>Datore di lavoro</b></th></tr>
                                        <tr></tr>
                                    </thead>
                                    <tbody>
                                        <s:textfield label="Cognome" name="searchDatSur"/>
                                        <s:textfield label="Nome" name="searchDatName"/>
                                        <s:textfield label="Zona" name="searchZone"/>
                                    </tbody>
                                </table>
                            </td>
                        </c:if>

                        <td align="center">
                            <table >
                                <thead>
                                    <tr><th></th><th><b>Dipendente</b></th></tr>
                                    <tr></tr>
                                </thead>
                                <tbody>
                                    <s:textfield label="Cognome" name="searchDipSur"/>
                                    <s:textfield label="Nome" name="searchDipName"/>
                                </tbody>
                            </table>
                        </td>

                        <td align="center">
                            <table >
                                <thead>
                                    <tr><th></th><th><b>Foglio presenze</b></th></tr>
                                    <tr></tr>
                                </thead>
                                <tbody>
                                    <s:select label="Mese" name="searchMonth" list="#@java.util.LinkedHashMap@{'':'--','01':'Gennaio','02':'Febbraio','03':'Marzo','04':'Aprile','05':'Maggio',
                                              '06':'Giugno','07':'Luglio','08':'Agosto','09':'Settembre','10':'Ottobre','11':'Novembre','12':'Dicembre'}"/>
                                    <s:select label="Anno" name="searchYear" list="#@java.util.LinkedHashMap@{ '':'--','2015':'2015',
                                              '2016':'2016','2017':'2017','2018':'2018','2019':'2019','2020':'2020','2021':'2021',
                                              '2022':'2022','2023':'2023','2024':'2024','2025':'2025','2026':'2026','2027':'2027',
                                              '2028':'2028','2029':'2029','2030':'2030'}"/>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr><td style="text-align: left;"><input class="myOKButton" type="submit" value="Filtra risultati"></td><td></td><td></td></tr>
                </tbody>
            </table>

            <style>
                tr.odd{
                    background-color: white
                }
                tr.even{
                    background-color: #E8E8E8
                }

            </style>


        </s:form>
        <br><br>
        <c:if test="${not empty dipendenti}">

            <c:choose>
                <c:when test="${empty ddl}">
                    <table style="width: 80%; border-bottom-color: gray; border-width: 2px;" border = 1>
                        <thead>
                            <tr>
                                <th>Cognome DDL</th>
                                <th>Nome DDL</th>
                                <th>Cognome Dipendente</th>
                                <th>Nome Dipendente</th>
                                <th>Data Assunzione</th>
                                <th>N. Contratto</th>
                                <th>Mese</th>
                                <th>Anno</th>
                                <th>Stato</th>
                                <th>Profilo</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${dipendenti}" var="dip" varStatus="conta">

                                <c:set var="rowStyle" value="${(conta.index)%2 eq 0?'odd':'even'}"/>
                                <tr class="${rowStyle }">

                                    <!--<tr style="">-->
                                    <td><c:out value="${dip.cognome}" /></td>
                                    <td><c:out value="${dip.nome}" /></td>
                                    <td><c:out value="${dip.cognome_dip}" /></td>
                                    <td><c:out value="${dip.nomedip}" /></td>
                                    <td><c:out value="${dip.data_assunzione}" /></td>
                                    <td style="font-weight: bold"><c:out value="${dip.n_contratto}" /></td>
                                    <td><c:out value="${dip.mese}" /></td>
                                    <td><c:out value="${dip.anno}" /></td>
                                    <!--<td style="background-color: darkseagreen"><c:out value="${dip.stato}" /></td>-->

                                    <c:choose>
                                        <c:when test="${dip.stato eq 'approvato'}"><td style="color:green; font-weight:bold "><c:out value="${dip.stato}" /></td></c:when>
                                        <c:when test="${dip.stato eq 'lavorazione'}"><td><c:out value="${dip.stato}" /></td></c:when>
                                        <c:when test="${dip.stato eq 'inviato'}"><td style="color:red; font-weight:bold "><c:out value="${dip.stato}" /></td></c:when>
                                    </c:choose>

                                    <td><c:out value="${dip.prospetto_duff}" /></td>
                                </tr>

                            </c:forEach>

                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <table style="width: 80%; border-bottom-color: gray; border-width: 2px;" border = 1>
                        <thead>
                            <tr>
                                <th>Nome Dipendente</th>
                                <th>Cognome Dipendente</th>
                                <th>Data Assunzione</th>
                                <th>N. Contratto</th>
                                <th>Mese</th>
                                <th>Anno</th>
                                <th>Stato</th>                            
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${dipendenti}" var="dip">
                                <tr>
                                    <td><c:out value="${dip.nomedip}" /></td>
                                    <td><c:out value="${dip.cognome_dip}" /></td>
                                    <td><c:out value="${dip.data_assunzione}" /></td>
                                    <td><c:out value="${dip.n_contratto}" /></td>
                                    <td><c:out value="${dip.mese}" /></td>
                                    <td><c:out value="${dip.anno}" /></td>
                                    <td><c:out value="${dip.stato}" /></td>
                                </tr>

                            </c:forEach>

                        </tbody>
                    </table>
                </c:otherwise>   
            </c:choose>

        </c:if>
        <br>
        <br>

        <c:if test="${not empty Messaggio}">
            <%= request.getAttribute("Messaggio")%>
            <br><br>
        </c:if>

    </body>
</html>