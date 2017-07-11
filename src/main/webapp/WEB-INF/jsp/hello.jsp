<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎你</title>
<script type="text/javascript" src="/weixin/assets/js/libs/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
var ajaxRequest = function(){
	var rtn =$.ajax({
		url:'/weixin/list',
		type:'POST' 
	});
	rtn.then(function(data){
		console.log(data)
	},function(code){
		console.log(code)
	})
}
</script>
</head>
<body>
<button onclick="ajaxRequest()">点击我</button>
</body>
</html>