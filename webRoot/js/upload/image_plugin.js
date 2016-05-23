KE.lang['invalidFlv'] = '请确认是否是flv文件';

/*-------------------------------------------------------------------------------------------*/

KE.lang['upload_image'] = "上传图片";
KE.plugin['upload_image'] = {
	click : function(id) {
		KE.util.selection(id);
		var dialog = new KE.dialog({
			id : id,
			cmd : 'upload_image',
			file : 'fccs/upload_image.html',
			width : 510,
			height : 340,
			title : KE.lang['upload_image'],
			yesButton : KE.lang['yes'],
			noButton : KE.lang['no']
		});
		dialog.show();
	},
	check : function(id) {
		var dialogDoc = KE.util.getIframeDoc(KE.g[id].dialog);
		var url = KE.$('url', dialogDoc).value;
		var title = KE.$('imgTitle', dialogDoc).value;
		var width = KE.$('imgWidth', dialogDoc).value;
		var height = KE.$('imgHeight', dialogDoc).value;
		var border = KE.$('imgBorder', dialogDoc).value;
		if (url.match(/\.(jpg|jpeg|gif|bmp|png)$/i) == null) {
			alert(KE.lang['invalidImg']);
			window.focus();
			KE.g[id].yesButton.focus();
			return false;
		}
		if (width.match(/^\d+$/) == null) {
			alert(KE.lang['invalidWidth']);
			window.focus();
			KE.g[id].yesButton.focus();
			return false;
		}
		if (height.match(/^\d+$/) == null) {
			alert(KE.lang['invalidHeight']);
			window.focus();
			KE.g[id].yesButton.focus();
			return false;
		}
		if (border.match(/^\d+$/) == null) {
			alert(KE.lang['invalidBorder']);
			window.focus();
			KE.g[id].yesButton.focus();
			return false;
		}
		return true;
	},
	exec : function(id) {
		KE.util.select(id);
		var iframeDoc = KE.g[id].iframeDoc;
		var dialogDoc = KE.util.getIframeDoc(KE.g[id].dialog);
		if (!this.check(id))
			return false;
		var url = KE.$('url', dialogDoc).value;
		var title = KE.$('imgTitle', dialogDoc).value;
		var width = KE.$('imgWidth', dialogDoc).value;
		var height = KE.$('imgHeight', dialogDoc).value;
		var border = KE.$('imgBorder', dialogDoc).value;
		this.insert(id, url, title, width, height, border);
	},
	insert : function(id, url, title, width, height, border) {
		var html = '';

		if (title != null && title != '' && title.length > 0) {
			html = '<center><table border="0"><tr><td align="center"></br><img src="'
					+ url + '" ';
			if (width > 0)
				html += 'width="' + width + '" ';
			if (height > 0)
				html += 'height="' + height + '" ';
			html += 'border="' + border
					+ '" /></br></td></tr><tr><td  align="center">' + title
					+ '</td></tr></table></center>';
		} else {
			html = '</br><img src="' + url + '" ';
			if (width > 0)
				html += 'width="' + width + '" ';
			if (height > 0)
				html += 'height="' + height + '" ';
			html += 'border="' + border + '" /></br></br>';
		}

		KE.util.insertHtml(id, html);
		KE.layout.hide(id);
		KE.util.focus(id);
	}
};

/*-------------------------------------------------------------------------------------------*/

KE.lang['flv'] = "插入flv视频";
KE.plugin['flv'] = {
	click : function(id) {
		KE.util.selection(id);
		var dialog = new KE.dialog({
			id : id,
			cmd : 'flv',
			file : 'fccs/flv.html',
			width : 510,
			height : 360,
			title : KE.lang['flv'],
			yesButton : KE.lang['yes'],
			noButton : KE.lang['no']
		});
		dialog.show();
	},
	check : function(id) {
		var dialogDoc = KE.util.getIframeDoc(KE.g[id].dialog);
		var url = KE.$('url', dialogDoc).value;
		var width = KE.$('width', dialogDoc).value;
		var height = KE.$('height', dialogDoc).value;
		var mode = KE.$('mode', dialogDoc).value;
		var previewUrl = KE.$('previewUrl', dialogDoc).value;
		if (url.match(/\.(flv)$/i) == null) {
			alert(KE.lang['invalidFlv']);
			window.focus();
			KE.g[id].yesButton.focus();
			return false;
		}
		if (width.match(/^\d+$/) == null) {
			alert(KE.lang['invalidWidth']);
			window.focus();
			KE.g[id].yesButton.focus();
			return false;
		}
		if (height.match(/^\d+$/) == null) {
			alert(KE.lang['invalidHeight']);
			window.focus();
			KE.g[id].yesButton.focus();
			return false;
		}
		if (previewUrl != 'http://' && previewUrl != ''
				&& previewUrl.match(/\.(jpg|jpeg|gif|bmp|png)$/i) == null) {
			alert(KE.lang['invalidImg']);
			window.focus();
			KE.g[id].yesButton.focus();
			return false;
		}
		return true;
	},
	exec : function(id) {
		KE.util.select(id);
		var iframeDoc = KE.g[id].iframeDoc;
		var dialogDoc = KE.util.getIframeDoc(KE.g[id].dialog);
		if (!this.check(id))
			return false;
		var url = KE.$('url', dialogDoc).value;
		var width = KE.$('width', dialogDoc).value;
		var height = KE.$('height', dialogDoc).value;
		var mode = KE.$('mode', dialogDoc).value;
		var previewUrl = KE.$('previewUrl', dialogDoc).value;
		this.insert(id, url, width, height, mode, previewUrl);
	},
	insert : function(id, url, width, height, mode, previewUrl) {
		var html = '';
		if (previewUrl != 'http://' && previewUrl != '') {
			html = '[flv=' + mode + ',' + width + ',' + height + ','
					+ previewUrl + ']' + url + '[/flv]';
		} else {
			html = '[flv=' + mode + ',' + width + ',' + height + ']' + url
					+ '[/flv]';
		}
		KE.util.insertHtml(id, html);
		KE.layout.hide(id);
		KE.util.focus(id);
	}
};