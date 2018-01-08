<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>



<input type="hidden" ng-init="$root.editID = ${elemento.id}" value="${elemento.id}"/>


<ng-controller ng-controller="MainCtrl as main">
    <ng-controller ng-controller="TypeConfigurationCtrl as typeConf">
    <div class="inventory-form">
        <div class="form-view">
            <h2 class="titulo-seccion">FORMULARIO CONFIGURACIÓN</h2>
            <div class="form-row">
                <label class="col-max">
                    <s:include value="/jsp/common/botonSelectPopup.jsp">
                        <s:param name="obligatorio">false</s:param>
                        <s:param name="urlAccionCargar"><s:url namespace='/tipoInstalacion' action='loadNoCTCPopup'/></s:param>
                        <s:param name="etiquetaBotonPopup"><s:text name="tipoInstalacion"/></s:param>
                        <s:param name="identificadorBotonPopup">idTipoInstalacionBotonPopup</s:param>
                        <s:param name="textoElementoSeleccionado"><s:if test="elemento.tipoInstalacion != null && elemento.tipoInstalacion.id != null"><s:property value="elemento.tipoInstalacion.familiaInstalacion.nombre"/> | <s:property value="elemento.tipoInstalacion.marca"/> | <s:property value="elemento.tipoInstalacion.modelo"/></s:if><s:else><s:text name="global.noElementselectedPopup"/></s:else></s:param>
                        <s:param name="idPopup">idTipoInstalacionPopup</s:param>
                        <s:param name="excluirPopup">NO</s:param>
                    </s:include>
                    
                    <div id="installationTypeID">
                    	<s:include value="/jsp/tipoInstalacion/popup/detalleTipoInstalacionSeleccionadaPopup.jsp"/>
                    </div>
                </label>
            </div>
            <div class="form-row">
                <label class="col-max">
                    <span class="etiqueta">Observaciones</span>
                    <span class="dato"><textarea type="text" maxlength="500" value="" ng-model="typeConf.configurationObs"></textarea></span>
                </label>
            </div>
        </div>

        <h2 class="titulo-seccion">
            ARBOL CONFIGURACIÓN
        </h2>
    </div>


    <div class="configuration-family hidden" ng-class="{'hidden' : typeConf.showCarouselDisabled}">
        <div class="header icon icon-search" ng-click="typeConf.showCarouselSearchFunction()" ng-class="{'icon-search' : !typeConf.showCarouselSearch, 'icon-remove' : typeConf.showCarouselSearch}">
            <p>Seleccion Configuración de familias</p>
        </div>

        <%-- SEARCH INFO --%>
        <div class="search-info" ng-repeat="(key, filter) in typeConf.searchCarouselValues" ng-if="!typeConf.showCarouselSearch && (filter != null && filter != '')">
            <div class="icon icon-filter"><div class="color-blue">{{key.split('.').pop()}}:&nbsp;</div>{{filter}}</div>
            <div class="clear-search icon icon-remove" ng-click="typeConf.clearCarouselFilter(key)"></div>
        </div>

        <div class="content">

            <%-- CFC SEARCH FIELDS --%>
            <div class="search-fields form-view hidden" ng-class="{'hidden' : !typeConf.showCarouselSearch}">
                <div class="form-row form-nowrap">
                    <div class="form-column col-50">
                        <div class="form-row">
                            <div class="col-max">
                                <span class="etiqueta">Marca</span>
                                <span class="dato">
                                    <input type="text" value="" maxlength="30" ng-model="typeConf.searchCarouselValues['tipoInstalacion.marca']">
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="form-column col-50">
                        <div class="form-row">
                            <div class="col-max">
                                <span class="etiqueta">Modelo</span>
                                <span class="dato">
                                    <input type="text" value="" maxlength="30" ng-model="typeConf.searchCarouselValues['tipoInstalacion.modelo']">
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-row search-buttons">
                    <input type="submit" value="Buscar" ng-click="typeConf.carouselSearch()"  class="formulario-boton">
                    <input type="submit" value="Limpiar" ng-click="typeConf.clearCarouselSearch()" class="formulario-boton formulario-boton2">
                </div>
            </div>

            <%-- CREATE NEW --%>
            <div class="search-fields search-buttons" ng-class="{'hidden' : typeConf.showCarouselSearch}">
                <div class="form-row search-buttons create-new">
                    <input type="submit" value="Nuevo"  class="formulario-boton formulario-boton2" ng-click="typeConf.defineCTCfromZero()">
                </div>
            </div>

            <%-- DISABLED CAROUSEL --%>
            <div class="disabled-carousel" ng-show="typeConf.showCarouselDisabled"></div>

            <%-- OWL CAROUSEL LIST --%>
            <data-owl-carousel class="owl-carousel owl-theme" ng-class="{'hidden' : typeConf.showCarouselSearch}">
                <div 
                    owl-carousel-item="" 
                    class="item carousel-item" 
                    ng-repeat="conf in typeConf.configurationConcatList track by $index" 
                    ng-click="typeConf.openConfigurationFamilyModal($index, conf.familiaInstalacion ? $root.FAMILY_CONFIGURATION : $root.TYPE_CONFIGURATION)"
                    ng-init="typeConf.initializeCarousel()">
                    <div class="title tooltip" ng-if="conf.familiaInstalacion">
                        {{conf.familiaInstalacion.nombre}}
                        <div class="tooltip-content hidden">
                            <div class="arrow-up"></div>
                            <div class="tooltip-text">
                                Familia Instalacion:&nbsp;<strong>{{conf.familiaInstalacion.nombre}}</strong>
                            </div>
                        </div>
                    </div>
                    <div class="title tooltip" ng-if="conf.tipoInstalacion">
                        {{conf.tipoInstalacion.familiaInstalacion.nombre}} | {{conf.tipoInstalacion.marca}} | {{conf.tipoInstalacion.modelo}}
                        <div class="tooltip-content hidden">
                            <div class="arrow-up"></div>
                            <div class="tooltip-text">
                                Tipo Instalacion:&nbsp;<strong>{{conf.tipoInstalacion.familiaInstalacion.nombre}}</strong><br>
                                Marca:&nbsp;<strong>{{conf.tipoInstalacion.marca}}</strong><br>
                                Modelo:&nbsp;<strong>{{conf.tipoInstalacion.modelo}}</strong>
                            </div>
                        </div>
                    </div>
                    <img ng-if="conf.imagenArbol != null && conf.familiaInstalacion" class="invert" ng-src="{{typeConf.imageToBase64(conf.imagenArbol)}}"/>
                    <img ng-if="conf.imagenArbol != null && conf.tipoInstalacion" ng-src="{{typeConf.imageToBase64(conf.imagenArbol)}}"/>
                    <div ng-if="conf.imagenArbol == null" class="broken-image icon icon-image"><div class="line"></div></div>
                </div>
                <div class="item carousel-item-loader" ng-if="typeConf.showCarouselLoader">
                    <div class="loader2"></div>
                </div>

                <%-- NO RESULTS VIEW --%>
                <div class="item carousel-item-no-result"  ng-if="typeConf.configurationConcatList.length <= 0">
	                <div class="no-results icon icon-notification">
	                    No existen elementos.
	                </div>
	            </div>
            </data-owl-carousel>

        </div>
    </div>

    <%-- MODAL USED TO DISPLAY THE CFC TREE --%>
    <div class="modal-configuration hidden" ng-class="{'hidden' : !typeConf.showConfigurationModal}">
        <div class="modal-content">
            <div class="modal-header">
            	<div class="modal-title">Configuración Familia: {{typeConf.modalTitle}}</div>
            	<div class="icon icon-remove" ng-click="typeConf.closeConfigurationFamilyModal()"></div>
            </div>
            <div class="modal-graph">
                <div id="modal-graph">
                </div>
                <div ng-if="typeConf.showCanvasModalLoader" class="loader-background">
                    <div class="loader"></div>
                </div>
            </div>
            <div class="modal-footer">
                <div class="form-row search-buttons">
                    <input type="submit" value="Aplicar"  class="formulario-boton"  ng-click="typeConf.defineCTCFromOther()">
                    <input type="submit" value="Cancelar" ng-click="typeConf.closeConfigurationFamilyModal()" class="formulario-boton formulario-boton2">
                </div>
            </div>
        </div>
    </div>



    <div class="inventory">
        <div class="graph-container">
            <div id="graph" 
                class="graph-canvas ui-droppable" 
                ng-class="{'cursor-pointer': main.hoverNode, 'canvas-fullscreen': main.isFullscreen}">
            </div>
            <div ng-if="main.showCanvasLoader" class="loader-background">
                <div class="loader"></div>
            </div>
            <canvas-options-directive 
                fullscreen-flag="main.isFullscreen" 
                zoom-in-fn="main.zoomInCanvas()" 
                zoom-out-fn="main.zoomOutCanvas()" 
                fullscreen-fn="main.canvasFullscreen()" 
                center-fn="main.centerTree()">
            </canvas-options-directive>
        </div>


        <div class="elements-container hidden" ng-class="{'hidden' : (main.numNodes <= 0)}">

            <%-- ELEMENTS CONTAINER --%>
            <div class="elements">
                <div class="header">
                    <p>Familia de Elementos</p>
                </div>
                <div class="content">
                    <div class="disabled-element-list" ng-if="main.numNodes <= 0"></div>

                    <%-- ELEMENTS SEARCH FIELDS --%>
                    <div class="form-row">
                        <label class="col-min">
                            <span class="dato search-input">
                                <input ng-keyup="typeConf.getElements(typeConf.searchValues)" class="white" type="text" value="" maxlength="30" ng-model="typeConf.searchValues.nombre">
                                <span class="icon icon-search"></span>
                            </span>
                        </label>
                    </div>

                    <%-- SEARCH INFO --%>
                    <div class="search-info" ng-repeat="(key, filter) in typeConf.searchValues" ng-if="(filter != null && filter != '') && !typeConf.showSearch">
                        <div class="icon icon-filter"><div class="color-blue">{{key.split('.').pop()}}:&nbsp;</div>{{filter}}</div>
                        <div class="clear-search icon icon-remove" ng-click="typeConf.clearFilter(key)"></div>
                    </div>

                    <%-- ELEMENTS LIST --%>
                    <ul class="draggable-elements" ng-if="!typeConf.showSearch" scroll-vertical-handler="typeConf.getMoreResults()">
                        <li 
                            ng-if="typeConf.elementsList.list.length > 0" 
                            class="ui-draggable ui-draggable-handle" 
                            ng-repeat="element in typeConf.elementsList.list track by $index" 
                            data-index="{{$index}}" 
                            data-type="{{$root.ELEMENT_TYPE}}" 
                            draggable-handler>
                                {{element.nombre}}
                        </li>

                        <%-- NO RESULTS VIEW --%>
                        <div class="no-results icon icon-notification" ng-if="typeConf.elementsList.list.length <= 0  && !typeConf.showListLoader">
                            No existen elementos.
                        </div>

                        <%-- LOADER --%>
                        <div class="loader2" ng-if="typeConf.showListLoader"></div>
                    </ul>
                </div>
            </div>
            <div class="botoneraAccionesPie" ng-class="{'hidden' : typeConf.showSearch}">
                <form id="formularioVolverBack" action='/ETNAJ/MI/migas/volverBack' method='post'>
                    <input type="submit" id="botonVolver" value="Volver" class="formulario-boton" formnovalidate="">
                </form>
                <input type="submit" value="Guardar" id="formularioCrearRegion_global_save" ng-click="typeConf.saveConfiguration()"  class="formulario-boton">
            </div>
        </div>
    </div>

    <node-options-directive 
        modal-info="main.modal" 
        close-fn="main.closeMenu()" 
        edit-fn="main.editNode()" 
        clone-fn="main.duplicateBranch()" 
        delete-fn="main.deleteNode()"
        copy-fn="main.copyBranch()"
        paste-fn="main.pasteBranch()">
    </node-options-directive>
    </ng-controller>
</ng-controller>