<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="js/jquery.js"></script>
<script type="text/javascript">
	
	$().ready(function(){
		$("#upload").click(function(){
			
			var formData = new FormData();
			for(var i=0;i<$("[name=photo]")[0].files.length;i++){
				formData.append("photo",$("[name=photo]")[0].files[i]);

								
			}
			
			
			$.ajax({
			url:"employee?type=upload",
			type:"post",
			data:formData,
			cache:false,
			processData:false,
			contentType:false,
			dataType:"text",
			success:function(data){
				var str="<img src='pic/'"+data+"'/>"
				str+="<input type='hidden' name='picture' value='"+data+"'/>";
			$("#photos").append(str);	
			
				
			}
		})		
		})
		$(document).on("click","#photos img",function(){
			$(this).next().remove();
			$(this).remove();
			//发送阿贾克斯请求
		})
		
		
		
		
		
		
		
		
		
	})
	
	
	
	
	
	
	
	
	
</script>
<style type="text/css">
#main{
width:400px;
margin:20px auto;

}
#photos img{
width:100px;
height:100px;
}


</style>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
</head>
<body>

	<div id="main">
		<form action="employee?type=add2" class="form-horizontal" role="form" method="post" >
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name"
						placeholder="请输入姓名">
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">姓别</label>
				<div class="col-sm-10">
					<input type="radio" value="男" name="sex" >男
					<input type="radio" value="女" name="sex">女
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">年龄</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="age"
						placeholder="请输入年龄">
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">部门</label>
				<div class="col-sm-10">
					<select name="depId" class="form-control">
					<option value="">部门</option>
					
					<c:forEach items="${deps}" var="dep">
					
					<option value="${dep.id }" >${dep.name }</option>
					</c:forEach>
					</select>
				</div>
			</div>
			
			
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">头像</label>
				<div class="col-sm-7">
					<input type="file" class="form-control" name="photo" />
				</div>
				<div class="col-sm-3">
					<input type="button" id="upload" class="btn btn-primary" value="上传"/>
				</div>
			</div>
			<div class="form-group" id="photos"></div>
			
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary">保存</button>
				</div>
			</div>
		</form>
	</div>

</body>
</html>