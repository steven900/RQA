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
    <link href="/framework/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
		
			
	
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>用户管理</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                          
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form method="post" action="/manage/user/save.do" class="form-horizontal">
                       		 [#if t?? && t.id>0 || t.id=-1]<input type="hidden" name="id" value="${t.id}"/>[/#if]
                        	<input type="hidden" name="forwardUrlBack" value="${forwardUrlBack}"/>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">姓名</label>

                                <div class="col-sm-4">
                                    <input type="text" name="name" value="${t.name}" class="form-control">
                                   
                                </div>
                                 
                            </div>
                                  <div class="hr-line-dashed"></div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">用户名</label>

                                <div class="col-sm-4">
                                    <input type="text" name="username" value="${t.username}" class="form-control">
                                   
                                </div>
                                 
                            </div>
                             <div class="hr-line-dashed"></div>
                           <div class="form-group">
                                <label class="col-sm-2 control-label">密码</label>

                                <div class="col-sm-4">
                                    <input type="text" name="password" value="${t.password}" class="form-control">
                                   
                                </div>
                                 
                            </div>
                            
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">头像</label>
								<div class="col-sm-3">
									<input type="text" id="img" name="photo" value="${t.photo}" placehold = "上传图片" class="form-control">
									<input id="imgId" type="file" name="upload" onchange="ajaxUpload('imgId','img','img',800,800,800,800);" style="display:none;"/>
									<br/>
									<img id="imgdisplayImage" src="${t.photo}" style="height:80px;"/> 
	                            </div>
	                            <div class="col-sm-4">
	                            	<button type="button" class="btn btn-w-m btn-default" onclick="$('#imgId').click();">上传图片</button>
	                             </div>
                            </div>
                           
                            <div class="form-group">
                                <label class="col-sm-2 control-label">限制权限</label>

                                <div class="col-sm-4">
                                    <select class="input-sm form-control input-s-sm inline" name="pass">
	                                    <option value="0" [#if t.pass==0]selected="selected"[/#if]>不限制</option>
	                                    <option value="1" [#if t.pass==1]selected="selected"[/#if]>限制</option>
	                                </select>
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
	<script type='text/javascript' src='/js/upload/ajaxUpload.js?v=2014021902'></script>
	<script src="/js/upload/imgBigUpload.js"></script>
    <script>
        $(document).ready(function(){
        	$(".summernote").summernote({height:300,lang:"zh-CN"})});
        	var edit=function(){$("#eg").addClass("no-padding");
        	$(".click2edit").summernote({lang:"zh-CN",focus:true})};
        	var save=function(){$("#eg").removeClass("no-padding");
        	var aHTML=$(".click2edit").code();
        	$(".click2edit").destroy();
        };
    </script>
</body>
</html>
