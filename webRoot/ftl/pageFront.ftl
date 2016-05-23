[#ftl]

[#macro pageFront data show_page=2]

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


<ul class="pageUl">
	[#if current > 1]
		<li class="pageLi"><a href="${data.urlPrefix}${current-1}${data.urlSuffix}" class="prev"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
	[/#if]
	[#list start_page..end_page as p]
		<li class="pageLi[#if p==current] active[/#if]"><a href="${data.urlPrefix}${p}${data.urlSuffix}">${p}</a></li>
	[/#list]
	[#if end_page < total - 1]
		<li class="pageLi ellipsis">&middot;&middot;&middot;</li>
	[/#if]
	[#if end_page < total]
		<li class="pageLi"><a href="${data.urlPrefix}${total}${data.urlSuffix}">${total}</a></li>
	[/#if]
	[#if current < total]
		<li class="pageLi"><a href='${data.urlPrefix}${current+1}${data.urlSuffix}' class="next"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
	[/#if]
</ul>
[/#macro]

[#macro pageFrontAjax data show_page=2]

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


<ul class="pageUl">
	[#if current > 1]
		<li class="pageLi"><a href="javascript:void(0);" class="prev" onclick="jumpPage('${current-1}');"><span class="glyphicon glyphicon-chevron-left"></span></a></li>
	[/#if]
	[#list start_page..end_page as p]
		<li class="pageLi[#if p==current] active[/#if]"><a href="javascript:void(0);" onclick="jumpPage('${p}');">${p}</a></li>
	[/#list]
	[#if end_page < total - 1]
		<li class="pageLi ellipsis">&middot;&middot;&middot;</li>
	[/#if]
	[#if end_page < total]
		<li class="pageLi"><a href="javascript:void(0);" onclick="jumpPage('${total}');">${total}</a></li>
	[/#if]
	[#if current < total]
		<li class="pageLi"><a href="javascript:void(0);" class="next" onclick="jumpPage('${current+1}');"><span class="glyphicon glyphicon-chevron-right"></span></a></li>
	[/#if]
</ul>
[/#macro]

[#global pageFrontAjax = pageFrontAjax /]
[#global pageFront = pageFront /]