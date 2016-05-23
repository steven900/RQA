[#ftl]
[#macro body_list title='' addurl='' addtitle='' tips='' backurl='' backtitle='返回列表']
		<div class="main">
			[#if title || addurl]
			<div class="cur_position">
				[#if title]
				<div class="position">
					<ul class="breadcrumb">
						<li class="active">${title}</li>
					</ul>
				</div>
				[/#if]
				<!--添加按钮-->
				[#if addurl]
				<div class="add_btn">
					<a title="user" class="btn btn-success" href="${addurl}">
						<span class="fui-plus-circle mr3"></span>[#if addtitle]${addtitle}[#else]添加[/#if]
					</a>
				</div>
				[/#if]
				
				[#if backurl?? && backurl!='']
				<div class="side_btn"><a title="${backtitle}" class="btn btn-base mr10" href="${backurl}">${backtitle}</a></div>
				[/#if]
			</div>
			[/#if]
			<!--页面主体-->
			<div class="in_main">
				<!--说明栏-->
				[#if tips  && tips!='']
				<div class="tips">${tips}</div>
				[/#if]
				<!--搜索区-->
				[#nested]
			</div>
		</div>
[/#macro]
[#global body_list = body_list/]