$(document).ready(function() {
	periodsLoad();
});

function periodsLoad() {
	
	//设置宽度
	$(".periods0").each(function(){
		var width_temp = "";
		var periods_width = $(this).children(".periods").width();
		var periods_more_width = $(this).children(".periods_more").width();
		if (periods_width > periods_more_width)
			width_temp = periods_width;
		else
			width_temp = periods_more_width;
		$(this).children(".periods").width(width_temp+12);
		$(this).children(".periods_more").width(width_temp+12);
	});
	
	//鼠标按下动作
	$(".p_m_items").bind("mousedown",function(){
		var tempNum = $(this).index();
		var tempVal = $(this).attr("rel");
		var tempJs = $(this).attr("alt");
		var tempVal1 = $(this).html();
		$(this).parent(".periods_more").prevAll(".periods").children("span").html(tempVal1);
		$(this).parent(".periods_more").prevAll(".valClass").val(tempVal1);
		$(this).parent(".periods_more").prevAll(".keyClass").val(tempVal);
		$(this).parent(".periods_more").prevAll(".keyClass").blur();
		$(".periods_more").css("visibility", "hidden");
		$(".periods").removeClass("periods_cur");
		$(".p_m_items").removeClass("cur");
		if(tempVal!=""){
			$(this).parent(".periods_more").children(".nullVal").show();
		}else{
			$(this).parent(".periods_more").children(".nullVal").hide();
		}
		if(tempJs=="function"){
			JsFunction(tempVal);
		}
	});
	
	//下拉框鼠标移上去
	$(".periods0").hover(function(){
		$(this).children(".periods_more").css("visibility", "visible");
		$(this).children(".periods").addClass("periods_cur");
		var value = $(this).children(".keyClass").val();
		if(value!=""){
			$(this).children(".periods_more").children(".p_m_items").each(function(){
				var rel = $(this).attr("rel");
				if(rel==value){
					$(this).addClass("cur");
				}else{
					$(this).removeClass("cur");
				}
			});
		}
	}, function() {
		$(this).children(".periods_more").css("visibility", "hidden");
		$(this).children(".periods").removeClass("periods_cur");
	});
	
	//下拉列表选项鼠标移上去，移出来
	$(".p_m_items").bind("mouseover",function(){
		$(this).addClass("cur");
	});
	
	$(".p_m_items").bind("mouseout",function(){
		$(this).removeClass("cur");
	});
	
}
		 
function selecting(Id,vals){
	var valObj = $("#"+Id).children(".periods_more").find(".p_m_items[rel='"+vals+"']");
	var tempNum = $(valObj).index();
	var tempVal = $(valObj).attr("rel");
	var tempVal1 = $(valObj).html();
	$(valObj).parent(".periods_more").prevAll(".periods").children("span").html(tempVal1);
	$(valObj).parent(".periods_more").prevAll(".valClass").val(tempVal1);
	$(valObj).parent(".periods_more").prevAll(".keyClass").val(tempVal);
	$(valObj).parent(".periods_more").prevAll(".keyClass").blur();
	$(".periods_more").css("visibility", "hidden");
	$(".periods").removeClass("periods_cur");
	$(".p_m_items").removeClass("cur");
	if(tempVal!=""){
		$(valObj).parent(".periods_more").children(".nullVal").show();
	}else{
		$(valObj).parent(".periods_more").children(".nullVal").hide();
	}
}
