[#ftl]

[#macro select title='']
	<tr>
		<th class="w100">${title}</th>
		<td>
			[#nested]
		</td>
	</tr>
[/#macro]
[#global select = select/]
