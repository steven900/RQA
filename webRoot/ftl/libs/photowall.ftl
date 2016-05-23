[#ftl]

[#macro photowall addurl='']
	<!doctype html>
		<html lang="zh">
		<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title></title>
		<!--主要样式-->
		<link rel="stylesheet" type="text/css" href="/framework/utils/photowall/css/styles.css">
		[@script src=['jquery'] /]
		<script src="/js/views/listinput.js"></script>
		</head>
		<body>
		
		<div style="margin-top:10px;">
			<input type="button" value="添加图片" onclick="location='${addurl}'"/>
		</div>
		<div class="wrapper">
			<ul class="gallery">
				[#nested]
			</ul>
		</div>
		
		</body>
		</html>
[/#macro]
[#global photowall = photowall/]