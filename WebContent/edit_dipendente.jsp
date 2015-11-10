<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="bean.Dipendente" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="menu_amministratore.jsp"></jsp:include>  
    <!--    <hr/>  -->

<%@ taglib uri="/struts-tags" prefix="s" %>  

<link rel="stylesheet" type="text/css" href="stylelogin.css">
<style>
    .id{
        color: blue;
    }

</style>


<s:form action="okEditDipendente">  
    <h1>MODIFICA Dipendente </h1>
    <table border="0">
        <thead>
            <tr>
                <th>Dati Anagrafici</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <%--  <td>Id</td> 
                  <td><s:property value="idanagrafica_dipe" />
                  </td> --%>


                <td  colspan="2" >
                    <s:textfield cssClass="id" name="idanagrafica_dipe" label="ID" readonly="true" size="5"/>  
                    <s:textfield name="nome" label="Nome"  size="40" /> 
                    <s:textfield name="cognome" label="Cognome" size="40" />
                    <s:textfield name="indirizzo" label="Indirizzo" size="40"/>
                    <s:textfield name="telefono" label="Telefono" />
                    <s:textfield name="cellulare" label="Cellulare"/>


                    <%-- <s:textfield name="email" label="Email" />--%>
                </td>
            </tr>
        </tbody>
    </table>
    <table  class="tableButton">
        <tbody>
            <tr>
                <td>
                    <table  class="tableButton">
                        <tbody>
                            <tr>
                                <td><s:submit class="myCancelButton" value="          Annulla          " action="anagraficadipendenti"/></td>
                            </tr>
                        </tbody>
                    </table>
                </td>
                <td>
                    <table  class="tableButton">
                        <tbody>
                            <tr>
                                <td> <s:submit class="myOKButton" value="             OK             "></s:submit></td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </tbody>
        </table>




</s:form>