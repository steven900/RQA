[#ftl]
[@html]
	[@head_nt title='news']
 		<script src="/framework/utils/layer-v2.0/layer/layer.js"></script>
 		<script>
 		function openurl(url){
	 		layer.open({
	 		    type: 2,
	 		    title: 'layer mobile页',
	 		    shadeClose: true,
	 		    shade: 0.4,
	 		    area: ['680px', '90%'],
	 		    content: url
	 		    }); 
 		}	
 		</script>
	[/@head_nt]
	[@bodynt title='新闻管理' backurl='' tips='']
				
			<input type="button" onclick="openurl('/framework/utils/photowall/index.html');" value="button"/>	
				
	[/@bodynt]
[/@html]