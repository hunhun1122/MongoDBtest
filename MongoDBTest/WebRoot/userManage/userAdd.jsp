<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../includeFile.jsp"%>
<SCRIPT type="text/javascript" src="../js/jquery-1.9.1.min.js">	</SCRIPT>	
<SCRIPT type="text/javascript">
	  
		
		
		function addUser()
		{
			var formData = form2Json("#userAdd");
			var v_url = WEB_CONTEXT+'rest/userinfo/Add';
			$.ajax({    
            type:"POST",   
            url: v_url,
            dataType:"json",    
            contentType: 'application/json', 
            data:formData,
            success: function(data){
            	if(data.success)
            	{
	 							window.opener.location.href=window.opener.location.href;
	 							window.close();
 							}
 							else
 							{
 								alert(data.msg);
 							}
            },
            error:function(e)
            {
            	alert('系统异常，请联系管理员');
            }
        });
			
		}
	</SCRIPT>	
</head>
<body>
<div >
	<div text align="left">
		<h3>添加用户</h3>
	</div>
<br/>

	
<br/>
	<div>
  		<div align="left">
  		<form id="userAdd" name="userAdd">
  		<table width="400" cellpadding="0" cellspacing="0" border="1">
				<tbody>
						
						<tr class="list">
							<th width="30%">
								登录ID：
							</th>
							<td width="70%">
								<input name="loginID" type="text" id="loginID" size="20" maxlength="20"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr class="list">
							<th>
								密码：
							</th>
							<td>
								<input name="password" type="password" id="password" size="20" maxlength="20"/>
								<font color="red">*</font>
							</td>
						</tr>
						<tr>
							<th>
								姓名：
							</th>
							<td>
								<input name="name" type="text" id="name" size="20" maxlength="20"/>
								<font color="red">*</font>
							</td>
						</tr>

						<tr>
							<th>
								年龄：
							</th>
							<td>
								<input name="age" type="text" id="age" size="20" maxlength="20"/>
							</td>
						</tr>

						<tr>
							<th>
								性别：
							</th>
							<td>
								<select name="gender" id="gener" style="width: 50px;">
									<option selected="selected" value="男">
										男
									</option>
									<option selected="selected" value="女">
										女
									</option>
								</select><font color="red">*</font>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input name="submit" type="button" value="创建用户" onclick="addUser()"/>
								<input type="button" value="取消"	onclick="javascript:window.close();" />
							</td>
						</tr>
					</tbody>
			</table>
			</form>
  		</div>
  	
		<br/>
  	
	</div>
</div>

</body>
</html>