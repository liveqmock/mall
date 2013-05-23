<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<jsp:include page="/WEB-INF/templates/default/share/backstage_location_check.jsp"></jsp:include>
<link rel="stylesheet" href="css/vip.css" type="text/css">


</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="control/center/coupon/manage" method="post" enctype="multipart/form-data">
<input type="hidden" name="method" value="addCoupon"/>
<br>
  <table width="90%" border="0" cellspacing="2" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"><td colspan="2"  > <font color="#FFFFFF"></font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">优惠券名称</div></td>
      <td width="78%"> <input type="text" name="name" size="64" maxlength="64"/>
        </td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">优惠金额</div></td>
      <td width="78%"><input type="text" name="discountPrice"/>
         </td>
    </tr>
       <tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">是否限制使用次数</div></td>
      <td width="78%"><input type="radio" name="limitTimes" value="true" checked/>限制<br/>
		<input type="radio" name="limitTimes" value="false"/>不限制<br/>
         </td>
    </tr>
    	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">最大使用次数</div></td>
      <td width="78%"><input type="text" name="maxTimes"/>
         </td>
    </tr>

    	<tr bgcolor="f5f5f5"> 
      <td width="22%" > <div align="right">是否可用</div></td>
      <td width="78%"><input type="radio" name="avaliable" value="true" checked/>可用<br/>
		<input type="radio" name="avaliable" value="false"/>不可用<br/>
         </td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="submit" name="SYS_SET" value=" 确  定  " class="frm_btn">
         <input type="button" value=" 后  退   " class="frm_btn" onclick="javascript:history.back(-1)">
        </div></td>
    </tr>
  </table>
</html:form>
 
<br>
</body>
</html>