function ws_add(title, href, menuId) {
	if($('#wsf' + menuId).length > 0) {
		$('#workspace').tabs('select', title);
		return;	
	}
	
	var tabs = $(".tabs li").size();
	if(tabs >= 15) {
		alert("已经存在15个页面选项卡，请先关闭不需要使用的页面");
		return;
	}
	
	$('#workspace').tabs('add',{
	    title: title,
	    closable: true,
		content: ws_get_content(href, menuId),
		id: menuId
	});
	ws_refresh();
}

function ws_get_content(href, menuId) {
	var html = 	'<iframe id="wsf' + menuId + '" name="wsf' + menuId + '" src="' + href + '" width="100%" height="100%" frameborder="0"></iframe>';
	return html;
}

function ws_reload() {
	var tab = $('#workspace').tabs('getSelected');
	var menuId = tab.panel('options').id;
	
	var src = $('#wsf' + menuId).attr("src");
	var html = ws_get_content(src, menuId);
	
	$("#workspace").tabs('update',{  
		tab:tab,
		options:{
			closable: true,
			content:html,
			fit:true,
			selected:true
		}
    });
	ws_refresh();
}

//欢迎页面
function ws_welcome() {
	ws_add('欢迎', '/manage/manage.do?method=toWelcome', 0);
}

var contextmenutitle = '';
function ws_refresh() {
	$(".tabs-inner").dblclick(function(){
	    var subtitle = $(this).children("span").text();
	    $("#workspace").tabs("close",subtitle);
	});
	$(".tabs-inner").bind('contextmenu',function(e){
		var posx = 0;
		var posy = 0;
		if(e.pageX || e.pageY) {
	        posx = e.pageX;
	        posy = e.pageY;
		} else if(e.clientX || e.clientY) {
	        posx = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
		    posy = e.clientY + document.body.scrollTop + document.documentElement.scrollTop;
		}
		
		
        $('#mm').menu('show', {
        	left:posx,
        	top:posy
        });
       
        var subtitle =$(this).children("span").text();
        $('#mm').data("currtab",subtitle);
       
        return false;
    });
    $('#mm-tabclose').click(function(){
        var currtab_title = $('#mm').data("currtab");
        $('#workspace').tabs('close',currtab_title);
    });
    $('#mm-tabcloseall').click(function(){
        $('.tabs-inner span').each(function(i,n){
            var t = $(n).text();
            $('#workspace').tabs('close',t);
        });   
    });
    $('#mm-tabcloseother').click(function(){
        var currtab_title = $('#mm').data("currtab");
        $('.tabs-inner span').each(function(i,n){
            var t = $(n).text();
            if(t!=currtab_title)
                $('#workspace').tabs('close',t);
        });   
    });
    $('#mm-tabcloseright').click(function(){
    	if(contextmenutitle != '') {
    		$('#workspace').tabs('select', contextmenutitle);
    	}
        var nextall = $('.tabs-selected').nextAll();
        if(nextall.length==0){
            return false;
        }
        nextall.each(function(i,n){
            var t=$('a:eq(0) span',$(n)).text();
            $('#workspace').tabs('close',t);
        });
        return false;
    });
    $('#mm-tabcloseleft').click(function(){
    	if(contextmenutitle != '') {
    		$('#workspace').tabs('select', contextmenutitle);
    	}
        var prevall = $('.tabs-selected').prevAll();
        if(prevall.length==0){
            return false;
        }
        prevall.each(function(i,n){
            var t=$('a:eq(0) span',$(n)).text();
            $('#workspace').tabs('close',t);
        });
        return false;
    });
}



function menuClick() {
	var menuId = $(this).attr("menuId");
	if($(this).attr("title") != "") {
		ws_add($(this).attr("title"), $(this).attr('rel'), menuId);
	}
}

$(function(){
	//设置窗口高度
	$(window).resize(function() {
		$("body").height($(window).height());
	});
	$(window).trigger('resize');
	
	$("#workspace").tabs({
		closable: true,
		fit:true,
		border:false
	});
	
	$('.menu_item').click(menuClick);

	$('#btnExit').click(function(){
		if(confirm('您确定要退出吗？') == true){
			document.location.href = '/manage/manage.do?method=doLogout';
		}
	});
	
	$('#btnPassword').click(function(){
		ws_add('修改密码', '/manage/manage.do?method=toModifyManagePwd');
	});
	
	$('#btnHome').click(ws_welcome);
	
	$('#btnReload').click(ws_reload);
	
	randerStyle();
});


function randerStyle() {
	$(".menu_item").attr("style", "padding-bottom: 3px;line-height:20px;color: #34495E;");
}