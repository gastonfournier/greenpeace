$(document).ready(function(){
	$(".step1 input[type=button]").click(function(){
		// load server
		var server = $("#server").val();
		$("#environments").load("/greenpeace/manage/showServerConfiguration?server="+server);
		changeStep("step1", "step2");
	});
	
	$(".environmentLink").live('click', function(){
		var server = $("#server").val();
		var env = $(this).text();
		$("#applications").load("/greenpeace/manage/showApplications?server="+server+"&envs="+env);
		changeStep("step2", "step3");
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