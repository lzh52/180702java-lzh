
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$()
			.ready(
					function() {
						//id在数据库中1开始计数
						var selectId = -1;
						$("#showAdd").click(function() {
							location.href = "project?type=showAdd"
						})
						$("#showUpdate")
								.click(
										function() {
											if (selectId > -1) {
												location.href = "project?type=showUpdate&id="
														+ selectId
											} else {
												alert("请选中一条数据");
											}
										})
						$("#delete")
								.click(
										function() {
											if (selectId > -1) {
												location.href = "project?type=delete&id="
														+ selectId
											} else {
												alert("请选中一条数据");
											}
										})

					
						$("tr").click(function() {
							$(this).toggleClass("select");
							selectId = $(this).data("id");
						})
						
										//jsp先执行
										if(${p.ye} <=1){
											$("#pre").addClass("disabled");
										$("#pre").find("a").attr("onclick","return false");
										}
						if(${p.ye }>=${p.maxYe }){
							$("#next").addClass("disabled");
						$("#next").find("a").attr("onclick","return false");
						}
					})
</script>
<style type="text/css">
#main {
	width: 800px;
	margin: 20px auto;
}

#pro .select {
	background: #337ab7
}

#pro td {
	width: 200px;
}

#pro input {
	width: 100px;
}

#pro select {
	width: 100px;
	height: 28px;
}
</style>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
</head>
<body>



	<div id="main">
		<form action="project" class="form-horizontal" role="form"
			method="post">
			<div class="form-group">
				<!-- col-sm-xx 最大12 -->
				<div class="col-sm-4">
					<input type="text" class="form-control" name="name"
						placeholder="请输入部门名" value=${c.name }>
				</div>


				<div class="form-group">
					<div class=" col-sm-4">
						<button type="submit" class="btn btn-primary">搜索</button>
					</div>
				</div>
			</div>




		</form>

		<table id="pro" class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>项目名称</th>

				</tr>
			</thead>

			<tbody>
				<c:forEach items="${pros}" var="pro">

					<tr data-id="${pro.id }">
						<td>${pro.id }</td>
						<td>${pro.name }</td>


					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<ul class="pagination">
				<!-- class="disabled"不影响功能只修饰  <if(ye<=1){ %>onclick="return false"<} >-->
				<li><a
					href="project?ye=1&name=${c.name }">首页</a></li>

				<li id="pre"><a href="project?ye=${p.ye-1 }&name=${c.name }">上一页</a></li>
				<c:forEach begin="${p.beginYe }" end="${p.endYe }"
					varStatus="status">
					<li <c:if test="${p.ye==status.index }"> class="active"</c:if>><a
						href="project?ye=${status.index }&name=${c.name }">${status.index }</a></li>
				</c:forEach>
				<li id="next"><a href="project?ye=${p.ye+1 }&name=${c.name }">下一页</a></li>
				<li><a
					href="project?ye=${p.maxYe }&name=${c.name }">尾页</a></li>

			</ul>
		</div>

		<button id="showAdd" type="button" class="btn btn-primary">添加</button>
		<button id="showUpdate" type="button" class="btn btn-primary">修改</button>
		<button id="delete" type="button" class="btn btn-primary">删除</button>

	</div>





</body>
</html>