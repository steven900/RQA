[#ftl]
[#macro head_list title content_type='text/html; charset=UTF-8']
	<head>
		<meta http-equiv="Content-Type" content="${content_type}" />
		<title>${title}</title>
		[@style src=['manage','jqueryui']/]
		<link href="/css/manage/flat-ui.css" rel="stylesheet">
		[@script src=['jquery','jqueryui','manage']/]
		<script src="/js/views/listinput.js"></script>
		<script src="/framework/utils/layer-v2.0/layer/layer.js"></script>
 		<script>
	 		function openurl(url,title){
		 		layer.open({
		 		    type: 2,
		 		    title: false,
		 		    shadeClose: true,
		 		    shade: 0.4,
		 		    area: ['680px', '90%'],
		 		    content: url
		 		    }); 
	 		}	
	 		
	 		var dateParams = { 
			dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'], 
			monthNames: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'], 
			prevText: '上一月',nextText: '下一月', clearText: '清空', currentText: '今天', 
			buttonText: '确定',dateFormat: 'yy-mm-dd',changeYear:true,
			showMonthAfterYear: true,yearRange: "2015:2030"};
 		</script>
		[#nested]
	</head>
[/#macro]
[#global head_list = head_list/]