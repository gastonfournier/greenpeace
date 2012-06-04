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
	<div class="span3">
		Selected server:<br/>
		${server}
	</div>
	<div class="span3 well">
		<g:each var="env" in="${parents}">
			${env}<br/>
		</g:each>
	</div>
</div>
</body>
</html>