[#ftl]

[#if plist]
	[#list plist as type]
		
		[#if type.paramname]
			<tr><th class="w100">${type.name}</th>
				<td>
					<select name="${type.paramname}">
						[#list type.proex as tp]
							<option value="${tp.id}">${tp.name}</option>
						[/#list]
					</select>
				</td>
			</tr>
		[/#if]
		
	[/#list]
[/#if]

