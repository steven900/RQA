[#ftl]
[@html]
	[@headBasic title='ecanproperty']
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
						<li class="active">类别属性</li>
					</ul>
				</div>
				<!--添加按钮-->
				<div class="add_btn"><a title="ecanproperty" class="btn btn-success" href="/manage/properties/picklist.do?[#if ecanproperty.classifyid ??]classifyid=${ecanproperty.classifyid}&[/#if]forwardUrl=${forwardUrl}"><span class="fui-plus-circle mr3"></span>添加类别属性</a></div>
				[#if parentUrlecanproperty && parentUrlecanproperty!=""]
				<div class="side_btn"><a title="返回列表" class="btn btn-base mr10" href="${parentUrlecanproperty}">返回列表</a></div>
				[/#if]
			</div>
			<!--页面主体-->
			<div class="in_main">
				<!--说明栏-->
				<div class="tips"></div>
				<!--搜索区-->
				[#--
				<div class="search">
					<form action="/manage/ecanproperty/list.do" method="get">
					<label for="">类别:</label>
					<input type="text" name="classifyid" class="vm inputBox w300" value="[#if ecanproperty.classifyid ??]${ecanproperty.classifyid}[/#if]"/>
					<input title="查询" type="submit" class="btn btn-warnning" value="查询">
					</form>
				</div>
				--]
				<span class="blank8"></span>
				<!--列表区-->
				<div class="main_lst">
					<table>
						<thead>
							<tr>
							[#--<td>propertyid</td>
							<td>产品</td>
							<td>classify</td>
							--]
							<td>参数1</td>
							<td>参数2</td>
							<td>排序</td>
							<td>操作</td>
							</tr>
						</thead>
						<tbody>
							[#if list][#list list.items as list]
							<tr>
							[#--
							<td>${list.propertyid}</td>
							<td>${list.ecproductid}</td>
							<td>${list.classifyid}</td>
							 --]
							<td>${list.prostring}</td>
							<td>${list.provalue}</td>
							<td><input type="text" class="dorder vm inputBox w50"   url="/manage/ecanproperty/dorder.do?id=${list.id}&forwardUrl=${forwardUrl}&dorder="  value="[#if list.dorder ]${list.dorder}[/#if]"/></td>
							
							<td>
							[#--<a href="/manage/ecanproperty/edit.do?id=${list.id}&forwardUrl=${forwardUrl}">查看</a> --]
								<a href="/manage/ecanproperty/delete.do?id=${list.id}&forwardUrl=${forwardUrl}" onclick="return confirm('确定要删除?');">删除</a>
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
