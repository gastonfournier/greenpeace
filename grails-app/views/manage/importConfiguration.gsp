<%@ page contentType="text/html;charset=UTF-8"%>
<g:each var="env" in="${environments}">
	<input type="checkbox" checked="checked" name="envToImport" value="${env}" />
	<a href="#" class="environmentLink">${env}</a><br/>
</g:each>
<br/>
<input type="button" value="Import selected" id="importSelectedEnvironments" /> 
<input type="button" value="Query applications" id="querySelectedEnvironments" />