var message;
var fixed = $("div.fixed-hidden"), container = $("div.container"), 
	p = $("#message"), createUser = $("div.hidden-createUser"), getAdmin = $("div.hidden-getAdmin");
var bn;
var list;
var totalpages;
var pageNo;
var i;
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
			if(res.pageNo==null && res.msg == null)
			{
				fixed.show();
			}
			else if(res.msg != null)
			{
				getAdmin.hide();
				createUser.show();
			
			}
			else{
				createUser.hide();
				getAdmin.show();
			}
			p.text(res.message);
			bn = res.bn;
		}
});
}

function getUserListByPage(pageNo)
{
	$.post(contextPath + '/user/getUserListByPage.do', {'pageNo' :pageNo},function(res){
			if(!res.success)
			{
				alert("不存在用戶");
			}
			else
			{
				list = res.list;
				pageNo = res.pageNo;
				totalpages = res.totalpages;
				window.location.reload(true); 
			}
			window.location.reload(true); 
		});
}

function searchByName()
{
	var name = document.getElementById("findName").value;
	$.post(contextPath + '/user/findUserByName.do',{'findName' :name}, function(res){
		if(!res.success)
		{
			alert("查找不到！");
		}
		else
		{
			list = res.list
			window.location.reload(); 
		}
	});
}

