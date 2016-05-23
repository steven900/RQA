[#ftl]
[@html]
	[@headBasic title='ecproduct']
		[@style src=['manage','jqueryui']/]
		<link href="/css/manage/flat-ui.css" rel="stylesheet">
		[@script src=['jquery','jqueryui','manage','upload','kindeditor','kindeditorLang']/]
      	<script src="/js/upload/ajaxUpload.js"></script>
		<script>
			$(function(){
               KindEditor.ready(function(K) {
         		}); 
         		
         		property();
         	 });
         	 
         	 function property(){
         	 	
         	 	var url = "/manage/ecproduct/getproperty.do?classifyid="+$('#classifyid').val()+"&nt="+new Date();
         	 	$.ajax({ 
         	 	 	url: url, 
         	 	 	context: document.body, 
         	 	 	success: function(data){
         	 	 		$('#propertydiv').html(data);
   					}
   				});
         	 }
		</script>
	[/@headBasic]
	[@body]
	<div class="main">
			<div class="cur_position">
				<div class="position">
					<ul class="breadcrumb">
						<li class="active">ecproduct</li>
					</ul>
				</div>
				<!--侧边按钮-->
				<div class="side_btn"><a title="返回列表" class="btn btn-base mr10" href="${forwardUrlBack}">返回列表</a></div>
			</div>
			<!--页面主体-->
			<div class="in_main">
				<!--说明栏-->
				<div class="tips"></div>
				<span class="blank8"></span>
				<div class="main_input">
				<form  action="/manage/ecproduct/save.do" method="post">
					<input type="hidden" name="forwardUrlBack"  value="${forwardUrlBack}"/>
					<table width="100%">
					
						[#include "/framework/manage/ecproduct/shareproperty.ftl"/]
					
					<tr>
						<th class="w100">类别</th><td>
						[#if list ??]
							<select id="classifyid" name="classifyid" onchange="property();">
							[#list list as type]
								<option value="${type.id}">${type.title}</option>
							[/#list]
							</select>
						[/#if]
						</td></tr>
					</tr>
					<tbody id="propertydiv">
					
					
					</tbody>
					<tr>
						<th></th>
						<td colspan="3">
						<div class="fl">
						<input title="保存并提交当前数据" type="submit" value="保存" class="btn btn-success" />
						</div>
						<div class="fr">
						<a title="返回上一级" class="btn btn-base fl" href="${forwardUrlBack}" />返回上级</a>
						</div>
						</td>
					</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	[/@body]
[/@html]
