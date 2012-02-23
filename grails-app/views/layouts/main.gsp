<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Greepeace - <g:layoutTitle /></title>
<r:require modules="bootstrap" />
<r:layoutResources />
<g:layoutHead />

<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}
</style>

<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="images/favicon.ico">
<link rel="apple-touch-icon" href="images/apple-touch-icon.png">
<link rel="apple-touch-icon" sizes="72x72"
	href="images/apple-touch-icon-72x72.png">
<link rel="apple-touch-icon" sizes="114x114"
	href="images/apple-touch-icon-114x114.png">

<link href="${resource(dir: 'css', file: 'main.css')}" type="text/css"
	rel="stylesheet" />

<g:javascript src="application.js" />

</head>

<body>

	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="i-bar"></span> <span
					class="i-bar"></span> <span class="i-bar"></span>
				</a> <a class="brand" href="#">Greenpeace</a>
				<div class="nav-collapse">
					<ul class="nav">
						<li class="dropdown ${controllerName == 'browse' ? 'active' : ''}"><a
							href="#" class="dropdown-toggle" data-toggle="dropdown">Browse
								<b class="caret"></b>
						</a>
							<ul class="dropdown-menu">
								<li ${actionName == 'byEnvironment' ? ' class="active"' : ''}><g:link
										controller="browse" action="byEnvironment">By Environment</g:link></li>
								<li ${actionName == 'byProject' ? ' class="active"' : ''}><g:link
										controller="browse" action="byProject">By Project</g:link></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Manage <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">Environments</a></li>
								<li><a href="#">Projects</a></li>
							</ul></li>
					</ul>
					<p class="navbar-text pull-right">
						Logged in as <a href="#">username</a>
					</p>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row-fluid">
			<g:layoutBody />
		</div>
		<!--/row-->

		<hr>

		<footer>
			<p>&copy; Company 2012</p>
		</footer>

	</div>
	<!--/.fluid-container-->

	<r:layoutResources />

</body>
</html>
