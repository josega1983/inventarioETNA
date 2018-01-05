<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ETNAJ</title>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
</head>
<body>
	<p>
		Bienvenido al portal de aplicaciones de ETNAJ
	</p>
	<div>
		<s:url id="welcomelUrl" namespace="/welcome" action="authenticate"/>
		<s:a theme="simple" cssClass="menu-opcion" href="%{welcomelUrl}">
		INVENTARIO</s:a>
		<br/>
	</div>
</body>
<head>
</html>