<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
   
    <h1>&nbsp;Datori di Lavoro e Contratti</h1>
    <p><b><font color="gray">&nbsp;Tabella contratti con foglio presenze attivato</font></b></p>
    <body>

        <div style="height:300px; overflow:auto"> 

            <table style="width: 80%; border-bottom-color: gray; border-width: 2px;" border = 0>
                <thead class="title">
                    <tr>
                        <th>Cognome</th>
                        <th>Nome</th>
                        <th>Cognome Dipendente</th>
                        <th>Nome Dipendente</th>
                        <th>Data Assunzione</th>
                        <th>Numero Contratto</th>                     
                    </tr>
                </thead>

                <s:iterator value="%{#request.datori}" status="status">
                    <tr class="<s:if test="#status.even">colore1</s:if><s:else>colore2</s:else>">
                        <td><s:property value="cognome"/></td>
                        <td><s:property value="nome"/></td>
                        <td><s:property value="cognome_dip"/></td>
                        <td><s:property value="nomedip"/></td>
                        <td><s:property value="data_assunzione"/></td>
                        <td><s:property value="n_contratto"/></td>
                    </tr>
                </s:iterator>
            </table>
        </div>

    </body>
</html>
