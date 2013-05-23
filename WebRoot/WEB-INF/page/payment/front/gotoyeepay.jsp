<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<META http-equiv=Content-Language content=zh-CN>
<title>正在连接</title></head>
<body>
	<form name="yeepay" action="https://www.yeepay.com/app-merchant-proxy/node" method='post' id="yeepayform">	
		<input type='hidden' name='p0_Cmd'   value="${yeepayRequest.p0_Cmd}"> <!-- 请求命令,在线支付固定为Buy -->
		<input type='hidden' name='p1_MerId' value="${yeepayRequest.p1_MerId}"> <!-- 商家ID -->
		<input type="hidden" name="p2_Order" value="${yeepayRequest.p2_Order}"> <!-- 商家的交易定单号 -->
		<input type='hidden' name='p3_Amt'   value="${yeepayRequest.p3_Amt}"> <!-- 订单金额 -->
		<input type='hidden' name='p4_Cur'   value="${yeepayRequest.p4_Cur}"> <!-- 货币单位 -->
		<input type='hidden' name='p5_Pid'   value="${yeepayRequest.p5_Pid}"> <!-- 商品ID -->
		<input type='hidden' name='p6_Pcat'  value="${yeepayRequest.p6_Pcat}"> <!-- 商品种类 -->
		<input type='hidden' name='p7_Pdesc' value="${yeepayRequest.p7_Pdesc}"> <!-- 商品描述 -->
		<input type='hidden' name='p8_Url'   value="${yeepayRequest.p8_Url}"> <!-- 交易结果通知地址 -->
		<input type='hidden' name='p9_SAF'   value="${yeepayRequest.p9_SAF}"> <!-- 需要填写送货信息 0：不需要 1:需要 -->
		<input type='hidden' name='pa_MP'    value="${yeepayRequest.pa_MP}"> <!-- 商家扩展信息 -->
		<input type='hidden' name='pd_FrpId' value="${yeepayRequest.pd_FrpId}"> <!-- 银行ID -->
		<!-- 应答机制 为“1”: 需要应答机制;为“0”: 不需要应答机制 -->
		<input type="hidden" name="pr_NeedResponse"  value="0">
		<input type='hidden' name='hmac' value="${yeepayRequest.md5hmac}"><!-- MD5-hmac验证码 -->
		<!--	<input type="submit" value="确定" />-->
	</form>

	 <script type="text/javascript">document.getElementById("yeepayform").submit();</script> 
	
</body></html>