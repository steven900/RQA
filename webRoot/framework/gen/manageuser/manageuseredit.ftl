[#ftl]
[@html]
	[@head_nt title='manageuser']
	[/@head_nt]
	[@bodynt title='manageuser' backurl='${forwardUrlBack}' tips='']
				<form  action="/manage/manageuser/save.do" method="post" onSubmit="">
					[#if manageuser && manageuser.id]<input type="hidden" name="id"  value="${manageuser.id}"/>[/#if]
					<input type="hidden" name="forwardUrlBack"  value="${forwardUrlBack}"/>
					<table width="100%">
						[@editinput title='用户名' id='username' name='username' value='${manageuser.username}' maxlength='100' number='0' /]
						[@editinput title='密码' id='password' name='password' value='${manageuser.password}' maxlength='100' number='0' /]
						<tr><th class="w100">权限</th>
						<td>
							<select name="level" class="vm inputBox w200">
								<option value="1">超级用户</option>
								<option value="2">普通用户</option>
							</select>
						</td>
						[@imgUploadSmallBig title='图片' id='imgId' name='img' folder='img' width='800' height='800' smallWidth='800' smallHeight='800' picurl='${manageuser.img}'/]
						[@submit backurl='${forwardUrlBack}'/]
					</table>
				</form>
	[/@bodynt]
[/@html]
