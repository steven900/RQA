[#ftl]
[@html]
	[@head_list title='managemenu']
	
	[/@head_list]
	[@body_list title='managemenu' addtitle='添加大类' addurl='/manage/managemenu/edit.do?pid=0&forwardUrl=${forwardUrl}' tips=''] 
				<div class="search">
					<form action="/manage/managemenu/list.do" method="get">
					<label for="">name:</label>
					<input type="text" name="name" class="vm inputBox w100" value="[#if managemenu.name ??]${managemenu.name}[/#if]"/>
					<input title="查询" type="submit" class="btn btn-warnning" value="查询">
					</form>
				</div>
				<span class="blank8"></span>
				<!--列表区-->
				<div class="main_lst">
					<table>
						<thead>
							<tr>
								<td class="w100">name</td>
								<td>url</td>
								<td class="w100">排序</td>
								<td class="w100">通过</td>
								<td class="w100">操作</td>
							</tr>
						</thead>
						<tbody>
							[#if list]
								[#list list.items as list]
							<tr>
							
							<td><a href="/manage/managemenu/list.do?pid=${list.id}&forwardUrl=${forwardUrl}">${list.name}</a></td>
							<td>${list.url}</td>
							<td>
								[@dorder url='/manage/managemenu/dorder.do?id=${list.id}&forwardUrl=${forwardUrl}&dorder=' value='${list.dorder}' /]
							</td>
							<td>
								[@checkbox 
									url='/manage/managemenu/pass.do?id=${list.id}&forwardUrl=${forwardUrl}&pass=' 
									value='${list.pass}'
									/]
							</td>
							<td>
								<a href="/manage/managemenu/edit.do?pid=${list.id}&forwardUrl=${forwardUrl}">添加</a>
								
								<a href="/manage/managemenu/edit.do?id=${list.id}&forwardUrl=${forwardUrl}">修改</a>
								<a href="/manage/managemenu/delete.do?id=${list.id}&forwardUrl=${forwardUrl}" onclick="return confirm('确定要删除?');">删除</a>
							</td>
							</tr>
								[/#list]
							[/#if]
						</tbody>
					</table>
				</div>
				[#if list]<div class="pagination">[@page data=list.page/]</div>[/#if]
	[/@body_list]
[/@html]
