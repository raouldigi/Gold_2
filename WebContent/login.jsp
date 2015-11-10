<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" type="text/css" href="style01.css">

<%@ taglib uri="/struts-tags" prefix="s" %>  

<jsp:include page="index.jsp"></jsp:include>  

    <p/>

<%--<s:form action="loginprocess" >
    <s:textfield name="username" label="Nome"></s:textfield>  
    <s:password name="userpass" label="Password"></s:password>  
    <s:submit value="          login          "></s:submit>  
</s:form>--%>


<s:form action="loginprocess" >
    <s:textfield key="username"/>
    <s:password key="userpass" />
    <s:submit class="myOKButton" value="      login      "/>
</s:form>