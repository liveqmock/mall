
$.cookie=
function (a, b, c) {
    if (typeof b != "undefined") {
        c = c || {};
        if (b === null) {
            b = "";
            c.expires = -1;
        }
        var d = "";
        if (c.expires &&
            (typeof c.expires == "number" || c.expires.toUTCString)) {
            var e;
            if (typeof c.expires == "number") {
                e = new Date;
                e.setTime(e.getTime() + c.expires * 24 * 60 * 60 * 1000);
            } else {
                e = c.expires;
            }
            d = ";expires=" + e.toUTCString();
        }
        var f = c.path ? ";path=" + c.path : "";
        var g = c.domain ? ";domain=" + c.domain : "";
        var h = c.secure ? ";secure" : "";
        document.cookie = [a, "=", encodeURIComponent(b), d, f, g, h].join("");
    } else {
        var j = null;
        if (document.cookie && document.cookie != "") {
            var k = document.cookie.split(";");
            for (var i = 0; i < k.length; i++) {
                var l = jQuery.trim(k[i]);
                if (l.substring(0, a.length + 1) == a + "=") {
                    j = decodeURIComponent(l.substring(a.length + 1));
                    break;
                }
            }
        }
        return j;
    }
};

/*ModelCallCenter#20110126*/
var modelCallCenter = {
	settings : {
		clstag1 : 0,
		clstag2 : 0
	},
	tbClose : function() {
		if ($(".thickbox").length != 0) {
			thickBoxclose()
		}
	},
	login : function() {
		this.tbClose();
		var _this = this;
		setTimeout(
				function() {
					$.thickBox( {
								type : "iframe",
								title : "您尚未登录",
								source : "http://passport.360buy.com/new/LoginFrame.aspx?clstag1="
										+ _this.settings.clstag1
										+ "&clstag2="
										+ _this.settings.clstag2
										+ "&r="
										+ Math.random(),
								width : 450,
								height : 360,
								_title : "thicktitler",
								_close : "thickcloser",
								_con : "thickconr"
							})
				}, 20)
	},
	regist : function() {
		this.tbClose();
		var _this = this;
		setTimeout(
				function() {
					$.thickBox( {
								type : "iframe",
								title : "您尚未登录",
								source : "http://passport.360buy.com/new/registPersonalFrame.aspx?clstag1="
										+ _this.settings.clstag1
										+ "&clstag2="
										+ _this.settings.clstag2
										+ "&r="
										+ Math.random(),
								width : 450,
								height : 500,
								_title : "thicktitler",
								_close : "thickcloser",
								_con : "thickconr"
							})
				}, 20)
	},
	init : function() {
		var _this = this;
		$.getJSON(
						"http://passport.360buy.com/new/loginwebservice.aspx?callback=?",
						{
							method : "GetHello"
						}, function(result) {
							_this.tbClose();
							if (result && result.info != "") {
								$("#shortcut .fore1").html(result.info)
							}
							_this.settings.fn()
						})
	}
};
/* login#20110126 */
$.login = function(options) {
	options = $.extend(
					{
						loginService : "http://passport.360buy.com/loginservice.aspx?callback=?",
						loginMethod : "Login",
						loginUrl : "https://passport.360buy.com/new/login.aspx",
						returnUrl : location.href,
						automatic : true,
						complete : null,
						modal : false
					}, options || {});
	if (options.loginService != "" && options.loginMethod != "") {
		$.getJSON(options.loginService, {
			method : options.loginMethod
		}, function(result) {
			if (result != null) {
				if (options.complete != null) {
					options.complete(result.Identity)
				}
				if (!result.Identity.IsAuthenticated && options.automatic
						&& options.loginUrl != "") {
					if (options.modal) {
						modelCallCenter.login()
					} else {
						location.href = options.loginUrl + "?ReturnUrl="
								+ escape(options.returnUrl)
					}
				}
			}
		})
	}
};
/* mycart20110223 */
var miniCartService = {
	settings : {
		url : "http://jd2008.360buy.com/purchase/minicartservice.aspx?tmp=0&callback=?",
		amountCookie : "cn",
		amountObject : $("#mycart-amount"),
		saveObject : $("#btn-savetocart"),
		removeObject : $("#mycart-list a[id][name]"),
		cartObject : $("#o-mycart-list"),
		listObject : $("#mycart-list"),
		eventObject : $("#i-mycart"),
		flag : true
	},
	refresh : function(c) {
		var b = this, a = this.settings;
		$.login( {
			automatic : false,
			complete : function(d) {
				$.getJSON(b.settings.url, {
					method : "GetCart"
				}, function(e) {
					if (e.Cart != null && e.GetCartNew != null) {
						a.amountObject.text(e.Cart.Num);
						a.listObject.html(e.GetCartNew.process(e));
						a.eventObject.attr("load", "true");
						a.flag = true;
						if (c) {
							c();
						}
					}
				});
			}
		});
	},
	remove : function(a) {
		var d = this, c = parseInt(a.attr("id")), b = a.attr("name");
		if (c > 0 && b) {
			$.getJSON(d.settings.url, {
				method : b,
				cartId : c
			}, function(e) {
				if (e.Result) {
					d.refresh();
				}
			});
		}
	},
	save : function() {
		var a = this;
		$.login( {
			complete : function(b) {
				if (b.IsAuthenticated) {
					$.getJSON(a.settings.url, {
						method : "SaveCart"
					}, function(c) {
						if (c.Result) {
							alert("寄存购物车成功");
						}
					});
				}
			}
		});
	},
	init : function() {
		var b = this, a = this.settings;
		a.amountObject.text($.cookie(a.amountCookie) || 0);
		a.eventObject.bind("mouseover", function() {
			if ($(this).attr("load") && a.flag) {
				a.cartObject.fadeIn();
			} else {
				if (a.flag) {
					a.flag = false;
					b.refresh(function() {
						a.cartObject.fadeIn();
					});
				} else {
					return;
				}
			}
		}).bind("mouseleave", function() {
			a.cartObject.fadeOut();
		});
		/*a.removeObject.livequery("click", function() {
			b.remove($(this));
		});
		a.saveObject.livequery("click", function() {
			b.save();
		});*/
	}
};
miniCartService.init();
/* saveFavorite#20110212 */
$.extend(
				modelCallCenter,
				{
					saveFavorite : function(productId) {
						var homeServiceUrl = "http://jd2008.360buy.com/homeservice.aspx?callback=?";
						$.login( {
									modal : true,
									complete : function(result) {
										if (result != null
												&& result.IsAuthenticated != null
												&& result.IsAuthenticated) {
											if (productId > 0) {
												$.getJSON(
																homeServiceUrl,
																{
																	method : "SaveNewFavorite",
																	productId : productId
																},
																function(result) {
																	if (result.SaveNewFavorite != null) {
																		$.thickBox( {
																					type : "text",
																					source : result.SaveNewFavorite
																							.process(result),
																					width : 300,
																					height : 100,
																					title : "提示",
																					_countdown : 6
																				})
																	}
																	$.getJSON(
																					"http://t.360buy.com/regard/save.action?goodsId="
																							+ productId
																							+ "&jsoncallback=?",
																					function(
																							json) {
																					})
																})
											}
										}
									}
								})
					}
				});
/* autoLocation#20110126 */
$.extend(modelCallCenter, {
	autoLocation : function(url) {
		var _this = this;
		$.login( {
			modal : true,
			complete : function(result) {
				if (result != null && result.IsAuthenticated != null
						&& result.IsAuthenticated) {
					window.location = url
				}
			}
		})
	}
});

/*
 * @Last-Modified:2011/03/08
 */

(function($){$.fn.jdPosition=function(option){var s=$.extend({mode:null},option||{});switch(s.mode){default:case"center":var ow=$(this).outerWidth(),oh=$(this).outerHeight();var w=$.browser.isMinW(ow),h=$.browser.isMinH(oh);$(this).css({left:$.browser.scroll().left+Math.max(($.browser.client().width-ow)/2,0)+"px",top:(!$.browser.isIE6)?(h?$.browser.scroll().top:($.browser.scroll().top+Math.max(($.browser.client().height-oh)/ 2, 0) + "px")) : (($.browser.scroll().top <= $.browser.client().bodyHeight - oh) ? ($.browser.scroll().top + Math.max(($.browser.client().height - oh) /2,0)+"px"):($.browser.client().bodyHeight-oh)+"px")});break;case"auto":break;case"fixed":break}}})(jQuery);
(function($) {
	$.fn.thickBox = function(option, callback) {
		if (typeof option == "function") {
			callback = option;
			option = {}
		}
		;
		var s = $.extend( {
			type : "text",
			source : null,
			width : null,
			height : null,
			title : null,
			_frame : "",
			_div : "",
			_box : "",
			_con : "",
			_loading : "thickloading",
			close : false,
			_close : "",
			_fastClose : false,
			_close_val : "×",
			_titleOn : true,
			_title : "",
			_autoReposi : false,
			_countdown : false
		}, option || {});
		var object = (typeof this != "function") ? $(this) : null;
		var timer;
		var close = function() {
			clearInterval(timer);
			$(".thickframe").add(".thickdiv").hide();
			$(".thickbox").empty().remove();
			if (s._autoReposi) {
				$(window).unbind("resize.thickBox").unbind(
						"scroll.thickBox")
			}
		};
		if (s.close) {
			close();
			return false
		}
		;
		var reg = function(str) {
			if (str != "") {
				return str.match(/\w+/)
			} else {
				return ""
			}
		};
		var init = function(element) {
			if ($(".thickframe").length == 0 || $(".thickdiv").length == 0) {
				$(
						"<iframe class='thickframe' id='"
								+ reg(s._frame)
								+ "' marginwidth='0' marginheight='0' frameborder='0' scrolling='no'></iframe>")
						.appendTo($(document.body));
				$("<div class='thickdiv' id='" + reg(s._div) + "'></div>")
						.appendTo($(document.body))
			} else {
				$(".thickframe").add(".thickdiv").show()
			}
			;
			$("<div class='thickbox' id='" + reg(s._box) + "'></div>")
					.appendTo($(document.body));
			if (s._titleOn)
				initTitle(element);
			$(
					"<div class='thickcon' id='" + reg(s._con)
							+ "' style='width:" + s.width + "px;height:"
							+ s.height + "px;'></div>")
					.appendTo($(".thickbox"));
			if (s._countdown)
				initCountdown();
			$(".thickcon").addClass(s._loading);
			reposi();
			initClose();
			inputData(element);
			if (s._autoReposi) {
				$(window).bind("resize.thickBox", reposi).bind(
						"scroll.thickBox", reposi)
			}
			;
			if (s._fastClose) {
				$(document.body).bind("click.thickBox", function(e) {
					e = e ? e : window.event;
					var tag = e.srcElement ? e.srcElement : e.target;
					if (tag.className == "thickdiv") {
						$(this).unbind("click.thickBox");
						close()
					}
				})
			}
		};
		var initCountdown = function() {
			var x = s._countdown;
			$(
					"<div class='thickcountdown' style='width:" + s.width
							+ "'><span id='jd-countdown'>" + x
							+ "</span>秒后自动关闭</div>").appendTo($(".thickbox"));
			timer = setInterval(function() {
				x--;
				$("#jd-countdown").html(x);
				if (x == 0) {
					x = s._countdown;
					close()
				}
			}, 1000)
		};
		var initTitle = function(element) {
			s.title = (s.title == null && element) ? element.attr("title")
					: s.title;
			$(
					"<div class='thicktitle' id='" + reg(s._title)
							+ "' style='width:" + s.width + "'><span>"
							+ s.title + "</span></div>").appendTo(
					$(".thickbox"))
		};
		var initClose = function() {
			if (s._close != null) {
				$(
						"<a href='#' class='thickclose' id='" + reg(s._close)
								+ "'>" + s._close_val + "</a>").appendTo(
						$(".thickbox"));
				$(".thickclose").one("click", function() {
					close();
					return false
				})
			}
		};
		var inputData = function(element) {
			s.source = (s.source == null) ? element.attr("href") : s.source;
			switch (s.type) {
			default:
			case "text":
				$(".thickcon").html(s.source);
				$(".thickcon").removeClass(s._loading);
				if (callback) {
					callback()
				}
				;
				break;
			case "html":
				$(s.source).clone().appendTo($(".thickcon")).show();
				$(".thickcon").removeClass(s._loading);
				if (callback) {
					callback()
				}
				;
				break;
			case "image":
				s._index = (s._index == null) ? object.index(element)
						: s._index;
				$(".thickcon").append(
						"<img src='" + s.source + "' width='" + s.width
								+ "' height='" + s.height + "'>");
				s.source = null;
				$(".thickcon").removeClass(s._loading);
				if (callback) {
					callback()
				}
				;
				break;
			case "ajax":
			case "json":
				if (callback) {
					callback(s.source, $(".thickcon"), function() {
						$(".thickcon").removeClass(s._loading)
					})
				}
				;
				break;
			case "iframe":
				$(
						"<iframe src='"
								+ s.source
								+ "' marginwidth='0' marginheight='0' frameborder='0' scrolling='no' style='width:"
								+ s.width + "px;height:" + s.height
								+ "px;border:0;'></iframe>").appendTo(
						$(".thickcon"));
				$(".thickcon").removeClass(s._loading);
				if (callback) {
					callback()
				}
				;
				break
			}
		};
		var reposi = function() {
			var w1 = $(".thickcon").outerWidth(), h1 = (s._titleOn ? $(
					".thicktitle").outerHeight() : 0)
					+ $(".thickcon").outerHeight();
			$(".thickbox").css( {
				width : w1 + "px",
				height : h1 + "px"
			});
			$(".thickbox").jdPosition( {
				mode : "center"
			});
			if ($.browser.isIE6) {
				var ow = $(".thickbox").outerWidth(), oh = $(".thickbox")
						.outerHeight();
				var w2 = $.browser.isMinW(ow), h2 = $.browser.isMinH(oh);
				$(".thickframe").add(".thickdiv").css(
						{
							width : w2 ? ow : "100%",
							height : Math.max($.browser.client().height,
									$.browser.client().bodyHeight)
									+ "px"
						})
			}
		};
		if (object != null) {
			object.click(function() {
				init($(this));
				return false
			})
		} else {
			init()
		}
	};
	$.thickBox = $.fn.thickBox
})(jQuery);
function thickBoxclose() {
	$(".thickclose").trigger("click");
};

(function($){$.extend($.browser,{client:function(){return{width:document.documentElement.clientWidth,height:document.documentElement.clientHeight,bodyWidth:document.body.clientWidth,bodyHeight:document.body.clientHeight};},scroll:function(){return{width:document.documentElement.scrollWidth,height:document.documentElement.scrollHeight,bodyWidth:document.body.scrollWidth,bodyHeight:document.body.scrollHeight,left:document.documentElement.scrollLeft+document.body.scrollLeft,top:document.documentElement.scrollTop+document.body.scrollTop};},screen:function(){return{width:window.screen.width,height:window.screen.height};},isIE6:$.browser.msie&&$.browser.version==6,isMinW:function(val){return Math.min($.browser.client().bodyWidth,$.browser.client().width)<=val;},isMinH:function(val){return $.browser.client().height<=val;}})})(jQuery);