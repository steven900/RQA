[#ftl]
[@html]
	[@headBasic title='ecproduct']
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
						<li class="active">产品管理</li>
					</ul>
				</div>
				<!--添加按钮-->
				<div class="add_btn"><a title="ecproduct" class="btn btn-success" href="/manage/ecproduct/[#if ecproduct.classifyid ??]edit[#else]add[/#if].do?forwardUrl=${forwardUrl}[#if ecproduct.classifyid ??]&classifyid=${ecproduct.classifyid}[/#if]"><span class="fui-plus-circle mr3"></span>添加产品</a></div>
				[#if parentUrlclassify && parentUrlclassify!=""]
				<div class="side_btn"><a title="返回列表" class="btn btn-base mr10" href="${parentUrlclassify}">返回列表</a></div>
				[/#if]
			</div>
			<!--页面主体-->
			<div class="in_main">
				<!--说明栏-->
				<div class="tips"></div>
				<!--搜索区-->
				[#include "/framework/manage/ecproduct/productsearch.ftl"]
				<div class="search">
					<form action="/manage/ecproduct/list.do" method="get">
					[#-- 
					<label for="">available:</label>
					<input type="text" name="available" class="vm inputBox w100" value="[#if ecproduct.available ??]${ecproduct.available}[/#if]"/>
					<label for="">类别:</label>
					<input type="text" name="classifyid" class="vm inputBox w100" value="[#if ecproduct.classifyid ??]${ecproduct.classifyid}[/#if]"/>
					<label for="">排序:</label>
					<input type="text" name="dorder" class="vm inputBox w100" value="[#if ecproduct.dorder ??]${ecproduct.dorder}[/#if]"/>
					--]
					[#-- 
					<label for="">id:</label>
					<input type="text" name="id" class="vm inputBox w100" value="[#if ecproduct.id ??]${ecproduct.id}[/#if]"/>
						--]
					<label for="">名称:</label>
					<input type="text" name="name" class="vm inputBox w100" value="[#if ecproduct.name ??]${ecproduct.name}[/#if]"/>
					[#-- 
					<label for="">properties:</label>
					<input type="text" name="properties" class="vm inputBox w100" value="[#if ecproduct.properties ??]${ecproduct.properties}[/#if]"/>
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
							<td>类别</td>
							
							<td>id</td>
							<td>名称</td>

							<td>价格</td>
							<td>排序</td>
							[#-- <td>properties</td>--]
							<td class="w150">操作</td>

							</tr>
						</thead>
						<tbody>
							[#if list][#list list.items as list]
							<tr>
							<td>${list.classifyid}</td> 
							<td>${list.id}</td>
							<td>${list.name}</td>

							<td>${list.price}</td>
	
							<td><input type="text" class="dorder vm inputBox w50"  url="/manage/ecproduct/dorder.do?id=${list.id}&forwardUrl=${forwardUrl}&dorder="  value="[#if list.dorder ]${list.dorder}[/#if]"/></td>
							[#-- <td>${list.properties}</td> --]

							<td>
								<a href="/manage/ecproduct/edit.do?id=${list.id}&forwardUrl=${forwardUrl}">修改</a>
								<a href="/manage/ecproduct/delete.do?id=${list.id}&forwardUrl=${forwardUrl}" onclick="return confirm('确定要删除?');">删除</a>
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
