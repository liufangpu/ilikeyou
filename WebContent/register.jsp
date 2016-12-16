<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./static/css/register.css" rel='stylesheet' type='text/css' />
<script type="text/javascript" src="./static/js/jquery-1.11.0.js"></script>
		
<title>Insert title here</title>
</head>
<base href="<%=basePath%>">
<body>
<div class="main">
		<div class="header" >
			<h1>Login or Create a Free Account!</h1>
		</div>
		<p>Welcome To Liufangpu's  Communication community！</p>
			<form id="myForm">
				<ul class="left-form"  >
					<h2>register a new account:</h2>
					<input id="show" type="hidden" style="color: red"/>
					<li>
						<input type="text" name="username" id="username"  placeholder="username"  required oninvalid="setCustomValidity('用户名不能为空');" oninput="setCustomValidity('');"  onblur="loadXMLDoc();"/>
						<a href="javasrcipt:void(0);" id="test"  > </a>
						
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="text" name="email" id="email"  placeholder="email" required oninvalid="setCustomValidity('邮箱不能为空');" onblur="loadXMLDoc1();"/>
						<a href="javasrcipt:void(0);" id= "test2"> </a>
						<div class="clear"> </div>
					</li> 
					<!-- <li>
						<input type="password"   placeholder="输入密码" required/>
						<a href="#" class="icon into"> </a>
						<div class="clear"> </div>
					</li>  -->
					<li>
						<input type="password" name="password" id="password"  placeholder="输入密码,6-16位字母和数字组成" required oninvalid="setCustomValidity('密码不能为空');" onblur="loadXMLDoc2();"/>
						<a href="javasrcipt:void(0);" id="test3" > </a>
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="password" id="password2"  placeholder="确认密码" required oninvalid="setCustomValidity('确认密码');" onblur="loadXMLDoc3();"/>
						<a href="javasrcipt:void(0);" id="test4" > </a>
						<div class="clear"> </div>
					</li> 
					<!-- <label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i> </i>我同意此协议</label> -->
					<input type="submit" id="submit"  value="Create Account">
						<div class="clear"> </div>
				</ul>
				<div class="clear"> </div>
			</form>
			
		</div>
   					<div class="copy-right">
					</div>

</body>
<script type="text/javascript">
function loadXMLDoc() {
	var username=document.getElementById("username").value;
	var test=document.getElementById("test");
	var reg=/^([a-zA-Z0-9_\u4e00-\u9fa5]){3,16}$/;
	if(reg.test(username)){
		$.ajax({
    		type:"post",
    		url:'<c:url value="/user/checkName" />',
    		data:{"username":username},
    		success:function(data) {
                if (data=='true') {
                   test.setAttribute("class", "icon ticker"); 
                   document.getElementById("show").setAttribute("type", "hidden");
                }else if(data=='false'){
                    test.setAttribute("class", "icon into");
                    //alert("用户名已被占用！");
                    document.getElementById("show").setAttribute("type", "text");
                    document.getElementById("show").setAttribute("value", "用户名已被占用！");
                }
    		}
           
        });
	}else{
		test.setAttribute("class", "icon into");
		document.getElementById("show").setAttribute("type", "text");
        document.getElementById("show").setAttribute("value", "用户名格式错误！");
	}
}
function loadXMLDoc1() {
	var email=document.getElementById("email").value;
	var test=document.getElementById("test2");
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/; 
	if(reg.test(email)){
		test.setAttribute("class", "icon ticker");
		document.getElementById("show").setAttribute("type", "hidden");
	}else{
		document.getElementById("show").setAttribute("type", "text");
		document.getElementById("show").setAttribute("value", "邮箱格式无效！");
		test.setAttribute("class", "icon into");
	}
}
function loadXMLDoc2() {
	var password=document.getElementById("password").value;
	var test=document.getElementById("test3");
	//var reg = /[a-zA-Z0-9]{6,16}/; 
	var reg=/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/;
	if(reg.test(password)){
		document.getElementById("show").setAttribute("type", "hidden");
		test.setAttribute("class", "icon ticker");
	}else{
		document.getElementById("show").setAttribute("type", "text");
		document.getElementById("show").setAttribute("value", "必须包含数字和字母，且为6-16位！");
		test.setAttribute("class", "icon into");
	}
}

function loadXMLDoc3() {
	var password=document.getElementById("password").value;
	var password2=document.getElementById("password2").value;
	var test=document.getElementById("test4");
	var reg = /[a-zA-Z0-9]{6,16}/; 
	if(reg.test(password2)&&password==password2){
		document.getElementById("show").setAttribute("type", "hidden");
		test.setAttribute("class", "icon ticker");
	}else{
		document.getElementById("show").setAttribute("type", "text");
		document.getElementById("show").setAttribute("value", "两次输入不一致！");
		test.setAttribute("class", "icon into");
	}
}
$("#submit").click(function (){
	var username=document.getElementById("username").value;
	var email=document.getElementById("email").value;
	var password=document.getElementById("password").value;
	var password2=document.getElementById("password2").value;
	var test=document.getElementById("test");
	var test2=document.getElementById("test2");
	var test3=document.getElementById("test3");
	/* var test4=document.getElementById("test4"); */
	if (username!=''&&email!=''&&password!=''&&password2!=''){
		if(test.getAttribute("class")=='icon ticker'&&test2.getAttribute("class")=='icon ticker'&&test3.getAttribute("class")=='icon ticker'){
			if(password==password2){
				//test4.setAttribute("class", "icon ticker");
				//document.getElementById("show").setAttribute("type", "hidden");
				$.ajax({
		            cache: true,
		            type: "POST",
		            url:'<c:url value="/user/addUser" />',
		            data:$('#myForm').serialize(),
		            async: false,
		            error: function(request) {
		                alert("Connection error");
		            },
		            success: function(data) {
		            	if(data=='true'){
		            		//alert("注册成功");
		            		window.open('success.html');
		            	}else{
		            		alert("注册失败");
		            		window.location.href='register.html';
		            	}
		            }
		        });
			}else{
				//test4.setAttribute("class", "icon into");
				alert("两次密码输入不一致！");
				//document.getElementById("show").setAttribute("type", "text");
                //document.getElementById("show").setAttribute("value", "两次密码输入不一致！");
			}
		}else{
			alert("信息填写错误，请重新填写！");
			return;
			//window.location.href='<c:url value="register.jsp" />';
		}
}
});

</script>
</html>