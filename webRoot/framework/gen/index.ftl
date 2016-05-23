[#ftl]
[@html]
<head>
<title>系统管理</title>
<link rel="stylesheet" href="/easyui/jquery-easyui-1.3.5/themes/bootstrap/easyui.css" type="text/css" />
<link rel="stylesheet" href="/easyui/jquery-easyui-1.3.5/themes/icon.css" type="text/css" />
[@style src=['workspace','manage']/]
<script type="text/javascript" src="/easyui/easyui/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/easyui/jquery.easyui.min.js"></script>
[@script src=['workspace']/]
<script type="text/javascript">
function reLogin() {
	window.location.href="/genv/login.do";
}

</script>
</head>

<body class="easyui-layout">
	<div region="north" border="false" class="layoutNorth">
		<div class="logo">
			<span style="line-height: 48px;width: 240px;color: #fff;font-size: 30px;">系统管理</span>
		</div>
		<div class="nav">
			<div class="btn-toolbar">
				<div class="btn-group">
					<a class="btn btn-primary" href="javascript:void(0);" onclick="ws_reload();">刷新</a>
					<a class="btn btn-primary" href="javascript:void(0);" onclick="if(confirm('您确定要退出吗？')){window.location.href='/genv/logout.do';}">退出系统</a>
				</div>
			</div>
		</div>
	</div>
	
	
	<div id="westLayout" region="west" split="false" title=" " class="layoutWest">
		<div id="menus" class="easyui-accordion" animate="false" border="true" fit="false" inline="false">
			
			<div title="产品框架">
					<div class="menuButton menu_item" rel="/manage/classify/list.do" menuId="107" title="分类管理"><div class="menuLink"><a href='javascript:void(0);'>分类管理</a></div></div>
					<div class="menuButton menu_item" rel="/manage/ecproduct/list.do" menuId="110" title="产品管理"><div class="menuLink"><a href='javascript:void(0);'>产品管理</a></div></div>
					<div class="menuButton menu_item" rel="/manage/properties/list.do" menuId="118" title="产品属性管理"><div class="menuLink"><a href='javascript:void(0);'>产品属性管理</a></div></div>
			</div>
			
			<div title="项目管理">
				
				<div class="menuButton menu_item" rel="/genv/fromjava.do" menuId="119" title="系统生成4.0"><div class="menuLink"><a href='javascript:void(0);'>系统生成4.0</a></div></div>
			</div>
			
			<div title="微信开发">
				<div class="menuButton menu_item" rel="/manage/weixinfuwumenu/list.do" menuId="121" title="微信菜单管理"><div class="menuLink"><a href='javascript:void(0);'>微信菜单管理</a></div></div>
				<div class="menuButton menu_item" rel="/manage/weixinfuwumenu/weixin.do" menuId="122" title="微信菜单模拟"><div class="menuLink"><a href='javascript:void(0);'>微信菜单模拟</a></div></div>
			</div>
			<div title="菜单管理">
				<div class="menuButton menu_item" rel="/manage/manageuser/list.do" menuId="820" title="用户管理"><div class="menuLink"><a href='javascript:void(0);'>用户管理</a></div></div>
				<div class="menuButton menu_item" rel="/manage/managemenus/list.do?pid=0" menuId="821" title="菜单管理"><div class="menuLink"><a href='javascript:void(0);'>菜单管理</a></div></div>
			</div>
			
			<div title="初始化">
				<div class="menuButton menu_item" rel="/manage/managemenus/createsql.do?pid=0" menuId="99" title="菜单初始化"><div class="menuLink"><a href='javascript:void(0);'>菜单初始化</a></div></div>
				<div class="menuButton menu_item" rel="/manage/weixinfuwumenu/createsql.do" menuId="998" title="微信初始化"><div class="menuLink"><a href='javascript:void(0);'>微信初始化</a></div></div>
				<div class="menuButton menu_item" rel="/manage/weixinfuwumenu/createsqlwxpay.do" menuId="9981" title="微信支付初始化"><div class="menuLink"><a href='javascript:void(0);'>微信支付初始化</a></div></div>
			</div>
			
		</div>
	</div>
	
	<div region="center" title="">
		<div id="workspace" class="easyui-tabs" fit="true" border="false">
		</div>
	</div>
	
	
	<div id="mm" class="easyui-menu" style="width:150px;">
        <div id="mm-tabclose">关闭</div>
        <div id="mm-tabcloseall">全部关闭</div>
        <div id="mm-tabcloseother">除此之外全部关闭</div>
        <div class="menu-sep"></div>
        <div id="mm-tabcloseright">当前页右侧全部关闭</div>
        <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
	</div>
</body>
[/@html]