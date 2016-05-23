[#ftl]
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>H+ 后台主题UI框架 - 基本表单</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="/framework/hplus/favicon.ico"> 
    <link href="/framework/hplus/css/bootstrap.min14ed.css?v=3.3.7" rel="stylesheet">
    <link href="/framework/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="/framework/hplus/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="/framework/hplus/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
    <link href="/framework/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
		
	<script>
		function check(){
			var aHTML=$(".summernote").code();
			$('#brief').val(aHTML);
			return true;
		}
	</script>	
	
</head>

<body class="white-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Bug报告</h5>
                        <div class="ibox-tools">
                             <a onclick="location='/manage/bug/list.do?state=0&projectid=${t.projectid}'" style="margin-right:10px;">
                                <i class="glyphicon glyphicon-arrow-left"></i>
                            </a>
                          
                            <a onclick="location='/manage/bug/list.do?state=0&projectid=${t.projectid}'">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                    		<div class="col-md-12">
                                <button type="button" id="loading-example-btn" class="btn btn-white btn-sm" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                            </div>
                       		[#if list]
                       			[#list list.items as t]
	                            <div class="form-group" >
	                                <div class="col-sm-12" style="margin-top:20px;">
	                                	<p style="font-weight:bold;">编号:#${t.id}</p>
										<p>Title: ${t.title} &nbsp;&nbsp;&nbsp;&nbsp;
										[#if t.state ==0]
	                                		 <span class="label label-danger">New Bug</span>
	                                	[/#if]
	                                	[#if t.state ==2]
	                                		 <span class="label label-primary">待确认</span>
	                                	[/#if]
	                                   
	                                   	[#if t.state ==1]
	                                		 <span class="label label-default">已修复</span>
	                                	[/#if]   
	                                	
	                                	</p>    
	                                	<p>创建于:[#if t.addtime?? && t.addtime??]${t.addtime?string('yyyy-MM-dd')}[/#if] &nbsp;&nbsp;&nbsp; 
	                                	[#if Session.bugmod !=1]
	                                	分配： <a href="javascript:;"><img alt="image" class="img-circle" src="[#if t.user??]${t.user.photo}[/#if]" style="height:32px;width:32px;"></a>[#if t??&& t.user??]${t.user.name}[/#if]</p>
	                                	[/#if]
	                                	<br/>   
	                                	[#if t.pic]            
										<a href="${t.pic}"><img src="${t.pic}" style="height:100;margin-bottom:20px;"/></a>
										[/#if]
										<br/>
										${t.brief}
										<br/>
	                                </div>
	                            </div>
	                             <div class="col-sm-12" style="height:2px;background-color:grey;margin-top:10px;"></div>
                            	[/#list]
                       		[/#if]
                            
                            
                    </div>
                </div>
            </div>
        </div>
    </div>

    
    <script src="/framework/hplus/js/jquery.min.js?v=2.1.4"></script>
    <script src="/framework/hplus/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="/framework/hplus/js/content.min.js?v=1.0.0"></script>
	<script type='text/javascript' src='/js/upload/ajaxUpload.js?v=2014021902'></script>
	<script src="/js/upload/imgBigUpload.js"></script>
   
</body>
</html>
