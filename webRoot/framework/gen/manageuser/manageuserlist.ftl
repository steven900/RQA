[#ftl]
[@html]
	[@head_list title='manageuser']
	[/@head_list]
	[@body_list title='manageuser' addurl='/manage/manageuser/edit.do?forwardUrl=${forwardUrl}' tips=''] 
				[@search url='/manage/manageuser/list.do']
					<label for="">主键:</label>
					<input type="text" name="id" class="vm inputBox w100" value="[#if manageuser.id ??]${manageuser.id}[/#if]"/>
					<label for="">通过:</label>
					<input type="text" name="pass" class="vm inputBox w100" value="[#if manageuser.pass ??]${manageuser.pass}[/#if]"/>
					<label for="">图片:</label>
					<input type="text" name="img" class="vm inputBox w100" value="[#if manageuser.img ??]${manageuser.img}[/#if]"/>
				[/@search]
				<span class="blank8"></span>
				<!--列表区-->
				<div class="main_lst">
					<table>
						<thead>
							<tr>
							<td class="w100">头像</td>
							<td>用户名</td>
							<td>密码</td>
							<td class="w100">级别</td>
							<td class="w50">通过</td>
							<td class="w100">权限管理</td>
							<td class="w100">操作</td>
							</tr>
						</thead>
						<tbody>
							[#if list][#list list.items as list]
							<tr>
							<td><img height="80" src="[#if list.img ]${list.img}[/#if]"/></td>
							<td>${list.username}</td>
							<td>${list.password}</td>
							<td>${list.level}</td>
							<td>[@checkbox url='/manage/manageuser/pass.do?id=${list.id}&forwardUrl=${forwardUrl}&pass=' value='${list.pass}'/]</td>
							<td><a href="javascript:;" onclick="openurl('/manage/manageuser/usersauth.do?userid=${list.id}');">权限管理</a></td>
							<td>
								<a href="/manage/manageuser/edit.do?id=${list.id}&forwardUrl=${forwardUrl}">修改</a>
								<a href="/manage/manageuser/delete.do?id=${list.id}&forwardUrl=${forwardUrl}" onclick="return confirm('确定要删除?');">删除</a>
							</td>
							</tr>
							[/#list][/#if]
						</tbody>
					</table>
				</div>
				[#if list]<div class="pagination">[@page data=list.page/]</div>[/#if]
	[/@body_list]
[/@html]
