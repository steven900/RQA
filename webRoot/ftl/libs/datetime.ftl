[#ftl]

[#macro datetime title='' id='' name='' value='' maxlength='80' number='0']
	<script>
		$(function() {
			$("#${id}").datepicker(dateParams);
		});
	</script>
	<tr>
		<th class="w100 ">${title}</th>
		<td>
			<input type="text" 
				 class="vm inputBox w200" 
				 name="${name}" 
				 id="${id}"  
				 value="[#if value ?? && value!='']${value?string('yyyy-MM-dd')}[/#if]"/>
		</td>
	</tr>
[/#macro]
[#global datetime = datetime/]