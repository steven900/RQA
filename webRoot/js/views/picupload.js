			
			 function ajaxUpload(id,folder,bigWidth,bigHeight,bigSize,bigFixed,smallWidth,smallHeight,scale,scaleBg,watermark,smallSize,size) {
					id= id+"Id";
					var params = "folder="+folder+"&bigWidth="+bigWidth+"&bigHeight="+bigHeight+"&bigSize="+bigSize+"&bigFixed="+bigFixed+"&smallWidth="+smallWidth+"&smallHeight="+smallHeight+"&scale="+scale+"&scaleBg="+scaleBg+"&watermark="+watermark+"&smallSize="+smallSize+"&size="+size;
					$.ajaxFileUpload({
						url: '/manage/upload/uploadBigSmallCheck.do?' + params,
						secureuri: false,
						fileElementId: id,
						dataType: 'json',
						success: function(data, status) {
							if(data.filename == '') {
								if(data.message != ''){
								}else{
									alert('上传失败，请检查图片是否符合要求！');
								}
							}else{
								var url = data.domain + data.path + data.filename;
								var smallUrl = data.domain + data.path + "s_" + data.filename;
								$("#"+id).val(url);
								$("#"+id+"displayImage").attr("src", smallUrl);
								$("#dispalyA").attr("href", url);
							}
						},
						error: function(data, status, e) {
							alert(data.filename + ":" + data.message);
							alert(status);
							alert(e);
							alert('上传失败');
						}
					});
				}
			
			
			 function ajaxUploaduploadBigSmallCheck(id,folder,bigWidth,bigHeight,bigSize,bigFixed,smallWidth,smallHeight,scale,scaleBg,watermark,smallSize,size) {
					id= id+"Id";
					var params = "folder="+folder+"&bigWidth="+bigWidth+"&bigHeight="+bigHeight+"&bigSize="+bigSize+"&bigFixed="+bigFixed+"&smallWidth="+smallWidth+"&smallHeight="+smallHeight+"&scale="+scale+"&scaleBg="+scaleBg+"&watermark="+watermark+"&smallSize="+smallSize+"&size="+size;
					alert(params);
					$.ajaxFileUpload({
						url: '/manage/upload/uploadBigSmallCheck.do?' + params,
						secureuri: false,
						fileElementId: id,
						dataType: 'json',
						success: function(data, status) {
						alert(data);
							if(data.filename == '') {
								if(data.message != ''){
									alert(data.message);
								}else{
									alert('上传失败，请检查图片是否符合要求！');
								}
							}else{
								var url = data.domain + data.path + data.filename;
								var smallUrl = data.domain + data.path + "s_" + data.filename;
								$("#productpic").val(url);
								$("#productdisplayImage").attr("src", smallUrl);
								$("#dispalyA").attr("href", url);
							}
						},
						error: function(data, status, e) {
							alert(data.filename + ":" + data.message);
							alert(status);
							alert(e);
							alert('上传失败');
						}
					});
				}
		