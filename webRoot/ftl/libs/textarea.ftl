[#ftl]

[#macro textarea title='' id='' name='' value='']
	<tr><th class="w100">${title}</th>
	<td><textarea  name="${name}" id="${id}">${value}</textarea></td>
	</tr>
[/#macro]
[#global textarea = textarea/]