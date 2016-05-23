var dateParams = { 
dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'], 
monthNames: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'], 
prevText: '上一月',nextText: '下一月', clearText: '清空', currentText: '今天', 
buttonText: '确定',dateFormat: 'yy-mm-dd',changeYear:true,
showMonthAfterYear: true,yearRange: "2010:2030"};

var kindEditorParams = {
items : [
    'source', '|', 'undo', 'redo', '|', 'preview', 'cut', 'copy', 'paste',
    'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
    'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
    'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
    'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
    'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 
    'table', 'hr', '|', 'anchor', 'link', 'unlink'
],
autoHeightMode : true,
afterCreate : function() {
	this.loadPlugin('autoheight');
},
height:'400px'
};

$(function () {
	/*排序*/
	$('.dorderBox').each(function(){
		$(this).keydown(function(event){
			if(event.keyCode == '13'){
				window.location.href = $(this).attr('url') + $(this).val();
			}
		})
	});
	
	
});