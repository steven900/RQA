	function ajaxUpload(id,name,folder,width,height,smallWidth,smallHeight) {
				//var params = 'folder='+folder+'&width='+width+"&height="+height+"&fixed="+fixed+"&size=10000";
				var params = 'folder='+folder+'&bigWidth='+width+'&bigHeight='+height+'&bigSize=100000&bigFixed=0&smallWidth='+smallWidth+'&smallHeight='+smallHeight+'&scale=1&scaleBg=0&watermark=0&smallSize=60&size=5000';
				$.ajaxFileUpload({
					url: '/manage/upload/uploadBigSmallCheck.do?' + params,
					secureuri: false,
					fileElementId: id,
					dataType: 'json',
					success: function(data, status) {
						if(data.filename == '') {
							if(data.message != ''){
								alert(data.message);
							}else{
								alert('上传失败，请检查图片是否符合要求！');
							}
						}else{
							var url = data.domain + data.path + data.filename;
							url = data.domain + data.path + "s_" + data.filename;
							$("#"+name).val(url);
							$("#"+name+"displayImage").attr("src", url);
						}
					},
					error: function(data, status, e) {
						alert('上传失败');
					}
				});
			}
