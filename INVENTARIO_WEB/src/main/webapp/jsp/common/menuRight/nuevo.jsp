<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="menu-desplegable-opcion">
	<%-- <s:if test="modeDebug || (permiso.contains('GEST'))"> --%>
		<div class="menu-desplegable-opcion">
			<s:url var="nuevoUrl" action="edit"/>
			<s:a class="opcion-nuevo-objeto"  href="%{nuevoUrl}"> Nuevo </s:a>
		</div>
	<%-- </s:if> --%>
</div>
