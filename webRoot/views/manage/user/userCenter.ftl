[#ftl]
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>项目管理系统</title>
    <link rel="shortcut icon" href="/framework/hplus/favicon.ico"> 
    <link href="/framework/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="/framework/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <!-- Morris -->
    <link href="/framework/hplus/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="/framework/hplus/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href="/framework/hplus/css/animate.min.css" rel="stylesheet">
    <link href="/framework/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row">
          
           
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>项目进度</h5>
                        <div class="ibox-tools">
                             <a onclick="window.location.reload();">
                                <i class="glyphicon glyphicon-repeat"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content ibox-heading">
                        <h3>还有约${wcountxf}个Bug需要修复</h3>
                        <small>努力工作吧</small>
                       <p>
                        [#if list??]
							[#list list as type]
                        	 	[#if type.num>0]
                        	 		<button type="button" class="btn btn-outlinebtn-default"  onclick="window.open('/manage/bug/myreport.do?state=0&projectid=${type.projectid}')">${type.projectname}(${type.num})</button>
                        	 	[/#if]
                        	[/#list]
						[/#if]	 
       					</p>
                    </div>
                    <div class="ibox-content timeline">
                       
                        <div class="timeline-item">
                            <div class="row">
                                <div class="col-xs-3 date">
                                    	到今天为止
                                    <br/>
                                    <small class="text-navy"></small>
                                </div>
                                <div class="col-xs-7 content">
                                    <p class="m-b-xs"><strong>你一共修复了${countxf}个bug</strong>
                                    </p>
                                    
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

        </div>
    </div>
    <script src="/framework/hplus/js/jquery.min.js?v=2.1.4"></script>
    <script src="/framework/hplus/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="/framework/hplus/js/plugins/flot/jquery.flot.js"></script>
    <script src="/framework/hplus/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="/framework/hplus/js/plugins/flot/jquery.flot.spline.js"></script>
    <script src="/framework/hplus/js/plugins/flot/jquery.flot.resize.js"></script>
    <script src="/framework/hplus/js/plugins/flot/jquery.flot.pie.js"></script>
    <script src="/framework/hplus/js/plugins/peity/jquery.peity.min.js"></script>
    <script src="/framework/hplus/js/demo/peity-demo.min.js"></script>
    <script src="/framework/hplus/js/content.min.js?v=1.0.0"></script>
    <script src="/framework/hplus/js/plugins/jquery-ui/jquery-ui.min.js"></script>
    <script src="/framework/hplus/js/plugins/gritter/jquery.gritter.min.js"></script>
    <script src="/framework/hplus/js/plugins/sparkline/jquery.sparkline.min.js"></script>
    <script src="/framework/hplus/js/demo/sparkline-demo.min.js"></script>
    <script>
       $(document).ready(function() {
       	[#if wcountxf>0]
   		 	WinMove();
		    setTimeout(function() {
		        $.gritter.add({
		            title: "您还有${wcountxf}个Bug需要修改",
		            text: '',
		            time: 10000
		        })
		    },
		    2000);
		   [/#if]
		});
 </script>
</body>

</html>
