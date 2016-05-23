[#ftl]
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>H+ 后台主题UI框架 - 数据表格</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="/framework/hplus/favicon.ico"> <link href="/framework/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="/framework/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <!-- Data Tables -->
    <link href="/framework/hplus/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

    <link href="/framework/hplus/css/animate.min.css" rel="stylesheet">
    <link href="/framework/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	<style>
		.table > thead > tr > th {
		    border-bottom: 0px solid #DDD;
		    background-color:white;
		}
		.fl{float:left;}
		.fyclass{margin-top:8px;margin-right:5px;float:left;}
		.mb10{margin-bottom:10px;}
		.w200{width:200px;}
		.fr{float:right;}
		.ft14{font-size:14px;}
		a{color:#18a689;}
		a:hover{color:#286090!important;}
	</style>
	<script>
		function changeState(id){
			alert(id);
			 if($('#'+id).attr("checked")){
			 	alert(1);
			 }
			 alert('2');
		}
	</script>
</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>
							项目管理                        	
                        </h5>
                        <div class="ibox-tools">
                           
                            <a onclick="window.location.reload();">
                                <i class="glyphicon glyphicon-repeat"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="col-sm-1 mb10" style="float:right;"> 	
                        	<button type="button" class="btn btn-sm btn-primary ft14" style="float:right;">搜索</button>
                        </div>
                        <div class="col-sm-3 m-b-xs" style="float:right;">
                             <input placeholder="项目分配管理" class="form-control" type="text"> 
                        </div>
                        
                       
                        
                        <table class="table table-striped table-bordered table-hover " id="editable">
                            <thead>
                                <tr>
                                    <th style="width:100px;">项目名称</th>
                                    <th style="width:100px;">描述</th>
                                    <th style="width:100px;">添加时间</th>
                                    <th style="width:100px;">权限</th>
                                </tr>
                            </thead>
                            <tbody>
                            	[#if list ??]
                            	[#list list.items as type]
                                <tr class="gradeX">
                                    <td>${type.title}</td>
                                    <td>${type.brief}</td>
                                    <td>[#if type.addtime??]${type.addtime?string('yyyy-MM-dd')}[/#if]	</td>
                                    <td>
                                         <input type="checkbox" value="" name="rqa" id="rqa" [#if type.menuauth??&& type.menuauth.rqa==1]
												checked="checked" onchange="location='/manage/menuauth/rqa.do?userid=${userid}&projectid=${type.id}&rqa=0&forwardUrl=${forwardUrl}'"
											[#else]
												onchange="location='/manage/menuauth/rqa.do?userid=${userid}&projectid=${type.id}&rqa=1&forwardUrl=${forwardUrl}'"
											[/#if]/>
                                    	
                                    </td>
                                    
                                </tr>
                                [/#list]
                                [/#if]
                            </tbody>
                            <!--
                            <tfoot>
                                <tr>
                                    <th>标题</th>
                                    <th>内容</th>
                                    <th>添加时间</th>
                                    <th>操作</th>
                                </tr>
                            </tfoot>
                           -->
                        </table>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="/framework/hplus/js/jquery.min.js?v=2.1.4"></script>
    <script src="/framework/hplus/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="/framework/hplus/js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="/framework/hplus/js/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="/framework/hplus/js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="/framework/hplus/js/content.min.js?v=1.0.0"></script>
</body>
</html>
