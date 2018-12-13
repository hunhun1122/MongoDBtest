
<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
		
<%@ include file="../includeFile.jsp"%>


	<SCRIPT type="text/javascript">

		var v_url = WEB_CONTEXT+'rest/userinfo/queryAll';
 		$(document).ready(function(){  
        $.ajax({    
            type:"GET",   
            url: v_url,
            dataType:"json",    
            contentType: 'application/json', 
            success: function(data){
            	
              $.each(data.users,function(index,item){
                 $("#userList").append('<tr><td><input name=\"selectFlag\" type=\"checkbox\" value=\"'+item.id+'\"/>'
                 +'</td><td>'+item.loginID
                 +'</td><td>'+item.name
                 +'</td><td>'+item.age
                 +'</td><td>'+item.gender+'</td></tr>');  
              }); 
            },
            error:function(e)
            {
            	alert(e);
            }  
        }); 
    });
    //修改用户
    function editUser()
    {
    	var selectFlags=document.getElementsByName("selectFlag");
			var selectFlag;
			var j=0;
			for(var i=0;i<selectFlags.length;i++){
				if(selectFlags[i].checked==true){
					selectFlag=selectFlags[i];
					j++;
				}
			}
			if(j>1)
			{
				alert('您只能选择一条记录');
				return false;
			}
			if(j==0){
				alert('请选择要修改的用户');
				return false;
			}	
		window.open(WEB_CONTEXT+'userManage/userEdit.jsp?id='+selectFlag.value,'adduserinfo','height=400,width=500,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')
    }
    //删除用户
    function deleUser()
    {
    	var selectFlags=document.getElementsByName("selectFlag");
			var selectFlag;
			var j=0;
			var ids = '';
			for(var i=0;i<selectFlags.length;i++){
				if(selectFlags[i].checked==true){
					selectFlag=selectFlags[i];
					j++;
					if(j>1)
					{
						ids = ids+',';
					}
					ids = ids+'\"'+selectFlag.value+'\"';
					
				}
			}
			
			if(j==0){
				alert('请选择要删除的用户');
				return false;
			}
			var v_data = '{\"ids\":['+ids+']}';
			var v_url = WEB_CONTEXT+'rest/userinfo/delete';
			$.ajax({    
            type:"POST",   
            url: v_url,
            dataType:"json",    
            contentType: 'application/json', 
            data:v_data,
            success: function(data){
            	if(data.success)
            	{
	 							window.location.reload();
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
		<h3>用户信息管理</h3>
	</div>
<br/>


	
<br/>
	<div>
  		<div  id="userDetail" align="left">
  		<table width="600" cellpadding="0" cellspacing="0" border="1">
				<thead>
					<th width="15%">选择</th>
					<th width="17%">登录名</th>
					<th width="17%">用户姓名</th>
					<th width="17%">年龄</th>
					<th width="17%">性别</th>
					
				</thead>
				<form id="userForm" name="userForm">
				<tbody id="userList">
					
				</tbody>
				</form>
			</table>
  		</div>
  	
		<br/>
  	<div>
		<input id="addUser" type="button" value="添加用户" onclick="javascript:window.open('userAdd.jsp','adduserinfo','height=400,width=500,top=0,left=0,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no')"/>  
		<input id="modifyUser" type="button" value="修改用户" onclick="editUser()"/>	
		<input id="delUser" type="button" value="删除用户" onclick="deleUser()"/>
		<input id="delUser" type="button" value="获取列表数据" onclick="test()"/>
		</div>
	</div>
</div>

</body>
</html>