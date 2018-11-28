$(function(){
	/* getUserListByPage(1); */
	/*window.location.reload();*/
});
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
				/*self.location.replace(location.href); */
				/*window.location.reload();*/
			}
			alert("加载");
		});
/*	 $.ajax({
			url:contextPath+"/user/getUserListByPage.do?pageNo="+pageNo,
			type:"post",
			dataType:"json",
			success:function(res){
				 if(res.success){  
					 list = res.list;
						pageNo = res.pageNo;
						totalpages = res.totalpages;
						
				 }
			}
		 });*/
}

function getUser(userId)
{
	$.post(contextPath + '/user/findByUserId.do', {'userId' :userId},function(res){
		if(!res.success)
		{
			alert("用戶不存在");
		}
		else
		{
			window.location.href=("user.do");
			/*window.location.reload(true);*/
		}
		/*window.location.reload();*/
	});
}