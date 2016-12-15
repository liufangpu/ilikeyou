<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./static/css/register.css" rel='stylesheet' type='text/css' />
<script type="text/javascript" src="./static/js/jquery-1.11.0.js"></script>
		
<title>Insert title here</title>
</head>
<body>
<div class="main">
		<div class="header" >
			<h1>Login or Create a Free Account!</h1>
		</div>
		<p>Welcome To Liufangpu's  Communication community！</p>
			<form>
				<ul class="left-form"  >
					<h2>register a new account:</h2>
					<li>
						<input type="text" id="username"  placeholder="username" required onblur="loadXMLDoc();"/>
						<a href="#" id="test" > </a>
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="text" id="email"  placeholder="Email" required onblur="loadXMLDoc1();"/>
						<a href="#" > </a>
						<div class="clear"> </div>
					</li> 
					<!-- <li>
						<input type="password"   placeholder="输入密码" required/>
						<a href="#" class="icon into"> </a>
						<div class="clear"> </div>
					</li>  -->
					<li>
						<input type="password" id="password"  placeholder="输入密码" required/>
						<a href="#" > </a>
						<div class="clear"> </div>
					</li> 
					<li>
						<input type="password" id="password2"  placeholder="再输一遍" required/>
						<a href="#" > </a>
						<div class="clear"> </div>
					</li> 
					<label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i> </i>我同意此协议</label>
					<input type="submit" onClick="myFunction()" value="Create Account">
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
                }else {
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


</script>
</html>