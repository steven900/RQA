[#ftl]
[#macro head_nt title content_type='text/html; charset=UTF-8']
	<head>
		<meta http-equiv="Content-Type" content="${content_type}" />
		<title>${title}</title>
		[@style src=['manage','jqueryui','flat-ui']/]
		[@script src=['jquery','jqueryui','manage','upload','kindeditor','kindeditorLang','ajaxUpload']/]
		<script src="/js/upload/imgBigUpload.js"></script>
		<script src="/framework/utils/layer-v2.0/layer/layer.js"></script>
		<script>
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
[#global head_nt = head_nt/]