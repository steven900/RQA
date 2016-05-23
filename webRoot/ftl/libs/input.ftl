[#ftl]

[#macro editinput title='' id='' name='' value='' maxlength='80' number='0']
	<tr><th class="w100">${title}</th>
	<td>
		<input type="text"  
			[#if number ==1]onkeyup="value=value.replace(/[^0-9.]/g,'') "[/#if] 
			 maxlength="${maxlength}" class="vm inputBox w200" name="${name}"
			 id="${id}" 
			 value="[#if value ??]${value}[/#if]"/>
	</td>
	</tr>
[/#macro]
[#global editinput = editinput/]