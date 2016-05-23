[#ftl]
[@html]
	[@head_list title='managemenus']
	[/@head_list]
	[#assign title='managemenus']
	[#assign url='/manage/managemenus/edit.do?forwardUrl=${forwardUrl}&pid=${managemenus.pid}']
	[@body_list title='${title}' addurl='${url}' tips=''] 
				<span class="blank8"></span>
				<!--列表区-->
				<div class="main_lst">
					<table>
						<thead>
							<tr>
							<td >title</td>
							[#if managemenus ?? && managemenus.pid !=0]
							<td >url</td>
							[/#if]
							<td class="w100">通过</td>
							<td class="w100">排序</td>
							<td class="w100">操作</td>
							</tr>
						</thead>
						<tbody>
							[#if list][#list list.items as list]
							<tr>
							<td><a href="javascript:;" onclick="openurl('/manage/managemenus/list.do?pid=${list.id}');">${list.title}</a></td>
							[#if managemenus ?? && managemenus.pid !=0]
							<td>${list.url}</td>
							[/#if]
							<td>[@checkbox url='/manage/managemenus/pass.do?id=${list.id}&forwardUrl=${forwardUrl}&pass=' value='${list.pass}'/]</td>
							<td>[@dorder url='/manage/managemenus/dorder.do?id=${list.id}&forwardUrl=${forwardUrl}&dorder="' value='${list.dorder}' /]</td>
							<td>
								<a href="/manage/managemenus/edit.do?id=${list.id}&forwardUrl=${forwardUrl}">修改</a>
								<a href="/manage/managemenus/delete.do?id=${list.id}&forwardUrl=${forwardUrl}" onclick="return confirm('确定要删除?');">删除</a>
							</td>
							</tr>
							[/#list][/#if]
						</tbody>
					</table>
				</div>
				[#if list]<div class="pagination">[@page data=list.page/]</div>[/#if]
	[/@body_list]
[/@html]
