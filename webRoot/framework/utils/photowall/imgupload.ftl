[#ftl]
[@html]
	[@head_nt title='news']
	[/@head_nt]
	[@bodynt title='新闻管理' backurl='${forwardUrlBack}' tips='']
				<form  action="/manage/news/save.do" method="post" onSubmit="">
					[@imgUploadSmallBig title='${newtitle}' id='imgId' name='img' folder='img' width='800' height='800' smallWidth='800' smallHeight='800' picurl=''/]
					[@submit backurl='${forwardUrlBack}'/]
					</table>
				</form>
	[/@bodynt]
[/@html]
