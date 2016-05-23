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
						<li class="active">properties</li>
					</ul>
				</div>
				<!--添加按钮-->
				<div class="add_btn"><a title="properties" class="btn btn-success" href="/manage/properties/edit.do?forwardUrl=${forwardUrl}"><span class="fui-plus-circle mr3"></span>properties</a></div>
			</div>
			<!--页面主体-->
			<div class="in_main">
				<!--说明栏-->
				<div class="tips"></div>
				<!--搜索区-->
				<div class="search">
					<form action="/manage/properties/list.do" method="get">
					<label for="">名称:</label>
					<input type="text" name="name" class="vm inputBox w300" value="[#if properties.name ??]${properties.name}[/#if]"/>
					<input title="查询" type="submit" class="btn btn-warnning" value="查询">
					</form>
				</div>
				<span class="blank8"></span>
				<!--列表区-->
				<div class="main_lst">
					<table>
						<thead>
							<tr>
							<td>名称</td>
							<td>操作</td>
							</tr>
						</thead>
						<tbody>
							[#if list][#list list.items as list]
							<tr>
							<td><a href="/manage/propertiesex/list.do?propertyid=${list.id}" target="_blank">${list.name}</a></td>
							<td>
								<a href="/manage/ecanproperty/pick.do?id=${list.id}[#if properties.classifyid]&classifyid=${properties.classifyid}[/#if] ">选择</a>
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
