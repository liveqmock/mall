<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/templates/default/share/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<META http-equiv=Content-Language content=zh-CN>
<title>购物车 Angel In The Box</title> 
<%@ include file="/location_check.jsp" %>
</HEAD>
<BODY>


<TABLE cellSpacing=0 cellPadding=5 width="98%" border="0" align="center">
  <TR>
    <TD><TABLE cellSpacing=0 cellPadding=0 width="96%" border=0>
      <TBODY>
        <TR>
          <TD width="24%"><IMG src="images/global/cart_001.gif" border=0></TD>
          <TD width="34%"><!-- 如果您修改了商品数量，请点击 
             <img style="CURSOR: hand; " alt="修改数量" src="images/buy/update-t-sm.gif" value="修改数量" border="0" onClick="javascript:modifyAmount()"> --></TD>
          <TD width="14%" align="left"><a href="<html:rewrite action="user/shoppingcart" />?method=deleteAll"><img style="CURSOR:hand;" alt="清空购物车" src="images/buy/az-empty-shoppingcard.gif" border="0"></a></TD>
          <TD width="15%" align=left><a href=""><img src="images/buy/as-s-continus.gif" width="116" height="22" border="0"></a></TD>
          <TD width="13%" align=right><a href="user/shoppingcart.do?method=settleAccounts&directUrl=${directUrl}" alt="进入结算中心" value="进入结算中心" border="0">
          <img style="CURSOR:hand;" src="images/buy/az-by-split.gif" width="116" height="22" border=0/>
          </a></TD>
        </TR>
      </TBODY>
    </TABLE></TD>
  </TR>

  <TR>
    <TD><form id="buycart" name="shoppingCartItemForm" action="<html:rewrite action="user/shoppingcart" />?method=updateAll" method="post">
    <INPUT TYPE="hidden" NAME="directUrl" value="${directUrl}">
    <TABLE cellSpacing=0 cellPadding=6 width="100%" border=0> 
      <TR bgColor=#d7ebff>
        <TD width="457"><STRONG>我挑选的商品</STRONG></TD>
        <TD width=112><DIV align=center><STRONG>市场价</STRONG></DIV></TD>
        <TD width=181><DIV align=center><STRONG>价格</STRONG></DIV></TD>
        <TD width=73><DIV align=center><STRONG>数量</STRONG></DIV></TD>
        <TD width=66>&nbsp;</TD>
      </TR>
      <c:if test="${! empty shoppingCart}">
<c:if test="${! empty shoppingCart.shoppingCartItems}">
<!-- loop begin -->

<c:forEach items="${shoppingCart.shoppingCartItems}" var="item"> 
       <TR vAlign="top">
        <TD><STRONG><A href="${item.product.shtml_File_Name }" target="_blank">${item.product.name}</A></STRONG>${item.product.tempImageUrl}
<br/>

<c:forEach items="${item.attributes}" var="option">

${ option.attribute.name }:${ option.value }<br/>

</c:forEach> 
         <span class="h3color"><!-- [颜色/样式：] --></span><BR><BR></TD>
        <TD width="112" align="center"><SPAN class="price" title="￥${item.product.marketPrice}元"><FONT color="black"><S><B>￥${item.product.marketPrice}元</B></S></FONT></SPAN></TD>
        <TD width="181"><center><SPAN class="price" style="text-align:center"><B>￥${item.product.sellPrice} 元</B></SPAN></center></TD>
        <TD align="middle" width="73"><input type="text" style="WIDTH: 30px" maxlength="3" value="${item.amount}"  name="amounts" onkeypress="javascript:InputIntNumberCheck()"/></TD>
        <TD align="middle" width="66"><a href="user/shoppingcart.do?method=delete&productId=${item.product.id}&itemId=${item.id }"><img height="17" src="images/buy/delete.gif" width="45" border="0"></a></TD>
      </TR>
      <TR vAlign="top">
        <TD colSpan="5"><IMG height=1 src="images/buy/green-pixel.gif" width="100%" border="0"></TD>
      </TR>
</c:forEach>
<!-- loop end -->	
</c:if> 
</c:if> 
    </TABLE>
      <table width="96%" border="0" align="left">
        <tr>
          <td width="40%" align="right">如果您修改了商品数量，请点击
          <input type="image" alt="修改数量" src="images/buy/update-t-sm.gif" value="修改数量" border="0"/>
 		  </td>
          <td width="9%" align="right"><DIV align="right"><SPAN class="price"><STRONG><B><FONT color="black">市场价总计:</FONT></B></STRONG></SPAN></DIV></td>
          <td width="11%" align="right"><DIV align="center"><SPAN class="price"><STRONG><B class="price"><FONT color="black">￥<s>${shoppingCart.totalPrice }</s>元</FONT></B></STRONG></SPAN></DIV></td>
  		  
          <td width="10%" align="right"><DIV align="right"><SPAN class="price"><STRONG><B><FONT color="black">本站总销售价:</FONT></B></STRONG></SPAN></DIV></td>
          <td width="10%" align="right"><DIV align="center"><SPAN class="price"><STRONG><B class="price"><FONT color="black">￥${shoppingCart.totalSellPrice }元</FONT></B></STRONG></SPAN></DIV></td>
          <td width="8%" align="right"><DIV align="right"><SPAN class="price"><STRONG><B><FONT color="black">节省:</FONT></B></STRONG></SPAN></DIV></td>
          <td width="12%" align="right"><DIV align="center"><SPAN class="price"><STRONG><B class="price">￥${shoppingCart.totalSavedPrice}元</B></STRONG></SPAN></DIV></td>
        </tr>
        <tr>
          <td colspan="3" align="right">&nbsp;</td>
          <td colspan="4" align="right">
         <a href="user/shoppingcart.do?method=settleAccounts&directUrl=${directUrl}" alt="进入结算中心" value="进入结算中心" border="0">
          <img style="CURSOR:hand;" src="images/buy/az-by-split.gif" width="116" height="22" border=0/>
          </a>
          </td>
        </tr>
      </table>
          </FORM>
      </TD>
  </TR>
</TABLE>
<br>

</BODY>
</HTML>
