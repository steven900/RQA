[#ftl]

[#macro bodynt title='' backurl='' tips='']
	<body>
		<div class="main">
			[#if title ?? || backurl ??]
			<div class="cur_position">
				[#if title ??]
				<div class="position">
					<ul class="breadcrumb">
						<li class="active">${title}</li>
					</ul>
				</div>
				[/#if]
				<!--侧边按钮-->
				[#if backurl ?? && backurl !='']
				<div class="side_btn"><a title="返回列表" class="btn btn-base mr10" href="${backurl}">返回列表</a></div>
				[/#if]
			</div>
			[/#if]
			<!--页面主体-->
			<div class="in_main">
				<!--说明栏-->
				[#if tips ?? && tips !='']
				<div class="tips">
					${tips}
				</div>
				[/#if]
				<span class="blank8"></span>
				<div class="main_input">
				[#nested]
				</div>
			</div>
		</div>
	</body>
[/#macro]
[#global bodynt = bodynt/]