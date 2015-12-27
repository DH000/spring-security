<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<aside>
	<div id="sidebar" class="nav-collapse ">
		<!-- sidebar menu start-->
		<ul class="sidebar-menu" id="nav-accordion">
			<li><a <c:if test="${empty index}">class="active"</c:if> href="/"> <i class="icon-dashboard"></i> <span>首页</span>
			</a></li>
			<!-- 菜单栏 -->
			<c:forEach items="${sessionScope.menus}" var="menu" varStatus="s">
				<li class="sub-menu"><a href="javascript:;"> <i class="icon-laptop"></i> <span>${menu.name}</span></a>
					<ul class="sub">
						<c:forEach items="${menu.subs}" var="sub" varStatus="ss">
							<li <c:if test="${sub.id == index}">class="active"</c:if>><a href="${sub.url}?index=${sub.id}">${sub.name}</a></li>
						</c:forEach>
					</ul></li>
			</c:forEach>
		</ul>
		<!-- sidebar menu end-->
		<script type="text/javascript">
			$(function(){
				$("#nav-accordion .sub-menu li.active").closest("li.sub-menu").find("a").trigger("click");
			});
		</script>
	</div>
</aside>