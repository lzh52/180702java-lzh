
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
						$("#sc input").blur(function(){
							var value = $(this).val();
							var empId = $(this).data("empid");
							var proId = $(this).data("proid");
							var id = $(this).data("id");
							var input = $(this);
							$.ajax({
							    url:"score",
								type:"post",
								data:{
									type:"input",
									id:id,
									value:value,
									empId:empId,
									proId:proId,
							
										},
										dataType:"json",
										success:function(data){
											input.parent().next().html(data.grade.value)
											input.data("id",data.id)
										}
										
							})
						})
					})
</script>
<style type="text/css">
#main {
	width: 800px;
	margin: 20px auto;
}

#sc .select {
	background: #337ab7
}

#sc td {
	width: 200px;
}

#sc input {
	width: 100px;
}

#sc select {
	width: 100px;
	height: 28px;
}
</style>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
</head>
<body>



	<div id="main">
		<form action="score" class="form-horizontal" role="form" method="post">
			<div class="form-group">
				<!-- col-sm-xx 最大12 -->
				<div class="col-sm-2">
					<input type="text" class="form-control" name="name"
						placeholder="请输入姓名" value=${c.emp.name }>
				</div>


				<div class="col-sm-2">
					<select name="depId" class="form-control">
						<option value="">部门</option>

						<c:forEach items="${deps}" var="dep">

							<option value="${dep.id }"
								<c:if test="${dep.id == c.emp.dep.id }"> selected</c:if>>${dep.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-3">
					<select name="proId" class="form-control">
						<option value="">项目</option>

						<c:forEach items="${pros}" var="pro">

							<option value="${pro.id }"
								<c:if test="${pro.id == c.pro.id }"> selected</c:if>>${pro.name }</option>
						</c:forEach>
					</select>
				</div>

				<div class="col-sm-2">
					<input type="text" class="form-control" name="name"
						placeholder="成绩" value=${c.value }>
				</div>

				<div class="col-sm-2">
					<select name="grade" class="form-control">
						<option value="">等级</option>

						<c:forEach items="${grades}" var="grade">

							<option value="${grade }"
								<c:if test="${grade == c.grade }"> selected</c:if>>${grade.value }</option>
						</c:forEach>
					</select>
				</div>


				<div class="form-group">
					<div class=" col-sm-2">
						<button type="submit" class="btn btn-primary">搜索</button>
					</div>
				</div>
			</div>




		</form>

		<table id="sc" class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>部门</th>
					<th style="width: 220px;">项目</th>
					<th>成绩</th>
					<th>等级</th>

				</tr>
			</thead>

			<tbody>
				<c:forEach items="${scs}" var="sc">

					<tr data-id="${sc.id }">
						<td>${sc.emp.id }</td>
						<td>${sc.emp.name }</td>
						<td>${sc.emp.dep.name }</td>
						<td>${sc.pro.name }</td>
						<td><input data-id="${sc.id }" data-empid="${sc.emp.id }" data-proid="${sc.pro.id }" type="text" value="${sc.value }" /></td>
						<td>${sc.grade.value }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<ul class="pagination">
				<!-- class="disabled"不影响功能只修饰  <if(ye<=1){ %>onclick="return false"<} >-->
				<li><a
					href="score?type=manage&ye=1&e_name=${c.emp.name }&depId=${c.emp.dep.id}&proId=${c.pro.id}">首页</a></li>

				<li id="pre"><a
					href="score?type=manage&ye=${p.ye-1 }&name=${c.emp.name }&depId=${c.emp.dep.id}&proId=${c.pro.id}">上一页</a></li>
				<c:forEach begin="${p.beginYe }" end="${p.endYe }"
					varStatus="status">
					<li <c:if test="${p.ye==status.index }"> class="active"</c:if>><a
						href="score?type=manage&ye=${status.index }&e_name=${c.emp.name }&depId=${c.emp.dep.id}&proId=${c.pro.id}">${status.index }</a></li>
				</c:forEach>
				<li id="next"><a
					href="score?type=manage&ye=${p.ye+1 }&name=${c.emp.name }&depId=${c.emp.dep.id}&proId=${c.pro.id}">下一页</a></li>
				<li><a
					href="score?type=manage&ye=${p.maxYe }&e_name=${c.emp.name }&depId=${c.emp.dep.id}&proId=${c.pro.id}">尾页</a></li>
			</ul>
		</div>


		<button id="save" type="button" class="btn btn-primary">保存</button>


	</div>





</body>
</html>