$(document).ready(function() {
	$(".mobile_detail .boxCss").click(function(){
		var classes = $(this).attr("class");
		var values = $(this).attr("rel");
		if(classes.indexOf("checkYes")==-1){
			var inputId = $(this).attr("data-Id");
			$(".mobile_detail .boxCss").each(function(){
				$(this).removeClass("checkYes");
				$(this).addClass("checkNo");
			});
			$(this).removeClass("checkNo");
			$(this).addClass("checkYes");
			$("#"+inputId).val(values);
		}
	});
	
	$(".mobile_detail .boxlight").focus(function(){
		$(this).addClass("light");
	});
	
	$(".mobile_detail .boxlight").blur(function(){
		$(this).removeClass("light");
	});
	
	$(".subSym .glyphicon-minus").click(function(){
		var vals = parseInt($("#proCount").val());
		if(vals>1){
			if(vals){
				$(this).addClass("symColor");
			}else{
				$(this).removeClass("symColor");
			}
			$("#proCount").val(vals-1);
		}
	});
	
	$(".addSym .glyphicon-plus").click(function(){
		var vals = parseInt($("#proCount").val());
		$(this).removeClass("symColor");
		$("#proCount").val(vals+1);
	});
	
	$(".mobile_detail .popClose").click(function(){
		var popObj = $(this).parent(".con").parent(".con0").parent(".mobile_detail");
		$(popObj).hide();
	});
});

function popShow(){
	$(".mobile_detail").show();
}