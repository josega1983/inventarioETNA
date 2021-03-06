<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>



<s:form theme="simple" id="filtroPopupForm" class="form-view">
	<div class="form-row">
		<label class="col-min">
			<s:property value="getText('global.id')"/>
			<input type="number" min="1" max="999999999" name="filtro.id" value="">
		</label>
		<label class="col-min">
			<s:property value="getText('region')"/>
			<s:textfield maxlength="30" name="filtro.region.nombre" key="region" theme="simple"/>
		</label>
		<label class="col-min">
			<s:property value="getText('sector')"/>
			<s:textfield maxlength="30" name="filtro.sector.nombre" key="sector" theme="simple"/>
		</label>
		<label class="col-min">
			<s:property value="getText('centro')"/>
			<s:textfield maxlength="30" name="filtro.centro.nombre" key="centro" theme="simple"/>
		</label>
		<label class="col-max">
			<s:property value="getText('unidadMantenimiento')"/>
			<s:textfield maxlength="30" name="filtro.unidadMantenimiento.nombre" key="unidadMantenimiento" theme="simple"/>
		</label>								
	</div>
</s:form>


