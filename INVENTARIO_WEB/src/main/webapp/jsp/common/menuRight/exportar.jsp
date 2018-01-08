<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:if test="%{actionName.contains('list') || actionName.contains('search')}">
	<div class="menu-desplegable-opcion">
		<s:submit id="exportar_list_PDF" cssClass="opcion-exportar" key="Exportar PDF" theme="simple"/>
	</div>
	<div class="menu-desplegable-opcion">
		<s:submit id="exportar_list_Excel" cssClass="opcion-exportar" key="Exportar Excel" theme="simple"/>
	</div>
</s:if>
<s:elseif  test="%{actionName.contains('showMore')}">
	<div class="menu-desplegable-opcion">
		<s:submit id="exportar_showMore_PDF" cssClass="opcion-exportar" key="Exportar PDF" theme="simple"/>
	</div>
	<%-- <div class="menu-desplegable-opcion">
		<s:submit id="exportar_showMore_Excel" cssClass="opcion-exportar" key="Exportar Excel" theme="simple"/>
	</div> --%>
</s:elseif>


<script>
function comprobarListadoPopUp(location){
	$.ajax({
			url:"preExportarList" ,
			type: "POST",
			async: false,
			success: function(result){
				validarSesionExpirada(result);

				var respuesta = JSON.parse(result),
				mensaje = "";
				if(respuesta.resultado === "KO"){
					mensaje = respuesta.mensaje;
					titulo = respuesta.titulo;
							swal({
							title: titulo,
							text:  mensaje,
							type: "info",
							showCancelButton: true,
							closeOnConfirm: true,
							animation: "slide-from-top"
					},
					function(inputValue){
						if (inputValue === false) return false;
						else window.location=location;
						})
				}
				else if (respuesta.resultado === "KO_APLICACION"){
				swal("Error",respuesta.mensaje,"error");
				}else{
				window.location=location;
				}


			},
			error: function (xhr, ajaxOptions, thrownError){
				swal("Error","Error, consulte al administrador de la aplicación para más información","error");
				return false;
				}
});
};
$("#exportar_list_PDF").click(function() {
var $this = $(this);
var $action=$this.data('action');
	var $actual = $('#actualPage').val();
		var location="exportarList_PDF";
	if($actual !=null){
	location+="?actualPage="+  $('#actualPage').val();
	}
	   comprobarListadoPopUp(location);
});

$("#exportar_list_Excel").click(function() {
	var $actual = $('#actualPage').val();
	var location="exportarList_XLS";
	if($actual !=null){
		location+="?actualPage="+  $('#actualPage').val();
	}
   comprobarListadoPopUp(location);


});

$("#exportar_showMore_PDF").click(function() {
	window.location="exportarShowMore_PDF?idFilaSeleccionada=" +  $('#idFilaSeleccionada').val() ;
});

$("#exportar_showMore_Excel").click(function() {
	window.location="exportarShowMore_XLS?idFilaSeleccionada=" +  $('#idFilaSeleccionada').val() ;
});
</script>