[#ftl]

[#macro editback backurl='']
	<!--侧边按钮-->
	[#if backurl ?? && backurl !='']
	<div class="side_btn"><a title="返回列表" class="btn btn-base mr10" href="${backurl}">返回列表</a></div>
	[/#if]
[/#macro]
[#global editback = editback/]