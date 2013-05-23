<%@ page language="java" contentType="text/html;charset=utf-8"
    pageEncoding="UTF-8"%>
    <div class="w">
<DIV class="top_div">
<div class="top_div_logo">
<a href="/"><img class="logo" src="images/global/logo.gif" title="LOGO-AngelInTheBox-盒中天使"></a></div>
<div class="top_div_top_menu">
<script type="text/javascript" src="js/jquery-1.2.6.min.js"></script>
<script type="text/javascript" src="js/jquery.lazyload.js"></script>  
<script type="text/javascript"> 
jQuery(document).ready(
function($){
$("img").lazyload({
     placeholder : "/images/grey.gif",
     effect      : "fadeIn",
     failurelimit : 14 
});
}); 
</script>

<!--  <link rel="stylesheet" href="/css/lavalamp_test.css" type="text/css"/>
<script type="text/javascript" src="/js/jquery-1.1.3.1.min.js"></script> 
<script type="text/javascript" src="/js/jquery.easing.min.js"></script> 
<script type="text/javascript" src="/js/jquery.lavalamp.min.js"></script> 
<script type="text/javascript"> 
    $(function() {
        $("#1, #2, #3").lavaLamp({
            fx: "backout", 
            speed: 700,
            click: function(event, menuItem) {
                return true;
            }
        });
    });
</script>  -->
<ul class="lavaLampWithImage"> 
    <li><a href="#">首　页</a></li> 
    <li><a href="user/center.do">个人中心</a></li> 
    <li><a href="user/shoppingcart.do">购物车</a></li> 
    <li><a href="helpcenter">帮助中心</a></li> 
</ul> 

 		</div>
</DIV>
<DIV class="clr bg0">
<ul class="menu_ul">
<li><a href="digital-wholesale">手机数码</a></li>
<li><a href="diannao_ruanjian_bangong-wholesale">电脑、软件、办公</a></li>
<li><a href="jiayongdianqi_qicheyongpin-wholesale">家用电器、汽车用品</a></li>
<li><a href="shouji-wholesale">手机</a></li>
</ul>
    <FORM name="tsearch-panel-fields"  
          action="search-products.do" class="tsearch-panel-fields"
          method=post><input type="hidden" name="method" value="search"/>
    <input accesskey="s" id="q" name="key">
    <button type="submit">搜索</button>
 
            </FORM>
<span class="clr"></span>
</DIV>
</div>