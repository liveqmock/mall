<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<META http-equiv=Content-Language content=zh-CN>
<title>��������</title></head>
<body>
	<form name="yeepay" action="https://www.yeepay.com/app-merchant-proxy/node" method='post' id="yeepayform">	
		<input type='hidden' name='p0_Cmd'   value="${yeepayRequest.p0_Cmd}"> <!-- ��������,����֧���̶�ΪBuy -->
		<input type='hidden' name='p1_MerId' value="${yeepayRequest.p1_MerId}"> <!-- �̼�ID -->
		<input type="hidden" name="p2_Order" value="${yeepayRequest.p2_Order}"> <!-- �̼ҵĽ��׶����� -->
		<input type='hidden' name='p3_Amt'   value="${yeepayRequest.p3_Amt}"> <!-- ������� -->
		<input type='hidden' name='p4_Cur'   value="${yeepayRequest.p4_Cur}"> <!-- ���ҵ�λ -->
		<input type='hidden' name='p5_Pid'   value="${yeepayRequest.p5_Pid}"> <!-- ��ƷID -->
		<input type='hidden' name='p6_Pcat'  value="${yeepayRequest.p6_Pcat}"> <!-- ��Ʒ���� -->
		<input type='hidden' name='p7_Pdesc' value="${yeepayRequest.p7_Pdesc}"> <!-- ��Ʒ���� -->
		<input type='hidden' name='p8_Url'   value="${yeepayRequest.p8_Url}"> <!-- ���׽��֪ͨ��ַ -->
		<input type='hidden' name='p9_SAF'   value="${yeepayRequest.p9_SAF}"> <!-- ��Ҫ��д�ͻ���Ϣ 0������Ҫ 1:��Ҫ -->
		<input type='hidden' name='pa_MP'    value="${yeepayRequest.pa_MP}"> <!-- �̼���չ��Ϣ -->
		<input type='hidden' name='pd_FrpId' value="${yeepayRequest.pd_FrpId}"> <!-- ����ID -->
		<!-- Ӧ����� Ϊ��1��: ��ҪӦ�����;Ϊ��0��: ����ҪӦ����� -->
		<input type="hidden" name="pr_NeedResponse"  value="0">
		<input type='hidden' name='hmac' value="${yeepayRequest.md5hmac}"><!-- MD5-hmac��֤�� -->
		<!--	<input type="submit" value="ȷ��" />-->
	</form>

	 <script type="text/javascript">document.getElementById("yeepayform").submit();</script> 
	
</body></html>