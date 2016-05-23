[#ftl]
[#macro kinderEdit id=""]
		<script>
			$(function(){
               KindEditor.ready(function(K) {
            	   K.create('#${id}', kindEditorParams);  
         		}); 
           });
		</script>
[/#macro]
[#global kinderEdit = kinderEdit/]


