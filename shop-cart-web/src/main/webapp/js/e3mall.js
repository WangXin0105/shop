var E3MALL = {
	checkLogin : function(){
		var _ticket = $.cookie("token");
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://localhost:10009/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					var username = data.data.username;
					var html = username + "，欢迎登陆！<a href=\"http://www.e3mall.cn/user/logout.html\" class=\"link-logout\"></a>";
					$("#loginbar").html(html);
				}
			}
		});
	}
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	E3MALL.checkLogin();
});