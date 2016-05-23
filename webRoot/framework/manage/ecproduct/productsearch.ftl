[#ftl]
[#if prolist]
	
	[#list prolist as type]
		<div style="float:left;width:100%;">
			<span style="float:left;margin-left:20px;">${type.name}</span>
			[#if type.proex]
				[#list type.proex as tp]
					<a href="/manage/ecproduct/list.do?condition=s${tp.id}v" style="margin-left:10px;">${tp.name}</a>
				[/#list]
			[/#if]
		</div>
	[/#list]
[/#if]