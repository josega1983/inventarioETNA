<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html class="no-js">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><tiles:insertAttribute name="title" ignore="true"/></title>
        <s:include value="/jsp/common/includes.jsp"/>
<link rel="icon" href="${pageContext.request.contextPath}/img/common-enaire/favicon.png"  type="image/png"/>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/common-enaire/favicon.png"  type="image/png"/>
    </head>
    <body id="buscar">
        <div class="contenedor">
            <header class="cabeceraPrincipal">
                <tiles:insertAttribute name="header"/>
            </header>
            <div class="main-container">
                <div class="menu" id="menu">
                    <tiles:insertAttribute name="menu"/>
                </div>
                <div class="contenido">
                    <div class="visualizacion">
    				<div class="visualizacion-formulario">
    					<tiles:insertAttribute name="body"/>
    						<form id="formularioVolverBack" action='/ETNAJ/MI/migas/volverBack' method='post'>
    						</form>
    							
    						<script type="text/javascript">
    							$(".botoneraAccionesPie").prepend("<input type='button' id='botonVolver' value='Volver' class='formulario-boton' formnovalidate=''>");

    							$( "#botonVolver" ).click(function() {
    								  $("#formularioVolverBack").submit();
    							});
    						</script>
    					</div>
    				</div>
                </div>
            </div>

            <div class="menu-desplegable-caja">
            	<tiles:insertAttribute name="menuRight"/>

            </div>

             <div class="buscador-caja">
             	<tiles:insertAttribute name="searchForm"/>
             </div>

            <div class="footer">
                <tiles:insertAttribute name="footer"/>
            </div>
        </div>

		<script src="${pageContext.request.contextPath}/style/bower_components/moment/min/moment-with-locales.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/style/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>

        <script src="${pageContext.request.contextPath}/js/listados.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/app.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/menu.js"></script>

	    <script src="${pageContext.request.contextPath}/js/paginacion.js" type="text/javascript"></script>
	    <script src="${pageContext.request.contextPath}/js/flexibility.js" type="text/javascript"></script>
	    <script src="${pageContext.request.contextPath}/js/popups.js" type="text/javascript"></script>
	    <script src="${pageContext.request.contextPath}/js/common/util.js" type="text/javascript"></script>
        
    </body>
</html>

