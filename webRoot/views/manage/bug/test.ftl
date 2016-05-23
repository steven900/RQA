[#ftl]
	<!DOCTYPE html>
	<html >
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>活动管理</title>
	<style>
	</style>
			<script type='text/javascript' src='/js/jquery/jquery.1.9.js'></script>
			<script type='text/javascript' src='/js/upload/ajaxUpload.js?v=2014021902'></script>
			<script src="/js/upload/imgBigUpload.js"></script>
	
	</head>
	<body>
		
		<input id="imgId" type="file" name="upload" onchange="ajaxUpload('imgId','img','img',800,800,800,800);"/>
		<input type="hidden" id="img" name="img"   value=""/>
		<br/>
			<img id="imgdisplayImage" src=""/> 
	
	</body>
	</html>

