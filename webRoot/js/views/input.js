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
		$(this).bind('keyup afterpaste keypress', function() {
			this.value = this.value.replace(/\D/g, '');
		});
	});
	$(".number").each(function() {
		$(this).bind('keyup afterpaste keypress', function() {
			this.value = this.value.replace(/([^0-9-]*)(-?)([0-9]*)(\.?)([0-9]{0,2}).*/g,'$2$3$4$5');
		});
	});
});

function checkMobile(mobile) {
	var pattern=/1[3-8]+\d{9}/;
	return pattern.test(mobile);
}

function checkEmail(email) {
	var pattern=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
	return pattern.test(email);
}