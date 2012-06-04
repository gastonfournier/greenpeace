$(document).ready(function(){
	$(".submitServer").click(function(){
		changeStep("step1", "step2");
	});
});

function changeStep(prev, next){
	var $prev = $("."+prev);
	var $next = $("."+next);
	$prev.removeClass("well");
	$prev.find("input[type=text]").focus(function(){
		// go backward
		changeStep(next, prev);
	});
	$next.addClass("well");
}