<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<html>
<head>
<title>查询产品</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link rel="stylesheet" href="../../../css/vip.css" type="text/css">
<SCRIPT language=JavaScript src="../../../js/FoshanRen.js"></SCRIPT>
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<html:form action="control/product/product/manage" method="post">

<input name="method" value="query" type="hidden">
<input name="productTypeId" value="" type="hidden">
  <table width="98%" border="0" cellspacing="1" cellpadding="3" align="center">
    <tr bgcolor="6f8ac4"> 
      <td colspan="2" ><font color="#FFFFFF">查询产品：</font></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">产品名称  ：</div></td>
      <td width="75%"> <html:text property="name" size="50" maxlength="40"/></td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">产品类别  ：</div></td>
      <td width="75%"> <input type="text" name="productTypeName" disabled="true" size="30" value=""/> 
        <input type="button" name="select" value="选择..." onClick="javaScript:winOpen('<html:rewrite action="control/product/product/manage"/>?method=selectProductType','列表',600,400)">
      </td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">底(采购)价(元) ：</div></td>
      <td width="75%"> 
在<html:text property="startBasePrice" size="10" maxlength="10" onkeypress="javascript:InputLongNumberCheck()"/>
      与<html:text property="endBasePrice" size="10" maxlength="10" onkeypress="javascript:InputLongNumberCheck()"/>之间
</td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">销售价(元) ：</div></td>
      <td width="75%"> 在<html:text property="startSellPrice" size="10" maxlength="10" onkeypress="javascript:InputLongNumberCheck()"/>
      与<html:text property="endSellPrice" size="10" maxlength="10" onkeypress="javascript:InputLongNumberCheck()"/>之间
      </td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">货号 ：</div></td>
      <td width="75%"> <html:text property="code" size="20" maxlength="30"/>(注:供货商提供的便于产品查找的编号)</td>
    </tr>
	<tr bgcolor="f5f5f5"> 
      <td width="25%"> <div align="right">品牌 ：</div></td>
      <td width="75%">     <%--      <select name="brandId">
          <option  value="">***无***</option>
       <c:forEach items="${brands.datas}" var="brand" varStatus="loop">
      	 <option  value="${brand.id}">${brand.name}</option>
       </c:forEach>
       --%>
       <html:select property="brandId">

          <html:option value="">***无***</html:option>
            <c:forEach items="${brands.datas}" var="brand" varStatus="loop">
      	 <option  value="${brand.id}">${brand.name}</option>
       </c:forEach>
        </html:select>
       </td>
    </tr>
    <tr bgcolor="f5f5f5"> 
      <td colspan="2"> <div align="center"> 
          <input type="submit" value=" 确 认 " class="frm_btn">
          &nbsp;&nbsp;<input type="button"  value=" 返 回 " class="frm_btn" onclick="javascript:history.back()">
        </div></td>
    </tr>
  </table>
</html:form>
<br>
</body>
</html>