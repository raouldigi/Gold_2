<%-- 
    Document   : annullaOkitButtom
    Created on : Oct 26, 2015, 4:14:44 PM
    Author     : gminardi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
 /************************tabella struts per bottoni*********************/       
        <table  class="tableButton">
            <tbody>
                <tr>
                    <td>
                        <table  class="tableButton">
                            <tbody>
                                <tr>
                                    <td><s:submit value="          Annulla          " action="listacontratti"/></td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                    <td>
                        <table  class="tableButton">
                            <tbody>
                                <tr>
                                    <td> <s:submit cssClass="okButton" value="          Modifica          "></s:submit></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>
        
        
    </body>
</html>
