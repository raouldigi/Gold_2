<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>

    <h1>Datori di Lavoro</h1>
    <body>

        <div style="height:300px; overflow:auto"> 

            <table style="width: 80%; border-bottom-color: gray; border-width: 1px;" border = 0>
                <thead class="title">
                    <tr>
                        <th>Cognome</th>
                        <th>Nome</th>
                        <th>Indirizzo</th>
                        <th>Email</th>
                        <th>Tipo</th>
                    </tr>
                </thead>


                <s:iterator value="%{#request.datori}" status="status">
                    <tr class="<s:if test="#status.even">colore1</s:if><s:else>colore2</s:else>">
                            <td>
                            <s:url action="sceltaDatore" var="url" escapeAmp="false">
                                <s:param name="cognomeddl" value="cognome"/>
                                <s:param name="nomeddl" value="nome"/>
                                <s:param name="email" value="email"/>
                                <s:param name="cognomedip" value="cognomedip"/>
                                <s:param name="nomedip" value="nomedip"/>
                            </s:url>
                            <a href="<s:property value="#url"/>"><s:property value="cognome"/>    </a>
                        </td>
                        <td><s:property value="nome"/>          </td>
                        <td><s:property value="indirizzo"/>       </td>
                        <td><s:property value="email"/>         </td>
                        <td><s:property value="tipo_utente"/></td>
                    </tr>
                </s:iterator>
            </table>
        </div>



    </body>

</html>
<hr/>
