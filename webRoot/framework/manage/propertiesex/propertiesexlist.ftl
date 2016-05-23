[#ftl]
[@html]
	[@headBasic title='propertiesex']
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
						<li class="active">产品子属性</li>
					</ul>
				</div>
				<!--添加按钮-->
				<div class="add_btn"><a title="propertiesex" class="btn btn-success" href="/manage/propertiesex/edit.do?propertyid=${propertiesex.propertyid}&forwardUrl=${forwardUrl}"><span class="fui-plus-circle mr3"></span>添加子属性</a></div>
				<!--侧边按钮-->
				[#if parentUrlecproduct && parentUrlecproduct!=""]
				<div class="side_btn"><a title="返回列表" class="btn btn-base mr10" href="${parentUrlecproduct}">返回列表</a></div>
				[/#if]
			</div>
			<!--页面主体-->
			<div class="in_main">
				<!--说明栏-->
				<div class="tips"></div>
				<!--搜索区-->
				<div class="search">
					<form action="/manage/propertiesex/list.do" method="get">
					<label for="">名称:</label>
					<input type="text" name="name" class="vm inputBox w300" value="[#if propertiesex.name ??]${propertiesex.name}[/#if]"/>
					[#-- 
					<label for="">propertyid:</label>
					<input type="text" name="propertyid" class="vm inputBox w300" value="[#if propertiesex.propertyid ??]${propertiesex.propertyid}[/#if]"/>
					--]
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
							<td>dorder</td>
							<td>操作</td>
							</tr>
						</thead>
						<tbody>
							[#if list][#list list.items as list]
							<tr>
							<td>${list.name}</td>
							<td><input type="text" class="dorder"  url="/manage/propertiesex/dorder.do?id=${list.id}&forwardUrl=${forwardUrl}&dorder="  value="[#if list.dorder ]${list.dorder}[/#if]"/></td>
							<td>
								<a href="/manage/propertiesex/edit.do?id=${list.id}&forwardUrl=${forwardUrl}">修改</a>
								<a href="/manage/propertiesex/delete.do?id=${list.id}&forwardUrl=${forwardUrl}" onclick="return confirm('确定要删除?');">删除</a>
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
