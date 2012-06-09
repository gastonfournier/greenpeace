<g:each var="app" in="${applications}">
	<g:link action="showApplications" params="${[server:server, env: app]}">${app}</g:link><br/>
</g:each>
