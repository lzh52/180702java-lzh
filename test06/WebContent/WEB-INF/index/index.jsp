<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
* {
	margin: 0;
	padding: 0;
}

#main {
	overflow: hidden;
}

a {
	color: #fff;
}

#left {
	width: 400px;
	float: left;
	height: 600px;
	padding-left: 20px;
}

#right {
	width: 800px;
	float: left;
	height: 600px;
}

#top, #bottom {
	clear: both;
	height: 80px;
	background: #337ab7;
	height: 80px;
}

.yi {
	width: 160px;
	height: 40px;
	background: #337ab7;
	color: #fff;
	margin-top: 10px;
	border-radius: 5px;
	text-align: center;
	line-height: 40px;
}

.er {
	list-style: none;
	width: 160px;
}

.er li {
	margin-top: 5px;
	text-align: center;
	background: #337a97;
	color: #fff;
	height: 30px;
	line-height: 30px;
	font-size: 14px;
}

.er a {
	color: #000;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$(".yi").click(function() {
			
			$(this).next().slideToggle(500);
		})
	})
</script>
</head>
<body>
	<div id="container">
		<div id="top"></div>
		<div id="main">
			<div id="left">
				<div class="yi">员工管理</div>
				<ul class="er">
					<li><a href="employee" target="right">员工管理</a></li>
					<li><a href="employee?type=showAdd" target="right">员工添加</a></li>
				</ul>
				<div class="yi">部门管理</div>
				<ul class="er">
					<li><a href="department" target="right">部门管理</a></li>
					<li><a href="department?type=showAdd" target="right">部门添加</a></li>
				</ul>
				<div class="yi">项目管理</div>
				<ul class="er">
					<li><a href="project" target="right">项目管理</a></li>
					<li><a href="project?type=showAdd" target="right">项目添加</a></li>
				</ul>
				<div class="yi">绩效管理</div>
				<ul class="er">
					<li><a href="score" target="right">绩效查看</a></li>
					<li><a href="score?type=manage" target="right">绩效管理</a></li>
				</ul>


			</div>
			<iframe id="right" name="right" scrolling="no" frameborder="0"
				src="employee"></iframe>
		</div>
		<div id="bottom"></div>
	</div>

</body>
</html>