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
                        <form method="post" action="/manage/bug/save.do" class="form-horizontal" onsubmit="return check();">
                       		 [#if t?? && t.id>0]<input type="hidden" name="id" value="${t.id}"/>[/#if]
                        	<input type="hidden" name="forwardUrlBack" value="${forwardUrlBack}"/>
                        	<input type="hidden" name="projectid" value="${t.projectid}"/>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">标题</label>

                                <div class="col-sm-4">
                                    <input type="text" name="title" value="${t.title}" class="form-control">
                                   
                                </div>
                                [#if t?? && t.id>0]
                               
                                <div class="col-sm-2">
                                 			[#if t.state ==0]
                                        		 <span class="label label-danger">New Bug
                                        	[/#if]
                                        	[#if t.state ==2]
                                        		 <span class="label label-primary">待确认
                                        	[/#if]
                                           
                                           	[#if t.state ==1]
                                        		 <span class="label label-primary">待确认
                                        	[/#if]
                                </div>
                                 [/#if]
                            </div>
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">状态</label>
								<div class="col-sm-4">
	                            	  <select class="input-sm form-control input-s-sm inline" name="state">
	                                    <option value="0" [#if t.state==0]selected="selected"[/#if]>新建</option>
	                                    <option value="1" [#if t.state==1]selected="selected"[/#if]>已修复</option>
	                                    <option value="2" [#if t.state==2]selected="selected"[/#if]>待确认</option>
	                                </select>
	                               </div>
                               
                            </div>
                            
                           
                            
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                            	  <label class="col-sm-2 control-label">分配</label>
                            	  <div class="col-sm-4">
	                            	 <select class="input-sm form-control input-s-sm inline" name="sentTo">
	                            	 	<option value="0" [#if t.sentTo==t.id]selected="selected"[/#if]>待分配</option>
	                            	 	[#if Session.bugmod !=1]
	                            	 		[#if list ??]
			                            	 	[#list list as type]
			                            	 	[#if type.pass!=1]
			                                  	  		<option value="${type.id}" [#if t.sentTo==type.id]selected="selected"[/#if]>${type.name}</option>
			                                  	  [/#if]
			                                  	[/#list]
			                                  [/#if]
	                            	 	[#else]
			                            	 	<option value="0" [#if t.sentTo>0]selected="selected"[/#if]>已分配</option>
			                                  
	                                 	 [/#if]
	                                </select>
	                               </div>
                            </div>
                            
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">上传</label>
								<div class="col-sm-2">
									<input type="text" placehold = "上传图片" class="form-control">
									<input id="imgId" type="file" name="upload" onchange="ajaxUpload('imgId','img','img',800,800,800,800);" style="display:none;"/>
									<input type="hidden" id="img" name="pic"   value="${t.pic}"/>
									<br/>
									<img id="imgdisplayImage" src="${t.pic}" style="height:80px;"/> 
	                            </div>
	                            <div class="col-sm-4">
	                            	<button type="button" class="btn btn-w-m btn-default" onclick="$('#imgId').click();">上传图片</button>
	                             </div>
                            </div>
                           
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">描述</label>
                                <input type="hidden" name="brief" id="brief" value="${t.brief}"/>
                             </div>
                            <div class="form-group" style="width:900px;margin:0 auto;">
                                <div class="summernote col-sm-4">
									${t.brief}
		                        </div>
                            </div>
                           
                           
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2">
                                    <button class="btn btn-primary" type="submit">保存内容</button>
                                    <button class="btn btn-white">取消</button>
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
