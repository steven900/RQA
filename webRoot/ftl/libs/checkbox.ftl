[#ftl]

[#macro checkbox url='' value='' default='1']
	
	<input 
		type="checkbox" class="pass vm inputBox"  
		value="${value}"  
		[#if value==1]
			onchange="location='${url}0'"
		[#else]
			onchange="location='${url}1'"
		[/#if]
		[#if value ==default ]checked="checked"[/#if]"/>
	
[/#macro]
[#global checkbox = checkbox/]