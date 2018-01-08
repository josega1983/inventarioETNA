<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>



<s:form theme="simple" id="filtroPopupForm" class="form-view">
	<div class="form-row">
		<label class="col-min">
			<s:property value="getText('global.id')"/>
			<input type="number" min="1" max="999999999" name="filtro.id" value="">
		</label>
		<label class="col-max">
			<s:property value="getText('global.name')"/>
			<s:textfield maxlength="30" name="filtro.nombre" key="global.name" theme="simple"/>
		</label>
	</div>
</s:form>


