<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <!--<hr />-->
    <h1>Lista Lavoratori</h1>
    Click su <img src="img/pencil_icon&16.png" style="width:15px;height:15px"> per modificare i dati anagrafici di un lavoratore.<br/>
    Click sul cognome del lavoratore per selezionarlo e avviare la creazione di un <strong>NUOVO CONTRATTO.</strong><br/>
        <br/>
    <body>

        <div style="height:300px; overflow:auto"> 

        <table style="width: 60%; border-bottom-color: gray; border-width: 2px;" border = 0>
            <thead class="title">
                    <tr>
                        <th>&nbsp;</th>
                        <th>Cognome</th>
                        <th>Nome</th>
                        <th>Indirizzo</th>
                        <th>Telefono</th>
                        <th>Cellulare</th>
                    </tr>
                </thead>

                <s:iterator value="%{#request.dipendenti}" status="status">
                    <tr class="<s:if test="#status.even">colore1</s:if><s:else>colore2</s:else>">
                   <td>
                            <s:url action="editDipendente" var="url" escapeAmp="false">
                                <s:param name="idanagrafica_dipe" value="idanagrafica_dipe"/>
                                <s:param name="cognome" value="cognome"/>
                                <s:param name="nome" value="nome"/>
                                <s:param name="indirizzo" value="indirizzo"/>
                                <s:param name="telefono" value="telefono"/>  
                                <s:param name="cellulare" value="cellulare"/>
                            </s:url>  
                           <a href="<s:property value="#url"/>"><img src="img/pencil_icon&16.png" style="width:10px;height:10px"></a>
                   </td>
                          
                       <td>
                            <s:url action="datorinuovocontratto" var="url" escapeAmp="false">
                                <s:param name="cognomedip" value="cognome"/>
                                <s:param name="nomedip" value="nome"/>
                            </s:url>
                            <a href="<s:property value="#url"/>"> <s:property value="cognome"/></a>
                        </td>
                        <td><s:property value="nome"/></td>
                        <td><s:property value="indirizzo"/></td>
                        <td><s:property value="telefono"/></td>
                        <td><s:property value="cellulare"/></td>
                    </tr>
                </s:iterator>
                  
            </table>
        </div>

    </body>
</html>
<hr/>