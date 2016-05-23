[#ftl]

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Initialize Value for ComboTree - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="/easyui/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="/easyui/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="/easyui/easyui/demo/demo.css">
	<script type="text/javascript" src="/easyui/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="/easyui/easyui/jquery.easyui.min.js"></script>
	<style>
		.tree-folder{display:none;}
		.tree-file{display:none;}
	</style>
</head>
<body>

	<form action="/manage/manageuser/usersauthsave.do" method="post">
		<input type="hidden" name="userid" value="${userid}"/>
		<ul class="easyui-tree">
			<li>
				<span>权限管理</span>
				<ul>
					[#if list]
						[#list list as listzp]
							<li data-options="state:'open'">
								<span ><input type="checkbox" name="key" value="${listzp.id}" [#if listzp.menuid ??]checked="checked"[/#if]/>${listzp.title}</span>
								[#if listzp.menus]
									<ul>
										[#list listzp.menus as type]
										<li>
											<span><input type="checkbox" name="key" value="${type.id}" [#if type.menuid ??]checked="checked"[/#if]/>${type.title}</span>
										</li>
										[/#list]
									</ul>
								[/#if]
							</li>
						[/#list]
					[/#if]
				</ul>
			</li>
		</ul>
		
		<input type="submit" value="保存" style="margin-top:10px;margin-left:10px;"/>
	</form>
</body>
</html>