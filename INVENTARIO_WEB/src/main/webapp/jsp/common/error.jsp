<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <body>
        <s:if test="hasActionErrors()">
			<s:iterator value="actionErrors">
		    	<div class="alert alert-danger alert-dismissable">
					<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
					<s:property/>
				</div>
			</s:iterator>
		</s:if>
    </body>
</html>