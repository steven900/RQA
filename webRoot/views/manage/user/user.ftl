[#ftl]
<!DOCTYPE html>
<html>


<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>H+ 后台主题UI框架 - 联系人</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="/framework/hplus/favicon.ico"> 
    <link href="/framework/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="/framework/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="/framework/hplus/css/animate.min.css" rel="stylesheet">
    <link href="/framework/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>

<body class="white-bg">

	
    <div class="wrapper wrapper-content animated fadeInRight">
    	<div class="row">
    		
    		<div class="col-sm-3">
    			<button type="button" class="btn btn-primary btn-sm" style="float:left;margin-right:50px;margin-bottom:20px;" onclick="location='/manage/user/edit.do?forwardUrl=${forwardUrl}'">
    			
    			<i class="glyphicon glyphicon-plus"></i>
    			&nbsp;&nbsp;<i class="glyphicon glyphicon-user"></i></button>
    		</div>
    		<div class="col-sm-6">
    		
    		</div>
    		<div class="col-sm-3">
                <div class="input-group style="float:right;">
                    <input placeholder="请输入关键词" class="input-sm form-control" type="text"> <span class="input-group-btn">
                        <button type="button" class="btn btn-sm btn-primary"> 搜索</button> </span>
                </div>
            </div>
    		
    	</div>
    	
        <div class="row">
            
            [#if list ??]
            [#list list.items as type]
            
           	<div class="col-sm-4">
                <div class="contact-box">
                        <div class="col-sm-4">
                            <div class="text-center">
                                <img alt="image" class="img-circle m-t-xs img-responsive" style="height:80px;" src="${type.photo}">
                                <div class="m-t-xs font-bold">Tech</div>
                            </div>
                        </div>
                        <div class="col-sm-8">
                            <h3><strong>姓名：${type.name}</strong></h3>
                            <p>账号：&nbsp;${type.username}</p>
                            
                        <button class="btn btn-primary btn-circle"
                        	style="font-size:20px;float:right;padding:0;" 
                        	onclick="location='/manage/user/edit.do?forwardUrl=${forwardUrl}&id=${type.id}'"><i class="fa fa-times"></i>
                        </button>
                        <button class="btn btn-primary btn-circle " style="font-size:20px;float:right;padding:0;font-size:16px;margin-right:10px;"
                         onclick="location='/manage/user/edit.do?forwardUrl=${forwardUrl}&id=${type.id}'" >
                        	<i class="glyphicon glyphicon-pencil"></i>
                        </button>
                        </div>
                        
                        <div class="clearfix"></div>
                </div>
            </div>
            [/#list]
            [/#if]
            
            
        </div>
    </div>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.6"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script>
        $(document).ready(function(){$(".contact-box").each(function(){animationHover(this,"pulse")})});
    </script>
</body>

</html>
