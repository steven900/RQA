$(function(){
		$(".dorder").each(function() {
				$(this).bind('keyup afterpaste keypress', function(e) {
					 this.value = this.value.replace(/([^0-9-]*)(-?)([0-9]*)(\.?)([0-9]{0,2}).*/g,'$2$3$4$5');
					 if(e.which == 13) {  
					 		var url =  this. getAttribute("url");
					 		window.location.href=url+this.value;
					 }
			   });
		});
		
	
		
});