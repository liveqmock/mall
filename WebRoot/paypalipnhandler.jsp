<%@ page import="java.util.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.io.*" %>
<%@ page import="javax.net.ssl.*" %>
<%@ page import="com.hnfealean.sport.web.TrustAnyHostnameVerifier"%>

<%
out.write("referer: "+request.getAttribute("Referer"));
// read post from PayPal system and add 'cmd'
Enumeration en = request.getParameterNames();
String str = "cmd=_notify-validate";
while(en.hasMoreElements()){
String paramName = (String)en.nextElement();
String paramValue = request.getParameter(paramName);
str = str + "&" + paramName + "=" + URLEncoder.encode(paramValue);
out.print("paramName: "+paramName+"  paramValue:"+paramValue+"<br>");
}
URL url=new URL("https://sandbox.paypal.com/cgi-bin/webscr");
HttpsURLConnection conn=(HttpsURLConnection)url.openConnection();

conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
conn.setDoOutput(true);
conn.connect();
PrintWriter pw = new PrintWriter(conn.getOutputStream());
pw.println(str);
pw.close();

BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
String line;
StringBuffer strb = new StringBuffer();
while ((line = br.readLine()) != null) {
    strb.append(line);
}
String ss = strb.toString();
System.out.println("paypal return:"+ss);
br.close();
// post back to PayPal system to validate
// NOTE: change http: to https: in the following URL to verify using SSL (for increased security).
// using HTTPS requires either Java 1.4 or greater, or Java Secure Socket Extension (JSSE)
// and configured for older versions.



// assign posted variables to local variables
String itemName = request.getParameter("item_name");
String itemNumber = request.getParameter("item_number");
String paymentStatus = request.getParameter("payment_status");
String paymentAmount = request.getParameter("mc_gross");
String paymentCurrency = request.getParameter("mc_currency");
String txnId = request.getParameter("txn_id");
String receiverEmail = request.getParameter("receiver_email");
String payerEmail = request.getParameter("payer_email");

//check notification validation
if(ss.equals("VERIFIED")) {
// check that paymentStatus=Completed
// check that txnId has not been previously processed
// check that receiverEmail is your Primary PayPal email
// check that paymentAmount/paymentCurrency are correct
// process payment
}
else if(ss.equals("INVALID")) {
// log for investigation
}
else {
// error
}
%>
