[#ftl]
[@html]
	[@head_list title='manageauth']
	[/@head_list]
	[@body_list title='manageauth' addurl='/manage/manageauth/edit.do?forwardUrl=${forwardUrl}' tips=''] 
[@search url='/manage/manageauth/list.do'
					<label for="">主键:</label>
					<input type="text" name="id" class="vm inputBox w100" value="[#if manageauth.id ??]${manageauth.id}[/#if]"/>
					<label for="">menuid:</label>
					<input type="text" name="menuid" class="vm inputBox w100" value="[#if manageauth.menuid ??]${manageauth.menuid}[/#if]"/>
				[/@search]
				<span class="blank8"></span>
				<!--列表区-->
				<div class="main_lst">
					<table>
						<thead>
							<tr>
							<td >主键</td>
							<td >menuid</td>
							<td class="w100">操作</td>
							</tr>
						</thead>
						<tbody>
							[#if list][#list list.items as list]
							<tr>
							<td>${list.id}</td>
							<td>${list.menuid}</td>
							<td>
								<a href="/manage/manageauth/edit.do?id=${list.id}&forwardUrl=${forwardUrl}">修改</a>
								<a href="/manage/manageauth/delete.do?id=${list.id}&forwardUrl=${forwardUrl}" onclick="return confirm('确定要删除?');">删除</a>
							</td>
							</tr>
							[/#list][/#if]
						</tbody>
					</table>
				</div>
				[#if list]<div class="pagination">[@page data=list.page/]</div>[/#if]
	[/@body_list]
[/@html]
