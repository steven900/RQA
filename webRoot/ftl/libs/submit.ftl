[#ftl]

[#macro submit backurl=""]
<tr>
	<th></th>
	<td colspan="2">
	<div class="fl">
		<input title="保存并提交当前数据" type="submit" value="保存" class="btn btn-success" />
	</div>
	[#if backurl ?? && backurl !='']
	<div class="fr">
		<a title="返回上一级" class="btn btn-base fl" href="${backurl}"/>返回上级</a>
	</div>
	[/#if]
	</td>
</tr>
[/#macro]
[#global submit = submit/]
