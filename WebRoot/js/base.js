if (typeof hnfealean != "object" || hnfealean == null) {
    hnfealean = {};
}
function StringBuffer(){
this.strings = new Array;
}
StringBuffer.prototype.append = function(str){
this.strings.push(str);
};
StringBuffer.prototype.toString = function(s){
return this.strings.join(s);
};
StringBuffer.prototype.length =function(){
return this.strings.length;
}

function addJson(_src){
	addJson(_src,"gb2312");
}
function addJson(_src,_charset){
	C=document.createElement("SCRIPT");
	C.src=_src;
	C.charset=_charset;
	document.body.appendChild(C);
}
hnfealean.addEventHandler =function(target,eventType,handlerFunction){
	//alert(target+handlerFunction);
	if(target.addEventListener){
		target.addEventListener(eventType,handlerFunction,false);
	}else if(target.attachEvent){
		var f = eval(handlerFunction);
		target.attachEvent("on"+eventType,f);
	}else{
		target["on"+eventType]=handlerFunction;
	}
};
hnfealean.removeEventHandler =function(target,eventType,handlerFunction){
	if(target.removeEventListener){
		target.removeEventListener(eventType,handlerFunction,false);
	}else if(target.detachEvent){
		target.detachEvent("on"+eventType,handlerFuntion);
	}else{
		target["on"+eventType]=null;
	}
};
function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}
	
function ltrim(stringToTrim) {
	return stringToTrim.replace(/^\s+/,"");
}
		
function rtrim(stringToTrim) {
	return stringToTrim.replace(/\s+$/,"");
}
function getEventSrcElement(){
	window.event?o=window.event.srcElement:o=this;
	return o;
}
String.prototype.trim = function() {return this.replace(/^\s+|\s+$/g,"");}
String.prototype.ltrim = function() {return this.replace(/^\s+/,"");}
String.prototype.rtrim = function() {return this.replace(/\s+$/,"");}

function addMask(message){
	var maskDivExist = document.getElementById("mask");
	var highLightDivExist = document.getElementById("highlightbox");
	if(maskDivExist==undefined){
		var maskDiv = document.createElement("DIV");
		maskDiv.className="mask";
		maskDiv.id="mask";
		document.body.appendChild(maskDiv);
		//document.getElementById("mask").style.display="block";
	}else{
		maskDivExist.style.display="block";
	}
	if(highLightDivExist==undefined){
	//document.getElementById("mask").innerHTML=innerHtml;
	var highLightDiv = document.createElement("DIV");
	highLightDiv.className="highlightbox";
	highLightDiv.id="highlightbox";
	highLightDiv.align="center";
	document.body.appendChild(highLightDiv);
	//document.getElementById("highlightbox").innerHTML=innerHtml;
	
	//document.getElementById("maskMessage").innerHTML=message;

	
	}
		var innerHtml = "<div class='highlighthead'><input type='button' onclick='hideMask();' value='X'></div><div id='maskMessage'></div>";
		
		document.getElementById("highlightbox").innerHTML=innerHtml;
		
		document.getElementById("maskMessage").innerHTML=message;
		document.getElementById("highlightbox").style.display="block";
	
}
function hideMask(){
	var mask = document.getElementById("mask");
	var highLightBox = document.getElementById("highlightbox");
	if(mask!=undefined){
		mask.style.display="none";
	}
	if(highLightBox!=undefined){
		highLightBox.style.display="none";
	}
}
function setTab(name,cursel,n){
	for(i=1;i<=n;i++){
	var menu=document.getElementById(name+i);/* two1 */
	var con=document.getElementById("con_"+name+"_"+i);/* con_two_1 */
	menu.className=i==cursel?"hover":"";/*三目运算  等号优先*/
	con.style.display=i==cursel?"block":"none";
	}
}

function send_request(callback, urladdress, isReturnData){      
    var xmlhttp = getXMLHttpRequest();
    xmlhttp.onreadystatechange = function(){
        	if (xmlhttp.readyState == 4) {
				    try{
			    	if(xmlhttp.status == 200){
						if(isReturnData && isReturnData==true){
							callback(xmlhttp.responseText);
						}
					}else{
						callback("抱歉，没找到此页面:"+ urladdress +"");
					}
		        } catch(e){
		        	callback("抱歉，发送请求失败，请重试 " + e);
		        }
		   }
    }
    xmlhttp.open("GET", urladdress, true);
    xmlhttp.send(null);
}

function getXMLHttpRequest() {
    var xmlhttp;
	if (window.XMLHttpRequest) {
		try {
			xmlhttp = new XMLHttpRequest();
			xmlhttp.overrideMimeType("text/html;charset=UTF-8");//设定以UTF-8编码识别数据
		} catch (e) {}
	} else if (window.ActiveXObject) {
		try {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e) {
			try {
				xmlhttp = new ActiveXObject("Msxml2.XMLHttp");
			} catch (e) {
				try {
					xmlhttp = new ActiveXObject("Msxml3.XMLHttp");
				} catch (e) {}
			}
		}
	}
    return xmlhttp;
}

function getHistory(id){

	var recentHistory = document.getElementById('recentHistory');		

	if(recentHistory){

		recentHistory.innerHTML= "";

		send_request(function(value){recentHistory.innerHTML=value}, "product/manage.do?method=getHistory&id="+id, true);

	}

}

