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
		
	
</head>

<body class="white-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>项目报价</h5>
                    </div>
                    
                    
                     <table class="table table-striped table-bordered table-hover " id="editable">
                            <thead>
                                <tr>
                                    <th style="width:200px;">类目</th>
                                    <th style="width:100px;">报价</th>
                                    <th style="width:100px;"> 周期</th>
                                    <th>描述</th>
                                </tr>
                            </thead>
                            <tbody>
                            	[#if list ??]
                            	[#list list as type]
                            	[#if type.price ??]
                                <tr class="gradeX">
                                    <td>${type.price.title}</td>
                                    <td>[#if type.price.price >0]${type.price.price}￥[/#if]</td>
                                    <td>${type.price.days}天</td>
                                    <td class="center">
                                    	${type.price.url}
                                    </td>
                                </tr>
                                [/#if]
                                [/#list]
                                [/#if]
                                  <tr class="gradeX">
                                    <td colspan="4" style="text-align:right;font-weight:bold;">
                                    	总价 ：${moneyall}￥ &nbsp;&nbsp;&nbsp;&nbsp;总时间： ${sumday}天&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </td>
                                </tr>
                            </tbody>
                        </table>
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
