<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/include.inc.jsp" %>
<%--
	模块：账号锁定界面，输入密码解锁
--%>

<div style="width: 350px;margin: 20px auto;" >
	<form  action="sy_login/unlock.do"  method="post"  onsubmit="return unlockAccountBefore(this)">
		 
		  
  			<div class="alert alert-error">界面已锁定，请输入登陆密码解锁！</div>
  	
    		<input type="text" name="pwd" class="easyui-validatebox" required="true"  data-options="validType:['length[6,20]']"   maxlength="20" />
   	
   		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <button type="submit" class="btn btn-primary" >解锁</button>
			    	
		  
		  <input type="hidden" name="password" />	
		  <input type="hidden" name="currentCallback" value="close" />
		
	</form>
	
	
</div>
<script type="text/javascript">
<!--
	function unlockAccountBefore(form){
		var $form=$(form);
		$.ajax({
			url:"sy_login/unlockEncryptionInfo.do",
			cache: false,
			type:"get",
			dataType:"json",
			success:function(data){
				if(data.statusCode==200){
					
					var key = RSAUtils.getKeyPair(data.exponent, '', data.modulus);  
					var password = RSAUtils.encryptedString(key, encodeURIComponent($("input[name='pwd']",$form).val())); 
					$("input[name='password']",$form).val(password);
					validateSubmitForm(form);
				}
			}
		});
		return false;
    }
//-->
</script>
