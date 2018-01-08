<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="logo">
    <img src="${pageContext.request.contextPath}/img/common-enaire/logo.png" alt="">
   <%--  <span class ="aplicacion"><s:property value="getText('aplicacion.nombre')"/></span> --%>
    <span class="version"><s:property value="getText('aplicacion.version')"/></span>
</div>

<div class="migas">
	<s:iterator value="migas" status="migaStatus">
		<s:if test="#migaStatus.count != migas.size">
			<div class="miga">
				<s:url var="enlace" namespace="/migas" action="clickMiga" >
					<s:param name="idMigaSeleccionada" value="%{#migaStatus.index}"/>
				</s:url>
				<s:a theme="simple" href="%{#enlace}">
		    		<s:property value="etiqueta"/>
				</s:a>
			</div>
		</s:if>
		<s:else>
			<div class="miga">
				<s:if test="migas.size > 1">
					<s:a theme="simple" href="javascript:void(0)">
		    			<s:property value="etiqueta"/>
					</s:a>
					<a class="miga-menu-mostrar" href="#"></a>
					<ul class="miga-menu-opciones">
						<li class="miga-menu-opcion">
							<a href="#" class="miga-fijar"><s:property value="getText('global.fijar')"/></a>
						</li>
					</ul>
				</s:if>
				<s:else>
					<s:a cssClass="soloUnaMiga" theme="simple" href="javascript:void(0)">
		    			<s:property value="etiqueta"/>
					</s:a>
				</s:else>
			</div>
		</s:else>
	</s:iterator>
		<s:iterator value="migasFijadas" status="migaFijadaStatus" var="migaFija">
		<div class='miga miga-fija'>
			<s:url var="enlace" namespace="/migas" action="clickMigaFija" >
				<s:param name="idMigaSeleccionada" value="%{#migaFijadaStatus.index}"/>
			</s:url>
			<s:a cssClass="miga-fija-accion" theme="simple" href="%{#enlace}">
	    		<s:set var="etiquetaMigaFija" value="#migaFija.get(#migaFija.size() -1).etiqueta"/>
				<s:property value="%{#etiquetaMigaFija}"/>
			</s:a>
			<a class="miga-quitar"><s:property value="%{#migaFijadaStatus.index}"/></a>
		</div>
	</s:iterator>
</div>

<div class="menu-desplegable">
    <a href="javascript:void(0)" class="menu-enlace">Menú</a>
    <a href="javascript:void(0)" class="menu-cerrar">Cerrar</a>
</div>
<script>
    document.documentElement.className = document.documentElement.className.replace('no-js', 'js');
</script>

