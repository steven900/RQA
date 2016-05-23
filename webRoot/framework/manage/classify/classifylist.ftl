[#ftl]
[@html]
	[@headBasic title='classify']
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
						<li class="active">分类管理</li>
					</ul>
				</div>
				<!--添加按钮-->
				<div class="add_btn"><a title="classify" class="btn btn-success" href="/manage/classify/edit.do?pclassifyid=[#if classify.pclassifyid]${classify.pclassifyid}[#else]0[/#if]&forwardUrl=${forwardUrl}"><span class="fui-plus-circle mr3"></span>添加分类</a></div>
			</div>
			<!--页面主体-->
			<div class="in_main">
				<!--说明栏-->
				<div class="tips"></div>
				<!--搜索区-->
				<div class="search">
					<form action="/manage/classify/list.do" method="get">
					<label for="">类别:</label>
					<input type="text" name="title" class="vm inputBox w100" value="[#if classify.title ??]${classify.title}[/#if]"/>
					<input type="hidden" name="pclassifyid" class="vm inputBox w100" value="[#if classify.pclassifyid ??]${classify.pclassifyid}[/#if]"/>
					<input title="查询" type="submit" class="btn btn-warnning" value="查询">
					</form>
				</div>
				<span class="blank8"></span>
				<!--列表区-->
				<div class="main_lst">
					<table>
						<thead>
							<tr>
							<td>id</td>
							<td>类别</td>
							[#-- <td>pclassifyid</td> --]
							<td>dorder</td>
							<td>属性</td>
							<td>产品</td>
							<td>操作</td>
							</tr>
						</thead>
						<tbody>
							[#if list][#list list.items as list]
							<tr>
							<td>${list.id}</td>
							<td>[#if list.pclassifyid==0]<a href="/manage/classify/list.do?pclassifyid=${list.id}">${list.title}</a>[#else]${list.title}[/#if]</td>
							[#-- <td>${list.pclassifyid}</td> --]
							<td><input type="text" class="dorder vm inputBox w100"  url="/manage/classify/dorder.do?id=${list.id}&forwardUrl=${forwardUrl}&dorder="  value="[#if list.dorder ]${list.dorder}[/#if]"/></td>
							<td><a href="/manage/ecanproperty/list.do?classifyid=${list.id}&parentUrl=${forwardUrl}&urlflag=1">属性</a></td>
							<td><a href="/manage/ecproduct/list.do?classifyid=${list.id}&parentUrl=${forwardUrl}&urlflag=1">产品</a></td>
							<td>
								<a href="/manage/classify/edit.do?id=${list.id}&forwardUrl=${forwardUrl}">修改</a>
								<a href="/manage/classify/delete.do?id=${list.id}&forwardUrl=${forwardUrl}" onclick="return confirm('确定要删除?');">删除</a>
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
