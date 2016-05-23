[#ftl]
[#macro singlephoto imgurl='' title='' url='' dorder='' urldel='']

	<li style="margin-top:20px;">
		<a href="${imgurl}">
		<img src="${imgurl}" style="height:145px;"/>
		</a>
		<div style="text-align:center;margin-top:10px;">
			<a href="${urldel}"><img src="/framework/utils/photowall/img/x.png" onclick="return confirm('你确定要删除吗？');" style="float:right;margin-top:3px;margin-right:10px;" class="hand"/></a>
			&nbsp;&nbsp;
			<!-- <img src="/framework/utils/photowall/img/check.png" style="float:right;margin-top:10px;margin-right:10px;cursor:point;"/>&nbsp;&nbsp; -->
			<input type="text" class="dorder" style="margin-top:5px;margin-right:10px;cursor:point;width:40px;float:right;line-height: 20px;border: 2px solid #DCE4EC;border-radius: 4px;text-align:center;" 
			value="${dorder}" url ="${url}"/>
		</div>
	</li>

	
[/#macro]
[#global singlephoto = singlephoto/]