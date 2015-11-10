<%@page import="bean.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<jsp:include page="menu_amministratore.jsp"></jsp:include>  
    <!--    <hr/>  -->

<%@ taglib uri="/struts-tags" prefix="s" %>  

<link rel="stylesheet" type="text/css" href="stylelogin.css">




<s:form action="okEditDatore">  
    <h1>MODIFICA Utenza Gold </h1>
    <table border="1">
        <thead>
            <tr>
                <th>Dati Anagrafici</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td colspan="2">
                    <%--<s:property value="idanagrafica_ddl" />--%>
                    <%--<s:hidden name="idanagrafica_ddl" value="%{idanagrafica_ddl}"/>--%>
                    <s:textfield class="myfield" name="idanagrafica_ddl" label="ID" size="5" readonly="true"/>
                    <s:textfield name="cognome" label="Cognome" size="40"/>
                    <s:textfield name="nome" label="Nome"  size="40"/> 
                    <s:textfield name="indirizzo" label="Indirizzo" size="40"/>
                    <s:select  name="zona" label="Zona" list="#@java.util.LinkedHashMap@{ '':'--','VR00':'VR00 Verona','VR01':'VR01 Legnago','VR03':'VR03 Bovolone','VR04':'VR04 Villafranca','VR05':'VR05 San Massimo','VR09':'VR09 San Michele','VR12':'VR12 San Giovanni Lupatoto'}"></s:select>
                    <s:textfield name="telefono" label="Telefono"/>
                    <s:textfield name="cellulare" label="Cellulare"/>
                    <s:textfield name="userid" label="UserId"/>
                    <s:select  name="tipo_utente" label="Tipo Utenza" list="#@java.util.LinkedHashMap@{ 'ddl':'Datore di Lavoro','acli':'Operatore ACLI'}"></s:select>
                    <s:textfield name="password" label="Password"/>
                    <s:textfield name="email" label="Email" />
                </td>
            </tr>
            <!--            <tr>
                            <td colspan="1">
                                <input type="submit"  value="      OK      "/>
            <%--<s:submit value="      OK      "/>--%>
        </td>
        <td colspan="1">
            <input type="submit"  name="action:cancelEditDatore" value="   Annulla   "/>
            <%--<s:submit value="   Annulla   " action="cancelEditDatore"/>--%>
        </td>    
    </tr>-->
        </tbody>
    </table>
    <table  class="tableButton">
        <tbody>
            <tr>
                <td>
                    <table  class="tableButton">
                        <tbody>
                            <tr>
                                <td><s:submit class="myCancelButton" value="Annulla" action="anagraficadatorilavoro"/></td>
                            </tr>
                        </tbody>
                    </table>
                </td>
                <td>
                    <table  class="tableButton">
                        <tbody>
                            <tr>
                                <td> <s:submit  class="myOKButton" value="     OK     "></s:submit></td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </tbody>
        </table>







</s:form>


