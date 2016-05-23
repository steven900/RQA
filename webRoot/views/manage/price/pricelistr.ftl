[#ftl]
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>项目管理</title>

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
		function check(priceid,projectid,id){
			var mark = 0;
		   if($("#"+id).is(':checked') ){
		   		mark =1;
		   }else{
		   
		   }	
		   var url = "/manage/price/ajax.do?priceid="+priceid+"&projectid="+projectid+"&pricemark="+mark;
		    $.getJSON(
			   url,
			   function(data){ 
			   
			   });			
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
                       
                        <form action="/manage/price/reportsave.do" method="post">
                        <table class="table table-striped table-bordered table-hover " id="editable">
                            <thead>
                                <tr>
                                    <th style="width:200px;">标题</th>
                                    <th>价钱</th>
                                    <th>时间</th>
                                    <th style="width:100px;">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            	[#if list ??]
                            	[#list list.items as type]
                                <tr class="gradeX">
                                    <td>${type.title}</td>
                                    <td>[#if type.price >0]${type.price}[/#if]</td>
                                    <td>${type.days}天</td>
                                    <td class="center">
                                    
                                    	<input type="checkbox" name="pricekey"  id="vl_${type.id}" [#if type.checked==1]checked="checked"[/#if] onclick="check('${type.id}','${projectid}','vl_${type.id}');"/>
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
						</form>
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
