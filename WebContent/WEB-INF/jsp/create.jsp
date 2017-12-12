<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head lang="en">
<meta charset="UTF-8">
<title>创建博文</title>
<%@include file="common/head.jsp"%>
<link rel="stylesheet" href="./css/footer.css">
<link rel="stylesheet" href="./css/sidbar.css">
<link rel="stylesheet" href="./css/blog.css">
<link rel="stylesheet" href="./css/highlight.css">
<link rel="stylesheet"
	href="./lib/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<script src="./lib/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
</head>

<body>

	<jsp:include page="common/nav.jsp">
		<jsp:param name="username"
			value="${sessionScope.currentUser.username}" />
	</jsp:include>

	<div class="container">
		<div class="page-header">
			<h2>
				创建博文 <small>在这里新建一篇博客</small>
			</h2>
		</div>
		<div class="row">
			<div class="col-sm-8">
				<form class="form-create-blog" action="./create" method="post">
					<div class="alert alert-warning">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						<strong>Warning!</strong>
						<%=request.getAttribute("message")%>
					</div>
					<div class="form-group">
						<label for="title">标题</label> <input type="text"
							class="form-control" name="title" placeholder="文章的标题" autofocus>
					</div>
					<div class="form-group">
						<label for="content">内容</label>
						<textarea class="form-control" name="content" placeholder="文章的内容"
							rows="18"></textarea>
					</div>
					<button type="submit" class="btn pull-right btn-primary">保存</button>
				</form>
			</div>

			<div class="col-sm-3 col-sm-offset-1">
				<c:set var = "author" value = "${currentUser}" scope = "session"/>
				<jsp:include page="common/userInfo.jsp" >
					<jsp:param name="author" value="${currentUser}"/>
				</jsp:include>
				<%@include file="common/archivedList.jsp"%>
			</div>
		</div>
	</div>

	<%@include file="common/footer.jsp"%>

</body>
</html>