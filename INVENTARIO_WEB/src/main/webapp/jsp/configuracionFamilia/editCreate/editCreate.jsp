<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>



<input type="hidden" ng-init="$root.editID = ${elemento.id}" value="${elemento.id}"/>
<div class="cong-model=figuration-container" ng-include src="'../jsp/configuracionFamilia/editCreate/main.html'">
</div>


</div>