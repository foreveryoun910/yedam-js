<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			// 회원목록 호출
			$.ajax({
				method: 'post',
				url: '/MemberList',
				dataType: 'json',
				success: memberList,
				error: function(){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
			
			// 회원목록 콜백함수
			let fields = ['id', 'name', 'phone', 'birth', 'address'];
			function memberList(data){
				let table = $('<table />').attr('border', '1');
				$(table).append($('<tr />').append('<th>아이디</th><th>이름</th><th>연락처</th><th>생년월일</th><th>주소</th>'));
				for(let i=0; i<data.length; i++){
					let tr = $('<tr />');
					for(let field of fields){
						let td = $('<td />').text(data[i][field]);
						$(tr).append(td);
					}
				$(table).append(tr);
				}
			$('#show').append(table);
			}
			
		});
	</script>
</head>
<body>
	<div align="center"><h3>List</h3></div>
	<div id="show" align="center">
	</div>
</body>
</html>