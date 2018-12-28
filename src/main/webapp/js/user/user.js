function changeAdmin()
{
	var admin =$("#admin");
	var element = document.getElementById('myimage'); 
	var text =window.location.protocol +"//"+ window.location.host + contextPath+'/image/user/y.png';
	if(element.src ==text)
	{
		element.src = "image/user/n.png";
		admin.attr("value", false);
		admin.val = false;
	}
	else
	{
		element.src = "image/user/y.png";
		admin.attr("value", true);
	}
}

function deleteUser(userId)
{
	$.post(contextpath +'user/deleteUser.do',{'userId':userId}, function(res){
		if(res.success)
			{
				windown.location.href("userList.do");
			}
		
	});
	}