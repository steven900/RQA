[#ftl]
[@html]
	[@headBasic title='properties']
		[@style src=['manage','jqueryui']/]
		<link href="/css/manage/flat-ui.css" rel="stylesheet">
		[@script src=['jquery','jqueryui','manage']/]
		<script src="/js/views/listinput.js"></script>
	[/@headBasic]
	[@body]
	<div class="main">
			<div class="cur_position">
				<div class="position">
					<ul class="breadcrumb">
						<li class="active">产品属性</li>
					</ul>
				</div>
				<!--添加按钮-->
				<div class="add_btn"><a title="properties" class="btn btn-success" href="/manage/properties/edit.do?forwardUrl=${forwardUrl}"><span class="fui-plus-circle mr3"></span>添加产品属性</a></div>
			</div>
			<!--页面主体-->
			<div class="in_main">
				<!--说明栏-->
				<div class="tips"></div>
				<!--搜索区-->
				<div class="search">
					<form action="/manage/properties/list.do" method="get">
					<label for="">名称:</label>
					<input type="text" name="name" class="vm inputBox w100" value="[#if properties.name ??]${properties.name}[/#if]"/>
					<input title="查询" type="submit" class="btn btn-warnning" value="查询">
					</form>
				</div>
				<span class="blank8"></span>
				<!--列表区-->
				<div class="main_lst">
					<table>
						<thead>
							<tr>
							[#--<td>dorder</td>--]
							<td>id</td>
							<td>名称</td>
							<td>参数名称</td>
							[#--<td>类别</td>--]
							<td>操作</td>
							</tr>
						</thead>
						<tbody>
							[#if list][#list list.items as list]
							<tr>
							[#-- <td><input type="text" class="dorder"  url="/manage/properties/dorder.do?id=${list.id}&forwardUrl=${forwardUrl}&dorder="  value="[#if list.dorder ]${list.dorder}[/#if]"/></td>--]
							<td>${list.id}</td>
							<td><a href="/manage/propertiesex/list.do?propertyid=${list.id}&parentUrl=${forwardUrl}&urlflag=1">${list.name}</a></td>
							<td>${list.paramname}</td>
							[#--<td>${list.ptype}</td>--]
							<td>
								<a href="/manage/properties/edit.do?id=${list.id}&forwardUrl=${forwardUrl}">修改</a>
								<a href="/manage/properties/delete.do?id=${list.id}&forwardUrl=${forwardUrl}" onclick="return confirm('确定要删除?');">删除</a>
							</td>
							</tr>
							[/#list][/#if]
						</tbody>
					</table>
				</div>
				[#if list]<div class="pagination">[@page data=list.page/]</div>[/#if]
		</div>
	</div>
	[/@body]
[/@html]
