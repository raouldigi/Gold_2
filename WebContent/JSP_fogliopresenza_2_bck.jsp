<%@page contentType="text/html" pageEncoding="UTF-8"%>




<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>
<html>

    <script type="text/javascript">
        function openPopup(URL) {
            window.open(URL, "Modifica Giornata", "height = 550, width = 500, left=300, top=200");
        }
    </script>

<!--        <link rel="stylesheet" type="text/css" href="style01.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Foglio Presenza</title>
    </head>-->

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Foglio Presenza</title>
</head>




<body>
    <!-- Gent.ma Sig./Sig.ra <br>
    MARCONI GABRIELLA<br>
    VIA SAN MARCO 28 <br> -->

    <p>

    <table border="0" width="1">
        <tbody>
            <tr>
                <td>PROSPETTO MESE DI: </td>
                <td>LAVORATORE: </td>
            </tr>
            <tr>
                <td><input type="text" name="" value="NOVEMBRE" size="30" ></td>
                <td><input type="text" name="" value="PASICHNYK NINA" size="60" /></td>
            </tr>
        </tbody>
    </table>

    <p></p>


    <table border="1" width="30">
        <thead>
            <tr>
                <th>////////////////////////////////////////</th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">01</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">02</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">03</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">04</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">05</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">06</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">07</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">08</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">09</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">10</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">11</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">12</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">13</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">14</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">15</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">16</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">17</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">18</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">19</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">20</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">21</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">22</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">23</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">24</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">25</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">26</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">27</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">28</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">29</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">30</a></th>
                <th><a href="<s:url value="#"/>" onclick="openPopup('<s:url value="updateDay.action"/>')">31</a></th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Lavoro Ordinario</td>
                <s:iterator value="%{#request.giornate}" var="giornata">
                    <td><s:property value="#giornata.lav_ord"/></td> <%--  <%= request.getAttribute("giornate") %> --%>
                </s:iterator>
            </tr>
            <tr>
                <td>Lavoro Straordinario</td>
                <s:iterator value="%{#request.giornate}" var="giornata">
                    <td><s:property value="#giornata.lav_straord"/></td>
                </s:iterator>                
            </tr>
            <tr>
                <td>Ferie</td>
                <s:iterator value="%{#request.giornate}" var="giornata">
                    <td><s:property value="#giornata.ferie"/></td>
                </s:iterator>
            </tr>
            <tr>
                <td>Permesso retribuito</td>
                <s:iterator value="%{#request.giornate}" var="giornata">
                    <td><s:property value="#giornata.perm_retrib"/></td>
                </s:iterator>
            </tr>
            <tr>
                <td>Permesso non Retribuito</td>
                <s:iterator value="%{#request.giornate}" var="giornata">
                    <td><s:property value="#giornata.perm_non_retrib"/></td>
                </s:iterator>
            </tr>
            <tr>
                <td>Malattia</td>
                <s:iterator value="%{#request.giornate}" var="giornata">
                    <td><s:property value="#giornata.malattia"/></td>
                </s:iterator>
            </tr>
            <tr>
                <td>Assenza non retribuita</td>
                <s:iterator value="%{#request.giornate}" var="giornata">
                    <td><s:property value="#giornata.assenza"/></td>
                </s:iterator>
            </tr>
            <tr>
                <td>**********************</td>
            </tr>
        </tbody>


        <table border="1" >

            <thead>
                <tr>
                    <th>PROSPETTO ORARIO LAVORATORI CONVIVENTI</th>

                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Festività lavorate nel mese</td>
                    <td>  <input type="text" name="" value="" size="20" /> </td>
                </tr>
                <tr>
                    <td>Domeninche lavorate nel mese</td>
                    <td><input type="text" name="" value="" size="20" /> </td>
                </tr>
                <tr>
                    <td>Riposi settimanali lavorati nel mese</td>
                    <td><input type="text" name="" value="" size="20" /> </td>
                </tr>
                <tr>
                    <td>Ferie godute nel mese</td>
                    <td><input type="text" name="" value="" size="20" /> </td>
                </tr>
                <tr>
                    <td>Malattia del mese</td>
                    <td><input type="text" name="" value="" size="20" /> </td>
                </tr>
                <tr>
                    <td>Ore di permesso retribuito</td>
                    <td><input type="text" name="" value="" size="20" /> </td>
                </tr>
            </tbody>
        </table>

        <p>Osservazioni:</p>

        <table>
            <tr>
                <td><textarea name="osservazioni" rows="4" cols="60"></textarea>
                </td>
            </tr>
        </table>

        <p>Data: <%= new java.util.Date() %> </p> 

       
        <table border="0" width="750">
                <tr> 
                    <td>
                        Questo prospetto DEVE essere comunicato sempre al servizio lavoro domestico dal giorno 25 al giorno 31 del mese corrente.
                        <br>È possibile comunicarlo nei seguenti modi:<br>
                        <ol>
                            <li>per fax al numero 045/5112269 (sede provinciale)</li>
                            <li>per e-mail all’indirizzo mondocolf.vr00@acli.it</li>
                        </ol>
                        <strong>Il servizio lavoro domestico s’impegna a produrre il cedolino entro il giorno 10 del mese successivo.</strong>
                        /p>Per appuntamenti e informazioni chiamare il numero 045/8065572 dalle 09:00 alle 12:00

                    </td>
                </tr>
            </table>

    </body>
</html>
