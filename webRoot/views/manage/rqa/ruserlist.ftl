[#ftl]
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>需求管理</title>

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
		function sendE(id){
			if(confirm("are you sure ?")){
				$.ajax({
						url: "/manage/ruser/sendEmail.do",
						data: {
							"id": id
						},
						type: "POST",
						success: function(data){
							layer.msg("success");
						},
						dataType: "json"
					});
			}
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
                        	[#if t ?? && t.type==2]报价单[/#if]
                        	[#if t ?? && t.type==1]需求管理[/#if]
                        	[#if t ?? && t.type==3]bug管理[/#if]
                        </h5>
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
                       [#if Session.auth ==1]
                        <div class="">
                            <a href="/manage/ruser/edit.do?type=${t.type}&forwardUrl=${forwardUrl}&projectid=${t.projectid}" class="btn btn-primary ">添加</a>
                        </div>
                        [/#if]
                        <!--
                        <div class="col-sm-2 m-b-xs" >
                        	<div id="editable_length" class="dataTables_length">
                        		<div class ="fyclass">每页 </div>
                        		<select class="form-control input-sm fl"  name="editable_length">
                        			<option value="10">10</option>
                        			<option value="25">25</option>
                        			<option value="50">50</option>
                        			<option value="100">100</option>
                        		</select> 
                        		<label class="fyclass">&nbsp;条</label>
                        	</div>
                       </div>
                       -->
                        <div class="col-sm-1 mb10" style="float:right;"> 	
                        	<button type="button" class="btn btn-sm btn-primary ft14" style="float:right;">搜索</button>
                        </div>
                        <div class="col-sm-6 m-b-xs" style="float:right;">
                             <input placeholder="内容" class="form-control" type="text"> 
                        </div>
                        
                       
                        
                        <table class="table table-striped table-bordered table-hover " id="editable">
                            <thead>
                                <tr>
                                    <th>标题</th>
                                    <th style="width:150px;">内容</th>
                                     <th style="width:100px;">发送邮件</th>
                                     <th style="width:100px;">邮件</th>
                                    <th style="width:100px;">添加时间</th>
                                    [#if Session.auth ==1]
                                    <th style="width:100px;">操作</th>
                                    [/#if]
                                </tr>
                            </thead>
                            <tbody>
                            	[#if list ??]
                            	[#list list.items as type]
                                <tr class="gradeX">
                                    <td>${type.title}</td>
                                    <td>
                                    	[#if type.shortphoto]
                                    		<a href="${type.shortphoto}" target="_blank">快照</a>
                                    	[/#if]
                                    	&nbsp;&nbsp;&nbsp;&nbsp;
                                    	[#if type.url]
                                    		<a href="${type.url}" target="_blank">源文件</a>
                                    	[/#if]
                                    </td>
                                    <td><span style="cursor:pointer;" onclick="sendE('${type.id}');">发送邮件</span></td>
                                    <td><a href="/manage/ruser/showEmail.do?id=${type.id}" target="_blank">邮件内容</a></td>
                                    <td>${type.addtime}</td>
                                    [#if Session.auth ==1]
                                    <td class="center">
                                    	<a href="/manage/ruser/edit.do?id=${type.id}&forwardUrl=${forwardUrl}&projectid=${t.projectid}"><i class="glyphicon glyphicon-pencil"></i></a>
                                    	&nbsp;&nbsp;
                                    	<a href="/manage/ruser/delete.do?id=${type.id}&forwardUrl=${forwardUrl}&projectid=${t.projectid}"><i class="glyphicon glyphicon-remove"></i></a>
                                    	
                                    </td>
                                    [/#if]
                                </tr>
                                [/#list]
                                [/#if]
                            </tbody>
                        </table>


						<!-- 分页模块 
						<div class="row">
							<div class="col-sm-6">
								<div aria-relevant="all" aria-live="polite" role="alert" id="editable_info" class="dataTables_info">显示 1 到 9 项，共 9 项</div>
							</div>
							<div class="col-sm-6">
								<div id="editable_paginate" class="dataTables_paginate paging_simple_numbers">
									<ul class="pagination">
										<li id="editable_previous" tabindex="0" aria-controls="editable" class="paginate_button previous disabled">
											<a href="#">上一页</a>
										</li>
										<li tabindex="0" aria-controls="editable" class="paginate_button active">
											<a href="#">1</a>
										</li>
										<li tabindex="1" aria-controls="editable" class="paginate_button">
											<a href="#">2</a>
										</li>
										<li tabindex="2" aria-controls="editable" class="paginate_button">
											<a href="#">...</a>
										</li>
										<li tabindex="3" aria-controls="editable" class="paginate_button">
											<a href="#">4</a>
										</li>
										<li id="editable_next" tabindex="0" aria-controls="editable" class="paginate_button next disabled">
											<a href="#">下一页</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
-->

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
