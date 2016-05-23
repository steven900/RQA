[#ftl]

[#macro imgUploadSmallBig  title='' id='' name='' folder='' width='' height='' smallWidth='' smallHeight='' picurl='' type='0']
	[#if type=0]
	<tr>
		<th class="w100">${title}</th>
		<td>
		[#-- 	
		 	ajaxUpload(id,name,folder,width,height,smallWidth,smallHeight)
		--]
		<input id="[#if id ??]${id}[#else]${name}Id[/#if]" type="file" name="upload" onchange="ajaxUpload('[#if id ??]${id}[#else]${name}Id[/#if]','${name}','[#if folder ??]${folder}[#else]${name}[/#if]',${width},${height},${smallWidth},${smallHeight});"/>
		<input type="hidden" id="${name}" name="${name}"   value="${picurl}"/>
		<br/>
			<img id="${name}displayImage" src="${picurl}"/> 
		</td>
	</tr>
	[#else]
		<input id="[#if id ??]${id}[#else]${name}Id[/#if]" type="file" name="upload" onchange="ajaxUpload('[#if id ??]${id}[#else]${name}Id[/#if]','${name}','[#if folder ??]${folder}[#else]${name}[/#if]',${width},${height},${smallWidth},${smallHeight});"/>
		<input type="hidden" id="${name}" name="${name}"   value="${picurl}"/>
		<br/>
		<img id="${name}displayImage" src="${picurl}" style="height:200px;" /> 
		<br/>
	[/#if]
[/#macro]
[#global imgUploadSmallBig = imgUploadSmallBig/]