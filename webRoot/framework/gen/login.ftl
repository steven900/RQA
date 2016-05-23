[#ftl]
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>项目生成系统</title>

<meta name="viewport" content="width=device-width">


<link href="/framework/index/css/login.css" rel="stylesheet" type="text/css">

<script>
	if(window.parent==window){
	
	}else{
		window.parent.reLogin();
	}

</script>

</head>
<body>

<div class="login">
	<form action="/genv/loginSubmit.do" method="post" id="form">
		<div style="height:150px;width:640px;font-size:36px;text-align:center;line-height:150px;color:white;font-weight:bold;">项目生成系统</div>
		
		<div class="login_form">
			<div class="user">
				<input class="text_value" value="" name="username" type="text" id="username">
				<input class="text_value" value="" name="password" type="password" id="password">
			</div>
			<button class="button" id="submit" type="submit">登录</button>
		</div>
	
		<div id="tip" style="color:red;">${message}</div>
		
		<div class="foot">Copyright &copy; 系统管理 All rights reserved.</div>
	</form>
</div>

</body>
</html>