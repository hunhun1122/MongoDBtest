<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

</head>
<body>
<table width="500" height="300" border="0" align="center">
</table>
<table width="500" border="0" align="center">
<tr>
<td colspan="2" style="background-color:#99bbbb;" align="center">
<h1>验证模型</h1>
</td>
</tr>

<tr valign="center">

<td style="background-color:#EEEEEE;height:200px;width:400px;" align="center">
<div>
	<form id="loginForm" name="loginForm" method="POST" action="<%=basePath%>rest/userinfo/login">
		<table>
			<tr><td>登录ID：</td><td><input type="text" name="loginID" id="loginID"/></td></tr>
			<tr><td>密码：</td><td><input type="password" name="password" id="password"/></td></tr>
			<tr><td>&nbsp;</td><td align="center"><input type="submit" value="登录"/></td></tr>
		</table>
	</form>
</div>

</td>
</tr>

<tr>
<td colspan="2" style="background-color:#99bbbb;text-align:center;">
Copyright GWM</td>
</tr>
</table>

</body>
</html>
