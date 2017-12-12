<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
  <meta charset="UTF-8">
  <title>用户登录</title>
   <link rel="stylesheet" href="./css/account.css">
 <%@include file="common/head.jsp" %>
<textarea class="form-control" id="content" name="content" placeholder="文章的内容" rows="18"></textarea>
<script type="text/javascript">
  $('#content').wysihtml5();
</script>

</head>

<body>
<jsp:include page="common/nav.jsp"></jsp:include>

<form class="form-signin" action="./login" method="post">
  <div class="alert alert-warning">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    <strong>Warning!</strong> <%=request.getAttribute("message") %>
    <%request.setAttribute("next",request.getParameter("next"));%>
  </div>
  <h2 class="form-signin-heading">用户登录</h2>
  <input type="text" name="username" class="form-control" placeholder="用户名" required autofocus>
  <input type="password" name="password" class="form-control" placeholder="密码" required>
  <div class="checkbox">
    <label>
      <input type="checkbox" name="remember-me" value="on"> 记住我
    </label>
  </div>
  <button class="btn btn-primary btn-block" type="submit">确定</button>
</form>

</body>

</html>