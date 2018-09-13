
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>

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
						$("#add").click(function() {
							var proId = $("#selectPro").val();
							location.href = "dep2pro?type=add&depId=${dep.id}&proId=" + proId;
						})
						//java代码先执行 js判断
					/* 	<c:if test="${f:length(noPros)==0}">
						$("#add").unbind("click");
						$("#add").addClass("disabled");
						</c:if> */
						
						
						//jQUERY判断
						if($("#selectPro").children().length==0){
							$("#add").unbind("click");
							$("#add").addClass("disabled");
						}
						
						$("#delete")
								.click(
										function() {
											if (selectId > -1) {
												location.href = "dep2pro?type=delete&depId=${dep.id}&proId="
														+ selectId
											} else {
												alert("请选中一条数据");
											}
										})

					
						$("tr").click(function() {
							$(this).toggleClass("select");
							selectId = $(this).data("id");
						})
						
						
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
		<h1>${dep.name}</h1>

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
		<div class="col-sm-4">
		<select class="form-control" id="selectPro">
		<c:forEach items="${noPros}" var="pro">
		<option value="${pro.id }">${pro.name }</option>
		</c:forEach>
		</select></div>
		
		
		
		<button id="add" type="button" class="btn btn-primary">添加</button>
		<button id="delete" type="button" class="btn btn-primary">删除</button>
		</div>
	</div>





</body>
</html>