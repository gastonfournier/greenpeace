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
		<div class="span3 well step1">
			<label for="server">Server (http://):</label>
			<input name="server" id="server" type="text" />
			<input type="button" value="Query server configuration"/>
		</div>
		<div class="span3 step2" id="environments">
		</div>
		<div class="span5 step3" id="applications">
		</div>
	</div>
	<div class="hidden">
		<g:form method="POST" name="importForm" action="importConfiguration">
			<input type="hidden" name="server" value="" />
			<input type="hidden" name="environments" value="" />
			<input type="hidden" name="applications" value="" />
		</g:form>
	</div>
</body>
</html>