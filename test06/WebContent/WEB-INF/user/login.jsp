<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.sql.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="js/jquery.js"></script>
<script type="text/javascript">
	$().ready(function() {

		$("#image").click(function() {
			$(this).attr("src", "user?type=randomImage&" + Math.random())
		})
	})
</script>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<script type="text/javascript"
	src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
</head>
<body>
	<div style="width: 600px; margin: 20px auto; margin-top: 120px;">
		<form id="form" action="user?type=doLogin" method="post"
			class="form-horizontal" role="form">
			<div class="form-group">
				<lable class="col-xs-2 control-lable">账号:</lable>
				<div class="col-xs-6">
					<input type="text" name="username" class="form-horizontal"
						value="${name }" />
				</div>
			</div>
			<div class="form-group">
				<lable class="col-xs-2 control-lable">密码:</lable>
				<div class="col-xs-6">
					<input type="password" name="password" class="form-horizontal" />
				</div>
			</div>
			<div class="form-group">
				<lable class="col-xs-2 control-lable">验证:</lable>
				<div class="col-xs-6">
					<input type="text" name="random" class="form-horizontal" />
				</div>
				<div class="col-sx-4">
					<img id="image" src="user?type=randomImage" />
				</div>
			</div>
			<div id="mes" style="height: 40px;">${mes }</div>
			<div class="form-group" style="width: 200px; margin-left:100px;">
				<input id="register" type="button" class="btn btn-primary" value="注册">
				<input id="login" type="submit" class="btn btn-primary" value="登录">
			</div>
	</div>
	</div>
	</form>
	</div>
</body>
</html>