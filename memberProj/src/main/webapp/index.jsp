<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>데이터 입출력 구현</title>
	<style>
		h5:hover {
			color: gold;
			transition: color .6s;
		}
		.col {
			float: none;
			margin: 0 auto;
			padding: 15px;
		}
		.frmTd {
			padding: 5px 15px;
		}
		.btn {
		
		}
	</style>
	
	<!-- jQuery 라이브러리 -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<!-- 부트스트랩4 라이브러리 -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

	<script>
		$(document).ready(function(){
			// 회원목록 호출
			$.ajax({
				method: 'post',
<<<<<<< HEAD
				url: '/MemberList',
=======
				url: 'MemberList',
>>>>>>> refs/remotes/origin/main
				dataType: 'json',
				success: memberList,
				error: function(){
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
			
			// 회원목록 콜백함수
			let fields = ['id', 'name', 'phone', 'birth', 'address'];
			let table = $('<table />').attr('border', '1');
			function memberList(data){
				$(table).append($('<tr />').append('<th width="180">아이디</th><th width="80">이름</th><th width="150">연락처</th><th width="130">생년월일</th><th width="50">주소</th><th width="50">삭제</th>'));
				for(let i=0; i<data.length; i++){
					let tr = $('<tr />');
					for(let field of fields){
						let td = $('<td />').text(data[i][field]);
						$(tr).append(td);
					}
					let deltd = $('<td />');
					let del = $('<button />').text('삭제').attr('id', 'delete');
					$(deltd).append(del);
					$(tr).append(deltd);
				$(table).append(tr);
				}
			$('#show').append(table);
			
			trClick();
			}
			
			
			
			// 회원등록 호출
			$('#insert').on('click', function(event){
				event.preventDefault();
				console.log('submit');
				let s = $('#frm').serialize();
				console.log(s);
				
				// 폼전송처리
				$.ajax({
					method: 'post',
					url: 'MemberInsert',
					data: $('#frm').serialize(),
					dataType: 'json',
					success: memberInsert,
					error: function(){
						alert('error');
					}
				});	
			});
			
			// 회원등록 콜백함수
			function memberInsert(data){
				let tr = $('<tr />');
				for(let field of fields){
					let td = $('<td />').text(data[field]);
					$(tr).append(td);
				}
				let del = $('<button />').text('삭제').attr('id', 'delete');
				let deltd = $('<td />');
				$(deltd).append(del);
				$(tr).append(deltd);
				$(table).append(tr);
			}
			
			
			
			
			function trClick(){
				
				// 회원조회 호출
				$('#select').on('click', function(){
					console.log('submit');
					// 폼전송처리
					$.ajax({
						method: 'post',
						url: 'MemberSelect',
						data: {id: $('#id').val()},
						dataType: 'json',
						success: memberSelect,
						error: function(){
							alert('error');
						}
					});	
				});			
				
				// 회원조회 콜백함수
				function memberSelect(data){
					console.log(data);
					$('#id').val(data.id);
					$('#name').val(data.name);
					$('#phone').val(data.phone);
					$('#birth').val(data.birth);
					$('#address').val(data.address);			
				}
			}
			
			
			
			// 회원수정 호출
			$('#update').on('click', function(event){
				event.preventDefault();
				console.log('submit');
				let s = $('#frm').serialize();
				console.log(s);
				
				// 폼전송처리
				$.ajax({
					method: 'post',
					url: 'MemberUpdate',
					data: $('#frm').serialize(),
					dataType: 'json',
					success: function(){
						alert('수정 성공! 새로고침을 해야 합니다..');
					},
					error: function(){
						alert('error');
					}
				});	
			});		
			
			
			
			// 회원삭제 호출
			$(tr).on('click', function(){
				console.log('submit');
				// 폼전송처리
				$.ajax({
					method: 'post',
					url: 'MemberDelete',
					data: {id: $('#id').val()},
					success: memberDelete,
					error: function(){
						alert('error');
					}
				});	
			});			
			
			// 회원삭제 콜백함수
			function memberDelete(data){
				tr.remove();
				alert("삭제되었습니다!");
			}
			
		});
	</script>
</head>
<body>
	<div class="container" align="center">
		<div><h5>Ajax를 활용한 멤버관리</h5></div>
		<div class="row">
			<div class="col-lg-8 col">
				<form id="frm" method="post">
					<table>
						<tr>
							<td class="frmTd">아이디(이메일): </td>
							<td><input type="text" id="id" name="id"></td>
						</tr>
						<tr>
							<td class="frmTd">회원이름: </td>
							<td><input type="text" id="name" name="name"></td>
							<td rowspan="3"></td>
						</tr>
						<tr>
							<td class="frmTd">연락처: </td>
							<td><input type="text" id="phone" name="phone"></td>
						</tr>
						<tr>
							<td class="frmTd">생년월일: </td>
							<td><input type="date" id="birth" name="birth"></td>	
						</tr>	
						<tr>
							<td class="frmTd">주소: </td>
							<td><input type="text" id="address" name="address"></td>
						</tr>				
					</table>
				</form>
			</div>
		</div> <!-- end of row -->
		<div class="row">
			<div class="col-lg-5 col">	
				<div>
					<button id="insert" type="submit" class="btn">등록</button>&nbsp;
					<button id="select" type="submit" class="btn">조회</button>&nbsp;
					<button id="update" type="submit" class="btn">수정</button>&nbsp;
					<button id="delete" type="submit" class="btn">삭제</button>
				</div>
			</div>
		</div>	
		
		<div id="show">
		</div>
	
	</div> <!-- end of container -->
	
	
</body>
</html>