[#ftl]
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>H+ 后台主题UI框架 - 基本表单</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="/framework/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="/framework/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="/framework/hplus/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="/framework/hplus/css/animate.min.css" rel="stylesheet">
    <link href="/framework/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
      <link href="/framework/hplus/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="/framework/hplus/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">
    
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
                        <h5>需求管理</h5>
                        <div class="ibox-tools">
                            <a  onclick="location='${forwardUrlBack}'">
                                <i class="glyphicon glyphicon-arrow-left"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form method="post" action="/manage/ruser/save.do" class="form-horizontal" onsubmit="return check();">
                        	[#if t?? && t.id>0]<input type="hidden" name="id" value="${t.id}"/>[/#if]
                        	<input type="hidden" name="forwardUrlBack" value="${forwardUrlBack}"/>
                        	<input type="hidden" name="type" value="${t.type}"/>
                        	
                            <div class="form-group">
                                <label class="col-sm-2 control-label">标题</label>
                                <div class="col-sm-10">
                                    <input type="text" name ="title" class="form-control" value="${t.title}">
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-2 control-label">项目</label>
                                <div class="col-sm-10">
                                	${t.projectName}
                                    <input type="hidden" name ="projectid" value="${t.projectid}" class="form-control">
                                </div>
                            </div>
                            
                          <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">上传</label>
								<div class="col-sm-4">
									<input type="text" placehold = "上传图片" class="form-control" id="img" name="url" value="${t.url}">
									<input id="imgId" type="file" name="file" onchange="ajaxUpload('imgId','img','img',800,800,800,800);" style="display:none;"/>
	                            </div>
	                            <div class="col-sm-4">
	                            	<button type="button" class="btn btn-w-m btn-default" onclick="$('#imgId').click();">上传图片</button>
	                             </div>
                            </div>
                            
                            
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">快照</label>
								<div class="col-sm-4">
								 <input type="text" name ="shortphoto" value="" class="form-control">
	                            </div>
                            </div>
                            
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">时间</label>
                                <div class="col-sm-4">
                                    <input type="text" class="form-control" name="addtime">
                                </div>
                            </div>
                            
                            <div class="hr-line-dashed"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">描述</label>
                                <input type="hidden" name="brief" id="brief" value="${t.brief}"/>
                             </div>
                            <div class="form-group" style="width:1000px;margin:0 auto;">
                                <div class="summernote col-sm-4">
									${t.brief}
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
    <script src="/framework/hplus/js/plugins/iCheck/icheck.min.js"></script>
    <script src="/framework/hplus/js/plugins/summernote/summernote.min.js"></script>
    <script src="/framework/hplus/js/plugins/summernote/summernote-zh-CN.js"></script>
    <script type='text/javascript' src='/js/upload/ajaxUpload.js?v=2014021902'></script>
    <script>
        
        	function ajaxUpload(id,name,folder,width,height,smallWidth,smallHeight) {
				//var params = 'folder='+folder+'&width='+width+"&height="+height+"&fixed="+fixed+"&size=10000";
				var params = 'folder='+folder+'&bigWidth='+width+'&bigHeight='+height+'&bigSize=100000&bigFixed=0&smallWidth='+smallWidth+'&smallHeight='+smallHeight+'&scale=1&scaleBg=0&watermark=0&smallSize=60&size=5000';
				$.ajaxFileUpload({
					url: '/filemanage/upload/fileupload.do?' + params,
					secureuri: false,
					fileElementId: id,
					dataType: 'json',
					success: function(data, status) {
						if(data.filename == '') {
							if(data.message != ''){
								alert(data.message);
							}else{
								alert('上传失败，请检查图片是否符合要求！');
							}
						}else{
						
							var dtdata = eval(data);
							var url = dtdata.data.result;
							$("#"+name).val(url);
						}
					},
					error: function(data, status, e) {
						alert('上传失败');
					}
				});
			}

    </script>
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
