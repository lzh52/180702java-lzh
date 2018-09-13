
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.List,entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>批量修改员工信息</title>
<script src="js/jquery.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#save").click(function() {
			var emps = "";
			$(".emp").each(function(index, element) {
				//查找此节点的name叫id的值
				var id = $(this).find("[name=id]").val();
				var name = $(this).find("[name=name]").val();
				//sex不是文本框，查找有两个值，筛选选中的（：筛选选择器）
				var sex = $(this).find("[name=sex]:checked").val();
				var age = $(this).find("[name=age]").val();
				emps += id + "," + name + "," + sex + "," + age + ";";
			})
			emps = emps.substring(0, emps.length - 1);
			window.location.href="employee?type=updateBatch2&emps=" + emps;
		})
	})
</script>
<style type="text/css">
#main {
	width: 100%;
	margin: 20px auto;
	padding-left: 160px;
}

.emp {
	width: 400px;
	float: left;
	margin: 20px auto;
	border: 1px dashed #ccc;
	padding: 20px 20px 10px 0;
}

#saveBtn {
	clear: both;
	/* 行级元素居中 */
	/* text-align: center; */
	margin-left: 775px
}
</style>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
</head>
<body>
	<%
		List<Employee> list = (List<Employee>) request.getAttribute("list");
	%>
	<div id="main">
		<%
			//逐元循环
			for (Employee emp : list) {
		%>
		<form action="employee" class="form-horizontal emp" method="post">
			<input type="hidden" name="type" value="updateBatch1" /> <input
				type="hidden" name="id" value="<%=emp.getId()%>" />

			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name"
						value="<%=emp.getName()%>">
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">姓别</label>
				<div class="col-sm-10">
					<input type="radio" <%if (emp.getSex().equals("男")) {%> checked
						<%}%> value="男" name="sex">男 <input type="radio"
						<%if (emp.getSex().equals("女")) {%> checked <%}%> value="女"
						name="sex">女
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">年龄</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="age"
						value="<%=emp.getAge()%>">
				</div>
			</div>


		</form>
		<%
			}
		%>
		<div id="saveBtn">
			<button id="save" type="button" class="btn btn-primary">保存</button>
		</div>


	</div>

</body>
</html>