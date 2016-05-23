[#ftl]
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>需求项目</title>

    <link rel="shortcut icon" href="/framework/hplus/favicon.ico"> 
    <link href="/framework/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="/framework/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="/framework/hplus/css/animate.min.css" rel="stylesheet">
    <link href="/framework/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    

</head>

<body class="gray-bg">

    <div class="wrapper wrapper-content animated fadeInUp">
        <div class="row">
            <div class="col-sm-12">

                <div class="ibox">
                    <div class="ibox-title">
                        <h5>Bug管理</h5>
                        <div class="ibox-tools">
                            <a href="/manage/bug/edit.do?forwardUrl=${forwardUrl}&projectid=${t.projectid}" class="btn btn-primary btn-xs">新建Bug</a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row m-b-sm m-t-sm">
                            <div class="col-md-1">
                                <button type="button" id="loading-example-btn" class="btn btn-white btn-sm" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                            </div>
                            <div class="col-md-11">
                                <div class="input-group">
                                	
                                	<button type="button" class="btn btn-outline [#if t.state ==-1]btn-primary[#else]btn-default [/#if]" onclick="location='/manage/bug/list.do?state=-1&projectid=${t.projectid}'">全部</button>
                                	<button type="button" class="btn btn-outline [#if t.state ==2]btn-info[#else]btn-default [/#if]" onclick="location='/manage/bug/list.do?state=2&projectid=${t.projectid}'">待确认</button>
                                	<button type="button" class="btn btn-outline [#if t.state ==1]btn-info[#else]btn-default [/#if]" onclick="location='/manage/bug/list.do?state=1&projectid=${t.projectid}'">已修复 </button>
                                	<button type="button" class="btn btn-outline [#if t.state ==0]btn-warning[#else]btn-default [/#if]" onclick="location='/manage/bug/list.do?state=0&projectid=${t.projectid}'">未修复</button>
                       				 
                       				 <button type="button" class="btn btn-outlinebtn-default" style="margin-left:20px;" onclick="window.open('/manage/bug/myreport.do?state=0&projectid=${t.projectid}')">我的报告</button>
                       				 <button type="button" class="btn btn-outlinebtn-default" style="margin-left:20px;" onclick="window.open('/manage/bug/report.do?projectid=${t.projectid}')">Bug报告</button>
                                </div>
                            </div>
                        </div>

                        <div class="project-list">

                            <table class="table table-hover">
                                <tbody>
                                   
                                   [#if list ??]
                                   	[#list list.items as type]
                                   	
                                   
                                    <tr>
                                        <td class="project-status">
                                        	[#if type.state ==0]
                                        		 <span class="label label-danger">New Bug
                                        	[/#if]
                                        	[#if type.state ==2]
                                        		 <span class="label label-primary">待确认
                                        	[/#if]
                                           
                                           	[#if type.state ==1]
                                        		 <span class="label label-default">已修复
                                        	[/#if]
                                           
                                        </td>
                                        <td class="project-title">
                                            <a href="">${type.title}</a>
                                            <br/>
                                            <small>编号：#${type.id} &nbsp;&nbsp;&nbsp;创建于 [#if type.addtime?? && type.addtime??]${type.addtime?string('yyyy-MM-dd')}[/#if]</small>
                                        </td>
                                        <td class="project-completion">
                                          
                                        </td>
                                        <td class="project-people">
                                        	[#if Session.bugmod !=1]
                                            <a href="javascript:;"><img alt="image" class="img-circle" src="[#if type.user??]${type.user.photo}[/#if]"></a>
                                       		&nbsp;&nbsp;[#if type.user??]${type.user.name}[/#if]
                                       		[#else]
                                       			程序员
                                       		[/#if]
                                        </td>
                                        <td class="project-actions">
                                            <a href="/manage/bug/bugCheck.do?id=${type.id}&forwardUrl=${forwardUrl}&projectid=${t.projectid}" class="btn btn-white btn-sm"><i class="glyphicon glyphicon-search"></i> 查看 </a>
                                           [#if auth ==1]
                                            <a href="/manage/bug/edit.do?id=${type.id}&forwardUrl=${forwardUrl}&projectid=${t.projectid}" class="btn btn-white btn-sm"><i class="glyphicon glyphicon-pencil"></i> 编辑 </a>
                                            [/#if]
                                            [#if type.state ==0]
                                            <a href="/manage/bug/updateBug.do?id=${type.id}&forwardUrl=${forwardUrl}&state=2&projectid=${t.projectid}" class="btn btn-white btn-sm"><i class="glyphicon glyphicon-check"></i>确认修复 </a>
                                            [/#if]
                                        </td>
                                    </tr>
                                    
                                    	[/#list]
                                   [/#if]
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <script src="/framework/hplus/js/jquery.min.js?v=2.1.4"></script>
    <script src="/framework/hplus/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="/framework/hplus/js/content.min.js?v=1.0.0"></script>
    </body>
</html>
