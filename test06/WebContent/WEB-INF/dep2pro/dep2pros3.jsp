
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>


<style type="text/css">
#main {
	width: 800px;
	margin: 20px auto;
}

#pro, #noPro {
	width: 700px;
	height: 300px;
	border: 1px solid #337ab7;
	border-radius: 5px;
}
#btn{
width:120px;
margin:20px auto;
}
#add{
margin-right:50px;
}
.pro{
background: #337ab7;
height:40px;
line-height:40px;
float:left;
margin-left:5px;
color: #fff;
padding: 0 20px;
margin-top:10px;
border-radius:5px;
}
.select{
background: #d9534f;
}
</style>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$(".pro").click(function(){
			$(this).toggleClass("select")
		})
		$("#add").click(function(){
			if($("#noPro").find(".select").length>0){
				var proId=$("#noPro").find(".select").data("id");
				$.ajax({
					url:"dep2pro",
					type:"post",
					data:{type:"add2",depId:${dep.id},proId:proId},
					dataType:"text",
					success:function(data){
						if(data=="true"){
							var pro = $("#noPro").find(".select");
							pro.removeClass("select") 
							$("#pro").append(pro)
						}
					}
				})
			}else{
				alert("请选择一个项目");
			}
		})
		$("#delete").click(function(){
			if($("#pro").find(".select").length>0){
				var proId=$("#pro").find(".select").data("id");
				$.ajax({
					url:"dep2pro",
					type:"post",
					data:{type:"delete2",depId:${dep.id},proId:proId},
					dataType:"text",
					success:function(data){
						if(data=="true"){
							var pro = $("#pro").find(".select");
							pro.removeClass("select") 
							$("#noPro").append(pro)
						}
					}
				})
			}else{
				alert("请选择一个项目");
			}
		})
		
	})
</script>
</head>
<body>



	<div id="main">
		<h1>${dep.name}</h1>
		<div id="pro">
		<c:forEach items="${pros }" var="pro">
		<div class="pro" data-id="${pro.id }">${pro.name }</div>
		
		
		
		</c:forEach>
		
		
		</div>
		<div>
			<button id="add" type="button" class="btn btn-primary">↑↑</button>
			<button id="delete" type="button" class="btn btn-primary">↓↓</button>
		</div>

		<div id="noPro">
		<c:forEach items="${noPros }" var="pro">
		<div class="pro" data-id="${pro.id }">${pro.name }</div>
		
		
		
		</c:forEach>
		</div>




	</div>





</body>
</html>