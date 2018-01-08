<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html class="no-js">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><tiles:insertAttribute name="title" ignore="true"/></title>
		<s:include value="/jsp/common/includes.jsp"/>


		<link rel="${pageContext.request.contextPath}/style/bower_components/vis/dist/vis.css" href="styles/main.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style/angular/main.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style/angular/vermas.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style/bower_components/owl.carousel/dist/assets/owl.carousel.min.css">



		<script src="${pageContext.request.contextPath}/style/bower_components/angular/angular.js"></script>
		<script src="${pageContext.request.contextPath}/style/bower_components/angular-animate/angular-animate.js"></script>
		<script src="${pageContext.request.contextPath}/style/bower_components/angular-cookies/angular-cookies.js"></script>
		<script src="${pageContext.request.contextPath}/style/bower_components/angular-resource/angular-resource.js"></script>
		<script src="${pageContext.request.contextPath}/style/bower_components/angular-route/angular-route.js"></script>
		<script src="${pageContext.request.contextPath}/style/bower_components/angular-sanitize/angular-sanitize.js"></script>
		<script src="${pageContext.request.contextPath}/style/bower_components/angular-touch/angular-touch.js"></script>
		<script src="${pageContext.request.contextPath}/style/bower_components/vis/dist/vis.js"></script>
		<script src="${pageContext.request.contextPath}/style/bower_components/owl.carousel/dist/owl.carousel.min.js"></script>



		<script src="${pageContext.request.contextPath}/js/ctc-Script/app.js"></script>
		<script src="${pageContext.request.contextPath}/js/ctc-Script/controllers/main.js"></script>
		<script src="${pageContext.request.contextPath}/js/ctc-Script/controllers/familyConfiguration.js"></script>
		<script src="${pageContext.request.contextPath}/js/ctc-Script/controllers/typeConfiguration.js"></script>
		<script src="${pageContext.request.contextPath}/js/ctc-Script/controllers/vermas.js"></script>
		<script src="${pageContext.request.contextPath}/js/ctc-Script/directives/nodeOptions-directive.js"></script>
		<script src="${pageContext.request.contextPath}/js/ctc-Script/directives/canvasOptions-directive.js"></script>
		<script src="${pageContext.request.contextPath}/js/ctc-Script/directives/scrollHandler-directive.js"></script>
		<script src="${pageContext.request.contextPath}/js/ctc-Script/directives/owlCarousel-directive.js"></script>
		<script src="${pageContext.request.contextPath}/js/ctc-Script/directives/draggableHandler-directive.js"></script>
		<script src="${pageContext.request.contextPath}/js/ctc-Script/providers/visCustom-factory.js"></script>
		<script src="${pageContext.request.contextPath}/js/ctc-Script/providers/visTempCustom-factory.js"></script>
		<script src="${pageContext.request.contextPath}/js/ctc-Script/providers/configuration-service.js"></script>

		<link rel="icon" href="${pageContext.request.contextPath}/img/common-enaire/favicon.png"  type="image/png"/>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/common-enaire/favicon.png"  type="image/png"/>
	</head>
    <body id="buscar" ng-app="inventarioApp">
		<div class="contenedor">
			<header class="cabeceraPrincipal">
				<tiles:insertAttribute name="header"/>
			</header>
			<div class="main-container">
				<div class="menu" id="menu">
					<tiles:insertAttribute name="menu"/>
				</div>
				<div class="contenido">
					<tiles:insertAttribute name="body"/>
				</div>
			</div>
			<div class="menu-desplegable-caja">
				<tiles:insertAttribute name="menuRight"/>
			</div>

			<div class="buscador-caja">
				<tiles:insertAttribute name="searchForm"/>
			</div>
			<footer class="footer">
				<tiles:insertAttribute name="footer"/>
			</footer>
		</div>

		<script src="${pageContext.request.contextPath}/js/listados.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/app.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/menu.js"></script>
		
		<script src="${pageContext.request.contextPath}/js/paginacion.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/flexibility.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/popups.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/common/util.js" type="text/javascript"></script>

	</body>

</html>