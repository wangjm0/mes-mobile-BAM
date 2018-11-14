var eahAjax = function(){
	this.ajax = function(url,params,callBack){
		var fullUrl = contextPath + url;
		$.ajax({
			 type: "POST",
			 url: fullUrl,
			 data: params,
			 success:callBack
		});
	}
}