[#ftl]

[#macro page data show_page=7]

[#assign total=data.pageCount /]
[#assign current=data.page /]
[#if current > total][#return][/#if]

[#assign start_page=(current-((show_page/2)?int)) /]
[#assign pages=[] /]
[#if  start_page > 1]
	[#assign pages=pages+[1] /]
[/#if]  
[#if  start_page > 2]
	[#assign pages=pages+[2] /]
[/#if]
[#if  start_page > 3]
	[#assign pages=pages+[0] /]
[/#if]
[#if  start_page < 1 ]
	[#assign start_page = 1 /]
[/#if]

[#assign end_page = (start_page+show_page)]
[#if end_page > total][#assign end_page=total][/#if]
[#assign pages=pages+(start_page..end_page) /]

[#if end_page < total]
	[#assign pages=pages+[0] /]
	[#if last && (end_page < total) ]
		[#assign pages=pages+[total] /]
	[/#if]
[/#if]

[#if more && (end_page >= total) ]
	[#assign pages=pages+[0] /]
[/#if]


<ul>
	[#if current > 1]
		<li><a href="${data.urlPrefix}${current-1}${data.urlSuffix}" class="prev">上一页</a></li>
	[/#if]
	[#list start_page..end_page as p]
		<li [#if p==current]class="active"[/#if]><a href="${data.urlPrefix}${p}${data.urlSuffix}">${p}</a></li>
	[/#list]
	[#if current < total]
		<li><a href='${data.urlPrefix}${current+1}${data.urlSuffix}' class="next">下一页</a></li>
	[/#if]
</ul>
[/#macro]

[#global page = page /]