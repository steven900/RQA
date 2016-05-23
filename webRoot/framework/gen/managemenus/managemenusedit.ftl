[#ftl]
[@html]
	[@head_nt title='managemenus']
	[/@head_nt]
	[@bodynt title='managemenus' backurl='${forwardUrlBack}' tips='']
				<form  action="/manage/managemenus/save.do" method="post" onSubmit="">
					[#if managemenus && managemenus.id]<input type="hidden" name="id"  value="${managemenus.id}"/>[/#if]
					<input type="hidden" name="forwardUrlBack"  value="${forwardUrlBack}"/>
					<input type="hidden" name="pid"  value="${managemenus.pid}"/>
					<table width="100%">
					[#if managemenus.pid ?? && managemenus.pid !=0]
						[@editinput title='url' id='url' name='url' value='${managemenus.url}' maxlength='200' number='0' /]
					[/#if]
					[@editinput title='title' id='title' name='title' value='${managemenus.title}' maxlength='200' number='0' /]
					[@submit backurl='${forwardUrlBack}'/]
					</table>
				</form>
	[/@bodynt]
[/@html]
