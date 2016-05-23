[#ftl]
[@html]
	[@head_nt title='managemenu']
	[/@head_nt]
	[@bodynt title='managemenu' backurl='${forwardUrlBack}' tips='']
				<form  action="/manage/managemenu/save.do" method="post" onSubmit="">
					[#if managemenu && managemenu.id]<input type="hidden" name="id"  value="${managemenu.id}"/>[/#if]
					<input type="hidden" name="forwardUrlBack"  value="${forwardUrlBack}"/>
					<table width="100%">
					[@editinput title='name' id='name' name='name' value='${managemenu.name}' maxlength='200' number='0' /]
					[#if managemenu.pid ?? &&   managemenu.pid  >0]
						[@editinput title='url' id='url' name='url' value='${managemenu.url}' maxlength='800' number='0' /]
					[/#if]
					<input type="hidden" name="pid"  value="[#if managemenu.pid]${managemenu.pid}[#else]0[/#if]"/>
					[@submit backurl='${forwardUrlBack}'/]
					</table>
				</form>
	[/@bodynt]
[/@html]
