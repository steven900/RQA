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
          });
		</script>
	[/@headBasic]
	[@body]
	<div class="main">
			<div class="cur_position">
				<div class="position">
					<ul class="breadcrumb">
						<li class="active">产品管理</li>
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
				<form  action="/manage/ecproduct/save.do" method="post" onSubmit="">
					[#if ecproduct && ecproduct.id]<input type="hidden" name="id"  value="${ecproduct.id}"/>[/#if]
					<input type="hidden" name="forwardUrlBack"  value="${forwardUrlBack}"/>
					<input type="hidden" name="classifyid" id="classifyid"  value="[#if ecproduct]${ecproduct.classifyid}[/#if]"/>
					<table width="100%">
					
					
					[#include "/framework/manage/ecproduct/shareproperty.ftl"/]
					[#if plist]
					[#list plist as type]
					[#if type.paramname]
					<tr><th class="w100">${type.name}</th>
						<td>
							<select name="${type.paramname}">
								[#list type.proex as tp]
									<option value="${tp.id}" [#if tp.id == type.selectedid]selected="selected"[/#if]>${tp.name}</option>
								[/#list]
							</select>
						</td>
					</tr>
					[/#if]
					[/#list]
					[/#if]
					
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
