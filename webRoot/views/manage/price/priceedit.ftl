[#ftl]
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>H+ 后台主题UI框架 - 基本表单</title>

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

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Bug管理</h5>
                        <div class="ibox-tools">
                        
                        	 <a onclick="location='${forwardUrlBack}'" style="margin-right:10px;">
                                <i class="glyphicon glyphicon-arrow-left"></i>
                            </a>
                          
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form method="post" action="/manage/price/save.do" class="form-horizontal" onsubmit="return check();">
                        	<input type="hidden" name="forwardUrlBack" value="${forwardUrlBack}"/>
                        	[#if t.id?? && t.id>0]
                        	<input type="id" name="id" value="${t.id}"/>
                        	[/#if]
                            <div class="form-group">
                                <label class="col-sm-2 control-label">标题</label>

                                <div class="col-sm-4">
                                    <input type="text" name="title" value="${t.title}" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label">说明</label>

                                <div class="col-sm-4">
                                    <input type="text" name="url" value="${t.url}" class="form-control">
                                </div>
                            </div>
                            
                             <div class="hr-line-dashed"></div>
                            
                            <div class="form-group">
                                <label class="col-sm-2 control-label">时间</label>

                                <div class="col-sm-4">
                                    <input type="text" placeholder="天" name="days" value="${t.days}" class="form-control">
                                </div>
                            </div>
                            
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">价钱</label>

                                <div class="col-sm-4">
                                    <input type="text" name="price" value="${t.price}" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" type="submit">保存内容</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    
    <script src="/framework/hplus/js/jquery.min.js?v=2.1.4"></script>
    <script src="/framework/hplus/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="/framework/hplus/js/content.min.js?v=1.0.0"></script>
    <script src="/framework/hplus/js/plugins/summernote/summernote.min.js"></script>
    <script src="/framework/hplus/js/plugins/summernote/summernote-zh-CN.js"></script>
	<script type='text/javascript' src='/js/upload/ajaxUpload.js?v=2014021902'></script>
	<script src="/js/upload/imgBigUpload.js"></script>
    <script>
        $(document).ready(function(){
        	$(".summernote").summernote({height:300,lang:"zh-CN"})});
        	var edit=function(){$("#eg").addClass("no-padding");
        	//$(".click2edit").summernote({lang:"zh-CN",focus:true})};
        	//var save=function(){$("#eg").removeClass("no-padding");
        	//var aHTML=$(".click2edit").code();
        	//$(".click2edit").destroy();
        };
    </script>
</body>
</html>
