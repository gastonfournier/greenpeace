<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta name="layout" content="main" />
<title>Import</title>
<g:javascript src="jquery/jquery.jeditable.mini.js" />
<g:javascript src="import.js" />

</head>
<body>
	<div class="row">
		<div class="span4">
			<g:form action="showServerConfiguration">
				<label for="server">Server (http://):</label>
				<input name="server" id="server" type="text" />
				<g:actionSubmit action="showServerConfiguration" value="Query server configuration"/>
			</g:form>
		</div>
	</div>
</body>
</html>