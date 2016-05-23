[#ftl]

<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="nav-close"><i class="fa fa-times-circle"></i>
    </div>
    <div class="sidebar-collapse">
        <ul class="nav" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element">
                    <span><img alt="image" class="img-circle" style="width:64px;height:64px;" src="[#if user?? && user.photo??]${user.photo}[#else]/framework/hplus/img/profile_small.jpg[/#if]" /></span>
                    <a data-toggle="dropdown" class="dropdown-toggle" href="/framework/hplus/#">
                        <span class="clear">
                       <span class="block m-t-xs"><strong class="font-bold">[#if user??]${user.username}[/#if]</strong></span>
                        <span class="text-muted text-xs block">普通账号<b class="caret"></b></span>
                        </span>
                    </a>
                    <ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li><a class="J_menuItem" href="/framework/hplus/form_avatar.html">修改头像</a>
                        </li>
                        <li><a class="J_menuItem" href="/framework/hplus/profile.html">个人资料</a>
                        </li>
                        <li><a class="J_menuItem" href="/framework/hplus/contacts.html">联系我们</a>
                        </li>
                        <li><a class="J_menuItem" href="/framework/hplus/mailbox.html">信箱</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="/manage/logout.do">安全退出</a>
                        </li>
                    </ul>
                </div>
                <div class="logo-element">H+
                </div>
            </li>
            <!-- 侧边栏管理-->
           [#include "/framework/hindex/menu.ftl"/]

        </ul>
    </div>
</nav>