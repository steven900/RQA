[#ftl]

[#macro textSearchLabel title='' id='' name='' value='']
	<label for="">${title}:</label>
	<input type="text" name="${name}" id="[#if id]${id}[#else]${name}[/#if]" class="vm inputBox w100" value="[#if value ??]${value}[/#if]"/>
[/#macro]
[#global textSearchLabel = textSearchLabel/]