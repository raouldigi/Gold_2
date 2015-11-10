<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>

    <h1>&nbsp;Lista Datori Lavoro e Operatori ACLI</h1>
    Click su <img src="img/pencil_icon&16.png" style="width:15px;height:15px"> per modificare i dati anagrafici di un utente.<br/>
    <body>

        <div style="height:300px; overflow:auto"> 

            <table style="width: 90%; border-bottom-color: gray; border-width: 1px;" border = 0>
                <thead class="title">
                    <tr>
                        <th>&nbsp;</th>
                        <th>Cognome</th>
                        <th>Nome</th>
                        <th>Indirizzo</th>
                        <th>Zona</th>
                        <th>Telefono</th>
                        <th>Cellulare</th>
                        <th>UserName</th>
                        <!--<th>Password</th>-->
                        <th>Email</th>
                        <th>Tipo</th>
                        <th>Edit Fogli Presenza</th>
                    </tr>
                </thead>


                <s:iterator value="%{#request.datori}" status="status">
                    <tr class="<s:if test="#status.even">colore1</s:if><s:else>colore2</s:else>">
                            <td>
                            <s:url action="editDatore" var="url" escapeAmp="false">
                                <s:param name="idanagrafica_ddl" value="idanagrafica_ddl"/>
                                <s:param name="cognome" value="cognome"/>
                                <s:param name="nome" value="nome"/>
                                <s:param name="indirizzo" value="indirizzo"/>   
                                <s:param name="zona" value="zona"/>       
                                <s:param name="telefono" value="telefono"/>  
                                <s:param name="cellulare" value="cellulare"/>   
                                <s:param name="userid" value="userid"/>      
                                <s:param name="password" value="password"/>
                                <s:param name="email" value="email"/>       
                                <s:param name="tipo_utente" value="tipo_utente"/>
                            </s:url>
                            <a href="<s:property value="#url"/>"><img src="img/pencil_icon&16.png" style="width:10px;height:10px"></a>
                        </td>
                        <td><s:property value="cognome"/>    </td>
                        <td><s:property value="nome"/>          </td>
                        <td><s:property value="indirizzo"/>       </td>
                        <td><s:property value="zona"/>           </td>
                        <td><s:property value="telefono"/>      </td>
                        <td><s:property value="cellulare"/>     </td>
                        <td><s:property value="userid"/>        </td>
                        <!--<td><s:property value="password"/>  </td>-->
                        <td><s:property value="email"/>         </td>
                        <td><s:property value="tipo_utente"/>         </td>
                        <s:if test="tipo_utente=='ddl'"><td>
                        <s:url action="searchprocessSudo" var="url2" escapeAmp="false">
                                <s:param name="userid" value="userid"/>      
                                <s:param name="email" value="email"/>     
                            </s:url>
                            <a href="<s:property value="#url2"/>"><s:property value="cognome"/></a>
                        </td></s:if>
                    </tr>
                </s:iterator>
            </table>
        </div>

    </body>
</html>

