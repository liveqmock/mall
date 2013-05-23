<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<META http-equiv=Content-Language content=zh-CN>
<title>正在连接到支付宝</title></head>
<body>
<form id="alipaysubmit" name="alipaysubmit" action="${alipayRequest.alipaysubmit }" method="get">
<input type="hidden" name="receive_mobile" value="${alipayRequest.receive_mobile }"/>
<input type="hidden" name="logistics_fee" value="${alipayRequest.logistics_fee }"/>
<input type="hidden" name="notify_url" value="${alipayRequest.notify_url }"/>
<input type="hidden" name="payment_type" value="${alipayRequest.payment_type }"/>
<input type="hidden" name="service" value="${alipayRequest.service }"/>
<input type="hidden" name="partner" value="${alipayRequest.partner}"/>
<input type="hidden" name="_input_charset" value="${alipayRequest._input_charset }"/>
<input type="hidden" name="logistics_type" value="${alipayRequest.logistics_type }"/>
<input type="hidden" name="price" value="${alipayRequest.price }"/>
<input type="hidden" name="out_trade_no" value="${alipayRequest.out_trade_no }"/>
<input type="hidden" name="receive_address" value="${alipayRequest.receive_address }"/>
<input type="hidden" name="logistics_payment" value="${alipayRequest.logistics_payment }"/>
<input type="hidden" name="subject" value="${alipayRequest.subject }"/>
<input type="hidden" name="receive_zip" value="${alipayRequest.receive_zip }"/>
<input type="hidden" name="quantity" value="${alipayRequest.quantity }"/>
<input type="hidden" name="receive_name" value="${alipayRequest.receive_name }"/>
<input type="hidden" name="body" value="${alipayRequest.body }"/>
<input type="hidden" name="return_url" value="${alipayRequest.return_url }"/>
<input type="hidden" name="show_url" value="${alipayRequest.show_url }"/>
<input type="hidden" name="receive_phone" value="${alipayRequest.receive_phone }"/>
<input type="hidden" name="sign" value="${alipayRequest.sign }"/>
<input type="hidden" name="sign_type" value="${alipayRequest.sign_type }"/>
<input type="hidden" name="seller_email" value="${alipayRequest.seller_email}"/>
<!--<input type="submit" value="提交">-->

</form>

	 <script type="text/javascript">document.getElementById("alipaysubmit").submit();</script> 
	
</body></html>