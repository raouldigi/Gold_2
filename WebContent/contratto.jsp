<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="/struts-tags" prefix="s" %> 

<jsp:include page="menu_amministratore.jsp"></jsp:include>  
    <!--    <hr/>  -->

    <link rel="stylesheet" type="text/css" href="style01.css">

    <h1> NUOVO CONTRATTO </h1>
    <h4>Il Datore di Lavoro può essere selezionato cliccando sul <strong>cognome</strong> nella tabella sottostante.</h4>

<s:form action="newcontratto">  

    <s:hidden name="cognomedip" value="cognomedip" />
    <s:hidden name="nomedip" value="nomedip" />
    <s:hidden name="cognomeddl" value="cognomeddl" />
    <s:hidden name="nomeddl" value="nomeddl" />
    <s:hidden name="email" value="email" />



    <table style=" width: 60%;  border-bottom-color: gray; border-width: 1px;" border = 1 >
        <tbody>
            <tr>
                <td valign="top">
                    <table border="0">
                        <thead>
                            <tr>
                                <th align="left">Dipendente (cognome/nome)</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <h2 style="color:crimson"><s:property value="cognomedip"/>&nbsp;<s:property value="nomedip" /></h2>
                                                                       
                                    <s:hidden name="cognome_dip" value="%{cognomedip}"></s:hidden>
                                    <s:hidden name="nome_dip" value="%{nomedip}"></s:hidden>
                                       
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                    <td valign="top">
                        <table border="0">
                            <thead>
                                <tr>
                                    <th align="left">Datore Di Lavoro (cognome/nome)</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <h2 style="color:crimson"><s:property value="cognomeddl"/>&nbsp;<s:property value="nomeddl" /></h2>
                                    <br/><h2><s:property value="email"/></h2>
                                    <s:hidden name="cognome" value="%{cognomeddl}"></s:hidden>
                                    <s:hidden name="nome" value="%{nomeddl}"></s:hidden>

                                    <!--                                    
                                    <s:textfield name="cognomeddl" label="Cognome" size=""></s:textfield>
                                    <s:textfield name="nomeddl" label="Nome" size="" ></s:textfield>
                                    <s:textfield name="email" label="Email" size=""></s:textfield>
                                        -->
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>

            </tbody>
        </table>
        <br>
        <s:if test="cognomeddl!=null">
        <table style=" width: 60%;  border-bottom-color: gray; border-width: 1px;" border = 1 >
            <tbody>
                <tr>
                    <td valign="top">
                        <table border="0">
                            <thead>
                                <tr>
                                    <th align="left">Dati Contratto</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                    <s:textfield name="indirizzolav" label="Indirizzo di lavoro" size=""></s:textfield>
                                    <%--<s:textfield name="cap" label="CAP" size="" maxlength="5"></s:textfield>--%>
<%--                                     <s:textfield name="citta" label="Città" size=""></s:textfield> --%>
                                    <s:select 	label="Città" 
                                    			list="sceltaCitta" 
                                    			name ="citta"
                                    			headerKey="-1" 
                                    			headerValue="Scegliere una città"/>
                                    <s:textfield name="data_assunz" label="Data Assunzione (gg/MM/aaaa)"  size="8" ></s:textfield>
                                    <%--<s:textfield name="data_cessaz" label="Data Cessazione (gg/MM/aaaa)" size="8"></s:textfield>--%>
                                    <s:select label="Giorno Riposo" 
                                              headerKey="-1"
                                              list="#@java.util.LinkedHashMap@{'1':'lun', '2':'mar', '3':'mer','4':'gio','5':'ven','6':'sab','7':'dom'}" 
                                              name="giorno_riposo" 
                                              value="1" />
                                    <s:textfield name="prospetto_duff" label="Prospetto D'Ufficio" size="20"></s:textfield>
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                    </td>
                    <td valign="top">
                        <table border="0">
                            <thead>
                                <tr>
                                    <th align="left">Tipologia</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td align="left">

                                    <s:select label="Tipo Contratto" 
                                              headerKey="-1"
                                              list="#@java.util.LinkedHashMap@{'Tempo Indeterminato':'Tempo Indeterminato', 'Tempo Determinato':'Tempo Determinato','Part Time':'Part Time'}" 
                                              name="tipo_contratto" 
                                              value="1" />
                                    <s:select label="Categoria" 
                                              headerKey="-1"
                                              list="#@java.util.LinkedHashMap@{'C':'C', 'NC':'NC','CP':'CP','PN':'PN','ASN':'ASN'}" 
                                              name="categoria" 
                                              value="1" />
                                    <s:select label="Livello" 
                                              headerKey="-1"
                                              list="#@java.util.LinkedHashMap@{'B':'B', 'BS':'BS','CS':'CS','DS':'DS','U':'U'}" 
                                              name="livello" 
                                              value="1" />
                                </td>
                            </tr>

                        </tbody>

                    </table>
                </td>
                <s:submit class="myOKButton" value="          OK          "></s:submit>
                </tr>

            </tbody>
        </table>
        </s:if>
        <br>
       
</s:form>
<hr/>
<jsp:include page="lista_datori_newcontr.jsp"></jsp:include>  