
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<SCRIPT type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.min.js">	</SCRIPT>
<script type="text/javascript">
  var WEB_CONTEXT = '<%=basePath%>';
  
  /**
  * 将form 表单data转化为JSON格式串
  **/
  function form2Json(formID)
	{
			var serializeObj = {};
			var aray = $(formID).serializeArray();
			$(aray).each(function(){
				
				if(serializeObj[this.name])
				{
					if($.isArray(serializeObj[this.name]))
					{
						serializeObj[this.name].push(this.value);
					}
					else
					{
						serializeObj[this.name] = [serializeObj[this.name],this.value];
					}
				}
				else
				{
						serializeObj[this.name] = this.value;
				}
			});
			return JSON.stringify(serializeObj);
	}
  
</script>

<style type="text/css">
table{border-collapse:collapse;border-spacing:0;border-left:1px solid #888;border-top:1px solid #888;background:#efefef;}
th,td{border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;}
th{font-weight:bold;background:#ccc;}
</style>

