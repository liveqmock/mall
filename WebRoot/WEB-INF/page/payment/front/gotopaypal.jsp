<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<META http-equiv=Content-Language content=zh-CN>
<title>转向paypal支付页面</title></head>
<body>
<form
 action="https://sandbox.paypal.com/cgi-bin/webscr" id="paypal_standard_checkout" name="paypal" method="POST">

 <input id="business" name="business" value="hothotearn@gmail.com" type="hidden"/>	
 
 <input type="hidden" value="http://127.0.0.1/pay/paypal.do?method=handler" name="return">
<input type="hidden" value="http://127.0.0.1/pay/paypal.do?method=handler" name="cancel_return">
<input type="hidden" value="http://127.0.0.1/pay/paypal.do?method=handler" name="shopping_url">
<input type="hidden" value="http://127.0.0.1/pay/paypal.do?method=handler" name="notify_url">

<input type="hidden" value="_xclick" name="redirect_cmd">


<input type="hidden" value="1" name="no_shipping">
<input type="hidden" value="1" name="upload">
<input type="hidden" value="USD" name="currency_code">
<!--<input type="hidden" value="CNY" name="currency_code">--> 
<input type="hidden" value="_ext-enter" name="cmd">


<input id="bn" name="bn" value="angelinthebox" type="hidden"/>
<input id="upload" name="upload" value="1" type="hidden"/>
<input type="hidden" value="angelinthebox${order.orderId}" name="item_name">
<input type="hidden" value="${order.createDate }" name="item_number">
 <input type="hidden" value="${order.totalOrderPrice}" name="amount">
<!--<input type="hidden" value="0.00" name="shipping">
<input type="hidden" value="0.00" name="tax">
<input type="hidden" value="0.00" name="tax_cart">-->
</form><script type="text/javascript">document.getElementById("paypal_standard_checkout").submit();</script></body></html>