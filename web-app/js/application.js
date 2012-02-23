function loadConfig(proj, env) {
	$('#config').load('/greenpeace/browse/config?project='+proj+'&env=' +env);
}