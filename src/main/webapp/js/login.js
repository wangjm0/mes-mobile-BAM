var message;
var fixed = $("div.fixed-hidden"), container = $("div.container"), p = $("#message"), createUser = $("div.hidden-createUser"), getAdmin = $("div.div.hidden-getAdmin");
var bn;
$(function(){
	fixed.hide();
	createUser.hide();
	getAdmin.hide();
	container.css("pointer-events", "auto");
	checkAll();
	
});

function checkAll(){
$.post(contextPath + '/login/checkAll.do', function(res){
	if(res.success == false)
		{
			container.css("opacity", 0.4);
			container.css("pointer-events", "none");
			fixed.show();
			p.text(res.message);
			bn = res.bn;
		}
});
}

function click()
{
	fixed.hide();
	if(bn)
	{
		createUser.show();
	}
	else
	{
		getAdmin.show();
	}
}
