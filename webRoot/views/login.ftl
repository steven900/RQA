<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
 	<title>项目管理系统</title>
    <link rel="shortcut icon" href="/framework/hplus/favicon.ico"> 
    <link href="/framework/hplus/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="/framework/hplus/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="/framework/hplus/css/animate.min.css" rel="stylesheet">
    <link href="/framework/hplus/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <div>
                <h1 class="logo-name">H+</h1>
            </div>
            <h3>欢迎使用 H+</h3>

            <form class="m-t" role="form" action="/manage/loginSubmit.do" method="post">
                <div class="form-group">
                    <input type="text" name="username" class="form-control" placeholder="用户名" required="">
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" placeholder="密码" required="">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>

            </form>
        </div>
    </div>
</body>
</html>
