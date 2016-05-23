$(document).ready(function() {
	periodsLoad();
	if($("#mbg").offset()) {
		$("html,body").animate({scrollTop: $("#mbg").offset().top}, 1);
	}
	
	/**
	 * ***************************************** input输入框输入限制初始化
	 * *********************************************
	 */
	$("input").each(function() {
		$(this).bind('focusout afterpaste', function() {
			this.value = $.trim(this.value);
		});
	});
	$(".digital").each(function() {
		$(this).bind('focusout afterpaste', function() {
			this.value = this.value.replace(/\D/g, '');
		});
	});
	$(".number").each(function() {
		$(this).bind('focusout afterpaste', function() {
			this.value = this.value.replace(/([^0-9-]*)(-?)([0-9]*)(\.?)([0-9]{0,2}).*/g,'$2$3$4$5');
		});
	});
});

function periodsLoad() {
	$(".periods0").each(function(){
		var width_temp = "";
		var periods_width = $(this).children(".periods").width();
		var periods_more_width = $(this).children(".periods_more").width();
		if (periods_width > periods_more_width)
			width_temp = periods_width;
		else
			width_temp = periods_more_width;
		$(this).children(".periods").width(width_temp);
		$(this).children(".periods_more").width(width_temp);
	});
	
	$(".p_m_items").mousedown(function(){
		tempNum = $(this).index();
		tempVal = $(this).attr("rel");
		tempVal1 = $(this).html();
		$(this).parent(".periods_more").prevAll(".periods").children("span").html(tempVal1);
		$(this).parent(".periods_more").prevAll(".valClass").val(tempVal1);
		$(this).parent(".periods_more").prevAll(".keyClass").val(tempVal);
		$(this).parent(".periods_more").prevAll(".keyClass").blur();
		$(".periods_more").css("visibility", "hidden");
		$(".p_m_items").removeClass("selcur");
		if(tempVal!=""){
			$(this).parent(".periods_more").children(".nullVal").show();
		}else{
			$(this).parent(".periods_more").children(".nullVal").hide();
		}
	});
	
	$(".p_m_items1").mousedown(function(){
	});
	
	$(".p_m_items2").mousedown(function(){
		tempNum = $(this).index();
		tempVal = $(this).attr("rel");
		tempVal1 = $(this).html();
		$(this).parent(".periods_more2").prevAll(".periods_2").children("span").html(tempVal1);
		$(this).parent(".periods_more2").prevAll(".valClass").val(tempVal1);
		$(this).parent(".periods_more2").prevAll(".keyClass").val(tempVal);
		$(this).parent(".periods_more2").prevAll(".keyClass").blur();
		$(".periods_more2").css("visibility", "hidden");
		$(".p_m_items2").removeClass("selcur");
		if(tempVal!=""){
			$(this).parent(".periods_more2").children(".nullVal").show();
		}else{
			$(this).parent(".periods_more2").children(".nullVal").hide();
		}
	});
	
	$(".periods0").hover(function(){
		$(this).children(".periods_more").css("visibility", "visible");
		var value = $(this).children(".keyClass").val();
		if(value!=""){
			$(this).children(".periods_more").children(".p_m_items").each(function(){
				var rel = $(this).attr("rel");
				if(rel==value){
					$(this).addClass("selcur");
				}else{
					$(this).removeClass("selcur");
				}
			});
		}
	}, function() {
		$(this).children(".periods_more").css("visibility", "hidden");
	});
	
	$(".periods1").hover(function(){
		$(this).children(".periods_more1").css("visibility", "visible");
		var value = $(this).children(".keyClass").val();
		if(value!=""){
			$(this).children(".periods_more1").children(".p_m_items1").each(function(){
				var rel = $(this).attr("rel");
				if(rel==value){
					$(this).addClass("selcur");
				}else{
					$(this).removeClass("selcur");
				}
			});
		}
	}, function() {
		$(this).children(".periods_more1").css("visibility", "hidden");
	});
	
	$(".periods2").hover(function(){
		$(this).children(".periods_more2").css("visibility", "visible");
		var value = $(this).children(".keyClass").val();
		if(value!=""){
			$(this).children(".periods_more2").children(".p_m_items2").each(function(){
				var rel = $(this).attr("rel");
				if(rel==value){
					$(this).addClass("selcur");
				}else{
					$(this).removeClass("selcur");
				}
			});
		}
	}, function() {
		$(this).children(".periods_more2").css("visibility", "hidden");
	});
	
	$(".p_m_items").mouseover(function(){
		$(this).addClass("selcur");
	});
	
	$(".p_m_items").mouseout(function(){
		$(this).removeClass("selcur");
	});
	
	$(".p_m_items1").mouseover(function(){
		$(this).addClass("selcur");
	});
	
	$(".p_m_items1").mouseout(function(){
		$(this).removeClass("selcur");
	});
	
	$(".p_m_items2").mouseover(function(){
		$(this).addClass("selcur");
	});
	
	$(".p_m_items2").mouseout(function(){
		$(this).removeClass("selcur");
	});
	
}

function backLevel(level){
	var levelObj = $(".levelDiv").children(".periods2").children(".periods_more2").children("div[rel='"+level+"']");
	var htmlLevel = $(levelObj).html();
	var showObj = $(".levelDiv").children(".periods2").children(".periods_2").children("span");
	$(showObj).html(htmlLevel);
}