<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>



<s:form theme="simple" id="filtroPopupForm" class="form-view">
	<div class="form-row">
		<label class="col-min">
			<s:property value="getText('global.id')"/>
			<input type="number" min="1" max="999999999" name="filtro.id" value="">
		</label>
		<label class="col-min">
			<s:property value="getText('global.marca')"/>
			<s:textfield maxlength="30" name="filtro.marca" key="global.marca" theme="simple"/>
		</label>
		<label class="col-min">
			<s:property value="getText('global.modelo')"/>
			<s:textfield maxlength="30" name="filtro.modelo" key="global.modelo" theme="simple"/>
		</label>
		<label class="col-max">
			<s:property value="getText('familiaInstalacion.nombre')"/>
			<s:textfield maxlength="30" name="filtro.familiaInstalacion.nombre" key="familiaInstalacion.nombre" theme="simple"/>
		</label>
	</div>
</s:form>


