<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>

    
    <h1>&nbsp;Lista Fogli Presenza</h1>

    <body>

        <div style="height:600px; overflow:auto"> 

            <table style="width: 80%; border-bottom-color: #E8E8E8; border-width: 1px;" border = 1>

                <thead class="title">
                    <tr>
                        <th>Cognome</th>
                        <th>Nome</th>
                        <th>Cognome Dipendente</th>
                        <th>Nome Dipendente</th>
                        <th>Data Assunzione</th>
                        <th>Numero Contratto</th>                     
                        <th>Mese</th>
                        <th>Anno</th>
                        <th>Stato</th>
                        <th>Profilo</th>
                    </tr>
                </thead>

                <s:iterator value="%{#request.dipendenti}" status="status" var="dipe">

                    <tr class="<s:if test="stato=='inviato' "></s:if><s:if test="#status.even">colore1</s:if><s:else>colore2</s:else>">

                                    <td><s:property value="cognome"/></td>
                                    <td><s:property value="nome"/></td>
                                    <td><s:property value="cognome_dip"/></td>
                                    <td><s:property value="nomedip"/></td>
                                    <td><s:property value="data_assunzione"/></td>
                                    <td><s:property value="n_contratto"/></td>
                                    <td><s:property value="mese"/></td>
                                    <td><s:property value="anno"/></td>
                                    <td class="<s:if test="stato=='inviato' ">colorefont</s:if>"><s:property value="stato"/></td>
                                    <td><s:property value="prospetto_duff"/></td>
                    </tr>
                </s:iterator>
            </table>

        </div>

    </body>
</html>
