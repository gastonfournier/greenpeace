<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta name="layout" content="main" />
<title>Browse Projects by Environment</title>
<g:javascript src="jquery/jquery.jeditable.mini.js" />
<g:javascript src="browse.js" />

</head>
<body>
	<div class="span3">
		<div class="well sidebar-nav">
			<g:render template="sideMenu"></g:render>
		</div>
		<!--/.well -->
	</div>
	<!--/span-->
	<div id="config" class="span9">
		<div class="row-fluid">
			<div class="span12" style="text-align: center;">
				<h4>Select a Project to load its configuration</h4>
			</div>
			<!--/span-->
		</div>
	</div>
	<!--/span-->
</body>
</html>