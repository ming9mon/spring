<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	<script src="resources/jQuery/jquery-3.4.1.min.js"></script>
</head>
<body>
	<h1>로그인 페이지</h1>
	<hr />
		<%-- <c:choose>
		<!-- 로그인이 안되어 있으면 -->
			<c:when test="${sessionScope.userId == null}"> --%>
				<form id="loginFrm" name="loginFrm">
					<table>
						<tr>
							<td>아이디</td>
							<td><input type="text" name="userId" id="userId" placeholder="10글자" maxlength="10"></td>
						</tr>
						<tr>
							<td>패스워드</td>
							<td><input type="password" name="passwd" id="passwd" maxlength="20"></td>
						</tr>
						<tr>
							<td colspan=2>
								<input type="button" id="aa" value="로그인" />
							</td>
						</tr>
					</table>
				</form>
			<%-- </c:when>
			<c:otherwise>
				<h3>${sessionScope.userId}님 환영합니다.</h3>
				<button id="logout">로그아웃</button>
			</c:otherwise>
		</c:choose> --%>
</body>
<script type="text/javascript">
	$(document).ready(function(e){
		$('#aa').click(function(){

			// 입력 값 체크
			if($.trim($('#userId').val()) == ''){
				alert("아이디를 입력해 주세요.");
				$('#userId').focus();
				return;
			}else if($.trim($('#passwd').val()) == ''){
				alert("패스워드를 입력해 주세요.");
				$('#passwd').focus();
				return;
			}
			//login Check
			$.ajax({
				url: "${pageContext.request.contextPath}/loginCheck.do",
				type: "POST",
				dataType: "json",
				data: $('#loginFrm').serializeArray(),
				success: function(data){
					console.log(data);
					if(data==1){
						
					}else{
						alert("아이디 또는 패스워드가 틀렸습니다.");
					}
				},
				error:function(){
					alert("서버 에러");
				}
			});
			
		});
	});
</script>
</html>