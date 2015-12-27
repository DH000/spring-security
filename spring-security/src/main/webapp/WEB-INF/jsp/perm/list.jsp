<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="Mosaddek">
<meta name="keyword" content="FlatLab, Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
<link rel="shortcut icon" href="img/favicon.png">

<title>用户管理系统-权限管理</title>
<jsp:include page="../common.jsp" />
</head>
<body>
	<section id="container">
		<!--header start-->
		<jsp:include page="../header.jsp" flush="true" />
		<!--header end-->

		<!--sidebar start-->
		<jsp:include page="../menus.jsp" flush="true">
			<jsp:param value="index" name="4" />
		</jsp:include>
		<!--sidebar end-->

		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
				<div class="row">
					<div class="col-lg-12">
						<section class="panel">
							<header class="panel-heading">权限列表</header>
							<table class="table table-striped table-advance table-hover">
								<thead>
									<tr>
										<th>多选</th>
										<th>类型</th>
										<th>名称</th>
										<th>别名</th>
										<th>链接</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${pageInfo.list}" var="item">
										<tr>
											<td><input type="checkbox"></td>
											<td>${item.type.name}</td>
											<td>${item.name}</td>
											<td>${item.alias}</td>
											<td><span class="label label-info label-mini">${item.url}</span></td>
											<td>
												<button class="btn btn-success btn-xs">
													<i class="fa icon-check"></i>
												</button> 
												<sec:authorize access="hasAuthority('perm:edit')">
													<button class="btn btn-primary btn-xs">
														<i class="fa icon-pencil"></i>
													</button>
												</sec:authorize>
												<sec:authorize access="hasAuthority('perm:del')">
													<button class="btn btn-danger btn-xs">
														<i class="fa icon-trash "></i>
													</button>
												</sec:authorize>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="panel-body">
								<tags:pager url="#{pageNumber}" currPage="${pageInfo.currPage}" pageSize="${pageInfo.pageSize}" total="${pageInfo.total}" />
							</div>
						</section>
					</div>
				</div>
			</section>
		</section>
		<!--main content end-->
		<!--footer start-->
		<!--footer end-->
	</section>
</body>
</html>
