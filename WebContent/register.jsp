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
			<form action="user/addUser" method="post">
				<ul class="left-form"  >
					<h2>register a new account:</h2>
					<li>
						<input type="text" name="username" id="username"  placeholder="username" required onblur="loadXMLDoc();"/>
						<a href="#" id="test" > </a>
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="text" name="email" id="email"  placeholder="Email" required onblur="loadXMLDoc1();"/>
						<a href="#" > </a>
						<div class="clear"> </div>
					</li> 
					<!-- <li>
						<input type="password"   placeholder="输入密码" required/>
						<a href="#" class="icon into"> </a>
						<div class="clear"> </div>
					</li>  -->
					<li>
						<input type="password" name="password" id="password"  placeholder="输入密码" required/>
						<a href="#" > </a>
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="password" id="password2"  placeholder="再输一遍" required/>
						<a href="#" > </a>
						<div class="clear"> </div>
					</li> 
					<!-- <label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i> </i>我同意此协议</label> -->
					<input type="submit" onclick="submit()" value="Create Account">
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
    	$.ajax({
    		type:"post",
    		url:'<c:url value="/user/checkName" />',
    		data:{"username":username},
    		success:function(data) {
                if (data=='true') {
                   test.setAttribute("class", "icon ticker"); 
                }else if(data=='false'){
                    test.setAttribute("class", "icon into");
                }
    		}
           
        });
}
function loadXMLDoc1() {
	var username=document.getElementById("username").value;
    	$.ajax({
    		type:"post",
    		url:'<c:url value="/user/checkName" />',
    		data:{"username":username},
    		success:function(data) {
                if (data=='true1') {
                   var test=document.getElementById("test");
                   test.setAttribute("class", "icon into"); 
                }
                else {
                	var test=document.getElementById("test");
                    test.setAttribute("class", "icon ticker");
                }
    		}
           
        });
}
function submit(){
	alert("dasdsa");
	  $.post(
			  '<c:url value="/user/addUser" />',
            {
             //你传的参数  把你的input框的值以post方式传送
              username:$('#username').val(),
              password:$('#password').val(),
              email:$('#email').val()
            },
	           function(data)
            {
              //这里是从你请求的地址返回来的数据 具体怎么处理就看你自己的需求了
              alert("注册成功");
            });
	 
}

</script>
</html>