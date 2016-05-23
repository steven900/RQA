[#ftl]
[@html]
	[@head_nt title='manageauth']
	[/@head_nt]
	[@bodynt title='manageauth' backurl='${forwardUrlBack}' tips='']
				<form  action="/manage/manageauth/save.do" method="post" onSubmit="">
					[#if manageauth && manageauth.id]<input type="hidden" name="id"  value="${manageauth.id}"/>[/#if]
					<input type="hidden" name="forwardUrlBack"  value="${forwardUrlBack}"/>
					<table width="100%">
					[@editinput title='menuid' id='menuid' name='menuid' value='${manageauth.menuid}' maxlength='12' number='1' /]
					[@submit backurl='${forwardUrlBack}'/]
					</table>
				</form>
	[/@bodynt]
[/@html]
