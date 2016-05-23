[#ftl]


[#macro search url]
	<div class="search">
		<form action="${url}" method="get">
		[#nested]
		<input title="查询" type="submit" class="btn btn-warnning" value="查询">
		</form>
	</div>
[/#macro]
[#global search = search/]