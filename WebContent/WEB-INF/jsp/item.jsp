<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>${blog.title }</title>
<%@include file="common/head.jsp"%>
<link rel="stylesheet" href="./css/footer.css">
<link rel="stylesheet" href="./css/sidbar.css">
<link rel="stylesheet" href="./css/blog.css">
<link rel="stylesheet" href="./css/highlight.css">
</head>
<body>

	<jsp:include page="common/nav.jsp">
		<jsp:param name="username" value="${blog.author.username }" />
	</jsp:include>

	<div class="container">
		<div class="row">
			<div class="col-sm-8 blog-item">
				<div class="page-header">
					<h2>${blog.title }</h2>
					<p class="blog-post-meta">
						<fmt:formatDate pattern="yyyy-MM-dd" value="${blog.createdTime }" />
						标签：<a href="#">Web开发</a>
					</p>
				</div>
				<div class="post-content">

					<p>${blog.content }</p>
				</div>
				<nav>
					<ul class="pager">
						<li class="previous"><a href="#"><span aria-hidden="true">&larr;</span>
								上一篇</a></li>
						<li class="next"><a href="#">下一篇 <span aria-hidden="true">&rarr;</span></a></li>
					</ul>
				</nav>
				<section class="comment-list">
					<h4 class="page-header">文章评论</h4>
					<div class="create-comment clearfix">
						<textarea id="content" name="content" rows="3"
							class="form-control" placeholder="添加新评论"></textarea>
						<a class="btn btn-success btn-sm btn-publish pull-right" href="#">
							发布评论</a>
					</div>
					<ul class="list-unstyled">
						<li class="clearfix"><img src="./img/catty.jpeg" alt=""
							class="avatar pull-left">
							<p class="content">
								<a href="#">Harttle,</a> <span class="text-muted date"> 2
									mins ago</span> Risus. Suspendisse faucibus orci et nunc. Nullam
								vehicula fermentum risus. Fusce felis nibh, dignissim vulputate,
								ultrices.
							</p></li>
						<li class="clearfix"><img src="./img/catty.jpeg" alt=""
							class="avatar pull-left">
							<p class="content">
								<a href="#">David</a> <span class="text-muted date">,
									yesterday</span> Risus. Suspendisse faucibus orci et nunc. Nullam
								vehicula fermentum risus. Fusce felis nibh, dignissim vulputate,
								ultrices.
							</p></li>
					</ul>
				</section>
			</div>
			<div class="col-sm-3 col-sm-offset-1">
				<c:set var="author" value="${currentAuthor}" scope="request" />
				<jsp:include page="common/userInfo.jsp">
					<jsp:param name="author" value="${author}" />
				</jsp:include>
				<%@include file="common/archivedList.jsp"%>
			</div>

		</div>
	</div>
	<%@include file="common/footer.jsp"%>
</body>
</html>