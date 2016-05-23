[#ftl]



<li>
    <a href="/manage/ruser/index.do">
        <i class="fa fa-home"></i>
        <span class="nav-label">个人中心</span>
        <span class="fa arrow"></span>
    </a>
     <ul class="nav nav-second-level">
        <li>
            <a class="J_menuItem" href="/manage/bug/bugInfo.do" data-index="0">个人中心</a>
        </li>
      </ul>
 </li>  
 
 		[#if list??]
        	[#list list as type]
 
				 <li>
				    <a href="/manage/ruser/index.do">
				        <i class="glyphicon glyphicon-paperclip" style="font-size:10px;"></i>
				        <span class="nav-label">${type.projectname}</span>
				        <span class="fa arrow"></span>
				    </a>
				     <ul class="nav nav-second-level">
				        [#if user?? && user.pass!=1]
				        <li>
		           			 <a class="J_menuItem" href="/manage/ruser/list.do?type=1&projectid=${type.projectid}">需求管理</a>
		        		</li>
		        		<li>
		           			 <a class="J_menuItem" href="/manage/ruser/list.do?type=3&projectid=${type.projectid}">问题反馈</a>
		        		</li>
		        		<li>
				            <a class="J_menuItem" href="/manage/ruser/list.do?type=2&projectid=${type.projectid}">报价单</a>
				        </li>
				        [/#if]
				        <li>
				            <a class="J_menuItem" href="/manage/bug/list.do?&projectid=${type.projectid}">Bug管理</a>
				        </li>
				      </ul>
				      
				 </li>  
     	[/#list]
       [/#if]

    [#if Session.userid =-1]  
<li>
    <a href="/manage/ruser/index.do">
        <i class="glyphicon glyphicon-cog" style="font-size:10px;"></i>
        <span class="nav-label">权限管理</span>
        <span class="fa arrow"></span>
    </a>
    <ul class="nav nav-second-level">
       
        <li>
            <a class="J_menuItem" href="/manage/projectment/list.do">项目管理</a>
        </li>
        <li>
            <a class="J_menuItem" href="/manage/user/list.do">用户管理</a>
        </li>
         <li>
            <a class="J_menuItem" href="/manage/price/list.do">报价管理</a>
        </li>
         <!--
        <li>
            <a class="J_menuItem" href="/views/manage/bug/buglist.ftl">bug列表</a>
        </li>
        <li>
            <a class="J_menuItem" href="/framework/hindex/table_data_tables.ftl">列表页面</a>
        </li>
        <li>
            <a class="J_menuItem" href="/framework/hindex/table_basic.ftl">基本列表页面</a>
        </li>
         <li>
            <a class="J_menuItem" href="/framework/hindex/form_basic.ftl">基本表单</a>
        </li>
        <li>
            <a class="J_menuItem" href="/views/manage/bug/bugedit.ftl">bug编辑</a>
        </li>
        -->
    </ul>

</li>
[/#if]
