function loadConfig_byEnvironment(parent, child) {
	loadConfig(child, parent)
}

function loadConfig_byProject(parent, child) {
	loadConfig(parent, child)
}

function loadConfig(proj, env) {
	$('#config').fadeOut('slow', function(){
        $(this).load('/greenpeace/browse/config?project='+proj+'&env=' +env, function(){
            $(this).fadeIn('slow');
        });
    });
}

function makeEditable(url, tag) {
	$(document).ready(function() {
		$('div#' + tag + ' .propVal').editable(url, {
			width : '98%',
			height : '18px',
			id : 'key',
			name : 'value'
		});
	});
}