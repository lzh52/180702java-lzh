
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
							location.href = "employee?type=showAdd"
						})
						$("#showAdd2").click(function() {
							location.href = "employee?type=showAdd2"
						})
						$("#showUpdate")
								.click(
										function() {
											if (selectId > -1) {
												location.href = "employee?type=showUpdate&id="
														+ selectId
											} else {
												alert("请选中一条数据");
											}
										})
						$("#delete")
								.click(
										function() {
											if (selectId > -1) {
												location.href = "employee?type=delete&id="
														+ selectId
											} else {
												alert("请选中一条数据");
											}
										})

						function doBatch(type) {
							var length = $("#emp .select").length;
							var ids = new Array();
							if (length > 0) {
								$("#emp .select").each(
										function(index, element) {
											ids.push($(this).data("id"));
										})
								//alert(ids);
								location.href = "employee?type=" + type
										+ "&ids=" + ids;
							} else {
								alert("请选中一条数据");
							}
						}

						$("#deleteBatch").click(function() {
							doBatch("deleteBatch");
						})
						$("#showUpdateBatch1").click(function() {
							doBatch("showUpdateBatch1");
						})
						$("#showUpdateBatch2").click(function() {
							doBatch("showUpdateBatch2");
						})
						$("#updateBatch3")
								.click(
										function() {
											//var emps = "";
											var array = new Array();
											$(".updateEmp")
													.each(
															function(index,
																	element) {
																//查找此节点的name叫id的值
																var id = $(this)
																		.data(
																				"id");
																var name = $(
																		this)
																		.find(
																				"[name=name]")
																		.val();
																var sex = $(
																		this)
																		.find(
																				"[name=sex]")
																		.val();
																var age = $(
																		this)
																		.find(
																				"[name=age]")
																		.val();
																//emps += id + "," + name + "," + sex + "," + age + ";";
																var emp = {
																	id : id,
																	name : name,
																	sex : sex,
																	age : age
																}
																array.push(emp);
															})

											//emps = emps.substring(0, emps.length - 1);
											//转义字符，url不允许大括号,g表示全局
											var str = JSON.stringify(array);
											str = str.replace(/{/g, "%7b");
											str = str.replace(/}/g, "%7d");

											window.location.href = "employee?type=updateBatch3&emps="
													+ str;
										})
						$("tr").click(function() {
							$(this).toggleClass("select");
							selectId = $(this).data("id");
						})
						$("tr")
								.dblclick(
										function() {
											//取消两次双击事件
											$(this).unbind("dblclick");
											$(this).unbind("click");
											$(this).addClass("updateEmp")
											var id = $(this).children().eq(0)
													.text();
											$(this)
													.children()
													.eq(0)
													.html(
															"<input type='text' name = 'id' value='" + id + "'/>");
											alert(id);
											var name = $(this).children().eq(1)
													.text();
											$(this)
													.children()
													.eq(1)
													.html(
															"<input type='text' name = 'name' value='" + name + "'/>");

											var sex = $(this).children().eq(2)
													.text();
											var select = "";
											if (sex == "男") {
												select = "<select name = 'sex'><option selected value='男'>男</option><option value='女'>女</option></select>"
											} else {
												select = "<select name = 'sex'><option value='男'>男</option><option selected value='女'>女</option></select>"
											}
											$(this).children().eq(2).html(
													select);

											var age = $(this).children().eq(3)
													.text();
											$(this)
													.children()
													.eq(3)
													.html(
															"<input type='text' name = 'age' value='" + age + "'/>");
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
						
						$("#emp img").hover(function(event){
							var photo = $(this).attr("src");
							$("#bigPhoto img").attr("src",photo);
							$("#bigPhoto").show();
							$("#bigPhoto").css({left:event.pageX+20,top:event.pageY+20})
						},function(){
							$("#bigPhoto").hide();
						})
						
						
					})
</script>
<style type="text/css">
#main {
	width: 800px;
	margin: 20px auto;
}

#emp .select {
	background: #337ab7
}

#emp td {
	width: 200px;
}

#emp input {
	width: 100px;
}

#emp select {
	width: 100px;
	height: 28px;
}
#emp img{
width:25px;
height:25px;
}
#bigPhoto{
width:100px;

display:none;
position:absolute;

}
#bigPhoto img{
width:100px;
height:100px;
}
</style>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
</head>
<body>



	<div id="main">
	<form action="employee" class="form-horizontal" role="form" method="post">
			<div class="form-group">
			<!-- col-sm-xx 最大12 -->
				<div class="col-sm-3">
					<input type="text" class="form-control" name="name"
						placeholder="请输入姓名" value=${c.name }>
				</div>
				<div class="col-sm-2">
					<select name="sex" class="form-control">
					<option value="">性别</option>
					<option value="男" <c:if test="${c.sex == '男' }"> selected</c:if>>男</option>
					<option value="女" <c:if test="${c.sex == '女' }"> selected</c:if>>女</option>
					</select>
				</div>
				<div class="col-sm-2">
					<input type="text" class="form-control" name="age"
						placeholder="年龄"value=${c.age!=-1?c.age:'' }>
				</div>
				<div class="col-sm-3">
					<select name="depId" class="form-control">
					<option value="">部门</option>
					
					<c:forEach items="${deps}" var="dep">
					
					<option value="${dep.id }" <c:if test="${dep.id == c.dep.id }"> selected</c:if>>${dep.name }</option>
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

		<table id="emp" class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>性别</th>
					<th>年龄</th>
					<th>部门</th>
					<th>照片</th>

				</tr>
			</thead>

			<tbody>
				<c:forEach items="${emps}" var="emp">

					<tr data-id="${emp.id }">
						<td>${emp.id }</td>
						<td>${emp.name }</td>
						<td>${emp.sex }</td>
						<td>${emp.age }</td>
						<td>${emp.dep.name }</td>
						<td><c:if test="${not empty emp.photo }"><img src="pic/${emp.photo }"/></c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<ul class="pagination">
				<!-- class="disabled"不影响功能只修饰  <if(ye<=1){ %>onclick="return false"<} >-->
				<li><a
					href="employee?ye=1&name=${c.name }&sex=${c.sex }&age=${c.age!=-1?c.age:''}&depId=${c.dep.id}">首页</a></li>

				<li id="pre"><a href="employee?ye=${p.ye-1 }&name=${c.name }&sex=${c.sex }&age=${c.age!=-1?c.age:''}&depId=${c.dep.id}">上一页</a></li>
				<c:forEach begin="${p.beginYe }" end="${p.endYe }"
					varStatus="status">
					<li <c:if test="${p.ye==status.index }"> class="active"</c:if>><a
						href="employee?ye=${status.index }&name=${c.name }&sex=${c.sex }&age=${c.age!=-1?c.age:''}&depId=${c.dep.id}">${status.index }</a></li>
				</c:forEach>
				<li id="next"><a href="employee?ye=${p.ye+1 }&name=${c.name }&sex=${c.sex }&age=${c.age!=-1?c.age:''}&depId=${c.dep.id}">下一页</a></li>
				<li><a href="employee?ye=${p.maxYe }&name=${c.name }&sex=${c.sex }&age=${c.age!=-1?c.age:''}&depId=${c.dep.id}">尾页</a></li>
			</ul>
		</div>

		<button id="showAdd" type="button" class="btn btn-primary">添加</button>
		<button id="showAdd2" type="button" class="btn btn-primary">添加2</button>
		<button id="showUpdate" type="button" class="btn btn-primary">修改</button>
		<button id="showUpdateBatch1" type="button" class="btn btn-primary">批量修改1</button>
		<button id="showUpdateBatch2" type="button" class="btn btn-primary">批量修改2</button>
		<button id="updateBatch3" type="button" class="btn btn-primary">批量修改3</button>

		<button id="delete" type="button" class="btn btn-primary">删除</button>
		<button id="deleteBatch" type="button" class="btn btn-primary">批量删除</button>

	</div>
<div id="bigPhoto"><img src=""/></div>




</body>
</html>