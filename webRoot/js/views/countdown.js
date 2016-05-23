$(function() {
	setInterval("countDown()", 1000);
});


function countDown() {
	var date = new Date();
	var hours = 15 - date.getHours();
	var minutes = 59 - date.getMinutes();
	if(hours < 0 || minutes < 0) {
		$(".countdown").html("已结束");
	} else {
		$(".countdown").html(hours + "小时" + minutes + "分钟");
	}
}