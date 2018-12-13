<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../includeFile.jsp"%>
<%
	String id = request.getParameter("id");
%>
<SCRIPT type="text/javascript" src="../js/jquery-1.9.1.min.js">	</SCRIPT>	
<SCRIPT type="text/javascript">
	  var v_url = WEB_CONTEXT+'rest/userinfo/userDetail';
	  var v_id = '<%=id%>';
	  var v_data = '{\"id\":\"'+v_id+'\"}';
 		$(document).ready(function(){
        $.ajax({    
            type:"POST",   
            url: v_url,
            dataType:"json",    
            contentType: 'application/json',
            data:v_data,
            success: function(data){
            	if(data.success)
            	{
            		$('#password').attr('value',data.user.password);
             		$('#loginID').attr('value',data.user.loginID);
             		$('#name').attr('value',data.user.name);
             		$('#age').attr('value',data.user.age);
             		$('#gender').attr('value',data.user.gender);
            	}
            	else
            	{
            		alert(data.msg);	
            	}
            },
            error:function(e)
            {
            	alert(JSON.stringify(e));	
            }  
        });  
    });
		
		function editUser()
		{
			var formData = form2Json("#userEdit");
			alert(formData);
			var v_url = WEB_CONTEXT+'rest/userinfo/update';
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
		<h3>编辑用户</h3>
	</div>
<br/>

	
<br/>
	<div>
  		<div align="left">
  		<form id="userEdit" name="userEdit">
  		<table width="400" cellpadding="0" cellspacing="0" border="1">
				<tbody>
						
						<tr class="list">
							<th width="30%">
								登录ID：
							</th>
							<td width="70%">
								<input name="id" type="hidden" id="id" value="<%=id%>"/>
								<input name="password" type="hidden" id="password"/>
								<input name="loginID" type="text" id="loginID" size="20" maxlength="20" readonly="true"/>
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
								<input name="submit" type="button" value="编辑用户" onclick="editUser()"/>
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