$(document).ready(function(){
	$(".step1 input[type=button]").click(function(){
		// load server
		var server = getServer();
		$("#environments").load("/greenpeace/import/environments?server="+server);
		changeStep("step1", "step2");
	});
	
	$(".environmentLink").live('click', function(){
		var server = getServer();
		var env = $(this).text();
		$("#applications").load("/greenpeace/import/applications?server="+server+"&envs="+env);
		changeStep("step2", "step3");
	});
	
	$("#querySelectedEnvironments").live('click', function(){
		var server = getServer();
		var envs = getSelectedEnvironments();
		var envsParam = envs.join(",");
		$("#applications").load("/greenpeace/import/applications?server="+server+"&envs="+envsParam);
		changeStep("step2", "step3");
	});
	
	$("#importSelectedEnvironments").live('click', function(){
		var server = getServer();
		var envs = getSelectedEnvironments();
		var envsParam = envs.join(",");
		var form = $("#importForm");
		form.find("input[name=environments]").val(envsParam);
		form.submit();
	});
	
	$("#importSelectedApplications").live('click', function(){
		var server = getServer();
		var envs = getSelectedEnvironments();
		var apps = getSelectedApplications();
		var envsParam = envs.join(",");
		var appsParam = apps.join(",");
		var form = $("#importForm");
		form.find("input[name=server]").val(getServer());
		form.find("input[name=environments]").val(envsParam);
		form.find("input[name=applications]").val(appsParam);
		form.submit();
	});
});

function changeStep(prev, next){
	var $prev = $("."+prev);
	var $next = $("."+next);
	$(".well").removeClass("well");
	$prev.find("input[type=text]").focus(function(){
		// go backward
		changeStep(next, prev);
	});
	$next.addClass("well");
}

function getServer(){
	return $("#server").val();
}

function getSelectedEnvironments(){
	// filter checked and keep the values
	return $.map($("input[name=envToImport]"), function(e){
		if ($(e).attr("checked")){
			return $(e).val();
		}
	});
}

function getSelectedApplications(){
	// filter checked and keep the values
	return $.map($("input[name=appToImport]"), function(e){
		if ($(e).attr("checked")){
			return $(e).val();
		}
	});
}