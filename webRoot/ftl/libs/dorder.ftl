[#ftl]
[#macro dorder url='' value='']
	
	<input type="text" class="dorder vm inputBox w30" style="text-align:center;" 
		url="${url}" 
		value="[#if value ]${value}[/#if]"/>
	
[/#macro]
[#global dorder = dorder/]