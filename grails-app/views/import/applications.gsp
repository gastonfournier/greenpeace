<g:each var="app" in="${applications}">
	<input type="checkbox" checked="checked" name="appToImport" value="${app}" />
	${app}
	<g:if test="${appsPerEnv.size() > 1}">
		<g:each var="env" in="${appsPerEnv}">
			<g:if test="${env.value.contains(app)}"><span class="label label-info">${env.key}</span></g:if>
		</g:each>
	</g:if>
	<br/>
</g:each>
<input type="button" value="Import selected" id="importSelectedApplications" />
