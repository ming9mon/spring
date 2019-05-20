<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="resources/jQuery/jquery-3.4.1.min.js"></script>
</head>
<body>
	<h1>회원가입</h1>
	<hr>
	<form id="signFrm" name="signFrm" action="signUp.do">
		<table>
			<tbody>
				<tr>
					<td>아이디</td>
					<td><input type="text" id="userId" name="userId"></td>
					<td><input type="button" id="check" value="중복체크"></td>
				</tr>
				<tr>
					<td colspan=3 id="idCheck"></td>
				</tr>
				<tr>
					<td>패스워드</td>
					<td colspan="2"><input id="passwd" name="passwd" type="password"></td>
				</tr>
				<tr>
					<td colspan="3"><input type="button" id="signUp" value="회원가입"></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>

<script type="text/javascript">
	$(document).ready(function(e){
		
		var idx = false;
		
		$('#signUp').click(function(){
			if($.trim($('#userId').val()) == ''){
				alert("아이디 입력.");
				$('#userId').focus();
				return;
			}else if($.trim($('#passwd').val()) == ''){
				alert("패스워드 입력.");
				$('#passwd').focus();
				return;
			}
			
			if(idx==false){
				alert("중복체크를 해주세요.");
				return;
			}else{
				$('#signFrm').submit();
			}
		});
		
		$('#check').click(function(){
			$.ajax({
				url: "${pageContext.request.contextPath}/idCheck.do",
				type: "POST",
				data:{
					"userId":$('#userId').val()
				},
				success: function(data){
					alert(data);
				},
				error: function(){
					alert("서버에러");
				}
			});
			

			var html="<tr><td colspan='3'>사용불가능한 아이디 입니다.</td></tr>";
			$('#idCheck').appendTo(html);
		});
		
	});
</script>

</html>