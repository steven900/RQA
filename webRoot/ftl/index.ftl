[#ftl]
[#assign suffix="?v=20141126"]
[#macro nocache ]
	${_response.setHeader("Cache-Control","no-cache,no-store")}
	${_response.setHeader("Pragma","no-cache")}
	${_response.setHeader("Expires","-1")}
[/#macro]

[#macro html lang=""]
	<!DOCTYPE html>
	<html [#if lang!=""]lang=${lang}[/#if]>
		[#nested]
	</html>
[/#macro]

[#macro head title content_type='text/html; charset=UTF-8']
	<head>
		<meta http-equiv="Content-Type" content="${content_type}" />
		<meta http-equiv="charset" content="UTF-8">
		<title>${title}</title>
		[#nested]
	</head>
[/#macro]

[#macro headIE8 title content_type='text/html; charset=UTF-8']
	<head>
		<meta http-equiv="Content-Type" content="${content_type}" />
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
		<title>${Session.seo.title}</title>
		<link rel="icon" href="/images/headmark32ico${suffix}" mce_href="/images/headmark32.ico${suffix}" type="image/x-icon">
		<link rel="shortcut icon" href="/images/headmark16.ico${suffix}" mce_href="/images/headmark16.ico${suffix}" type="image/x-icon">
		[#nested]
	</head>
[/#macro]

[#macro headBasic title content_type='text/html; charset=UTF-8']
	<head>
		<meta http-equiv="Content-Type" content="${content_type}" />
		<title>${title}</title>
		[#nested]
	</head>
[/#macro]

[#macro headBoot title charset='utf-8']
	<head>
		<meta charset="${charset}">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="robots" content="all" />
		<meta name="author" content="ORZ_Tech" />
		<meta name="Copyright" content="ORZ_Tech" />
		<meta name="Description" content="${Session.seo.description}" />
		<meta name="Keywords" content="${Session.seo.keywords}" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="apple-mobile-web-app-status-bar-style" content="default"/>
		<title>${Session.seo.title}</title>
		<link href="/bootstrap/css/bootstrap.css" rel="stylesheet">
	    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!--[if lt IE 9]>
	      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	    <![endif]-->
		[#nested]
	    <script src="http://cdn.bootcss.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	</head>
[/#macro]

[#macro body class='']
<body class="${class}">
	[#nested]
</body>
[/#macro]

[#macro includes src='']
	[#if src?is_sequence || (src?is_string && src != '')]
		[#if src?is_string]
			[#local _srcs = [src] /]
		[#elseif src?is_sequence]
			[#local _srcs = src /]
		[/#if]
		[#list _srcs as _src]
			[#include "${inc[_src]!_src}" /]
		[/#list]
	[/#if]
[/#macro]

[#macro script src='']
	[#if src?is_sequence || (src?is_string && src != '')]
		[#if src?is_string]
			[#local _srcs = [src] /]
		[#elseif src?is_sequence]
			[#local _srcs = src]
		[/#if]
		[#list _srcs as _src]
			<script type='text/javascript' src='${scripts[_src]!_src}'></script>
		[/#list]
	[/#if]
	
	<script type="text/javascript">
		[#nested]
	</script>
[/#macro]

[#macro money value type=1]
	[#if type == 1]
		￥${value?string(',###.00')}
	[#else]
		${value?string(',###.00')}元
	[/#if]
[/#macro]

[#macro style src='']
	[#if src?is_sequence || (src?is_string && src != '')]
		[#if src?is_string]
			[#local _srcs = [src] /]
		[#elseif src?is_sequence]
			[#local _srcs = src /]
		[/#if]
		[#list _srcs as _src]
			<link rel="stylesheet" type="text/css" href="${styles[_src]!_src}"/>
		[/#list]
	[/#if]
	<style>
		[#nested]
	</style>
[/#macro]

[#assign scripts={
	'jquery':'/js/jquery/jquery.1.9.js',
	'manage':'/js/manage/manage.js?v=2014021902',
	'workspace':'/js/manage/workspace.js',
	'jqueryui':'/js/jqueryui/js/jquery-ui-1.10.4.custom.min.js',
	'kindeditor':'/editor/kindeditor.js',
	'kindeditorLang':'/editor/lang/zh_CN.js',
	'upload':'/js/upload/ajaxUpload.js?v=2014021902',
	'unslider':'/js/unslider/unslider.js',
	'unslider_min':'/js/unslider/unslider.min.js',
	'flatui':'/js/flatui/flat-ui.js',
	'application':'/js/flatui/application.js',
	'select':'/js/select.js',
	'popup':'/js/popupBox.js',
	'input':'/js/views/input.js',
	'picupload':'/js/views/picupload.js',
	'ajaxUpload':'/js/upload/ajaxUpload.js'
}/]

[#assign styles={
	'bootstrap':'/bootstrap/css/bootstrap.css',
	'manage':'/css/manage/manage.css',
	'workspace':'/css/manage/workspace.css',
	'login':'/css/manage/login.css',
	'jqueryui':'/js/jqueryui/css/ui-lightness/jquery-ui-1.10.4.custom.min.css',
	'css':'/css/views/css.css',
	'card':'/css/views/card.css',
	'page':'/css/views/page.css',
	'flatui':'/css/manage/flat-ui.css'
}/]


[#macro subImg src='' type='small']
	[#if type=="big"]
		[#if src?index_of("s_") > 0]
			${src?replace("s_", "")}
		[#else]
			${src}
		[/#if]
	[#else]
		[#if src?index_of("s_") > 0]
			${src}
		[#else]
			${src?substring(0, src?last_index_of("/") + 1)}s_${src?substring(src?last_index_of("/") + 1)}
		[/#if]
	[/#if]
[/#macro]

[#macro nginxImg src type width height style='' class='' id='']
	<img id="${id}" src="${src}!${type}${width}x${height}.jpg" style="${style}" class="${class}" />
[/#macro]

[#global nginxImg = nginxImg /]
[#global subImg = subImg /]
[#global nocache = nocache /]
[#global html = html /]
[#global head = head /]
[#global headBasic = headBasic /]
[#global headBoot = headBoot /]
[#global headIE8 = headIE8 /]
[#global body = body /]
[#global includes = includes /]
[#global script = script /]
[#global style = style /]
[#global money = money /]
[#global moneySign = moneySign /]

[#include '/ftl/page.ftl']
[#include '/ftl/pageFront.ftl']

[#include '/ftl/includes.ftl']
