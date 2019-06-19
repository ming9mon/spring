<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새글등록</title>
<script src="resources/jQuery/jquery-3.4.1.min.js"></script>
</head>
<body>
	<h1>글 쓰기</h1>
	<hr>
	<form id="frm" name="frm" action="insertBoard.do" method="post">
		<table border="1">
			<tr>
				<td bgcolor="orange" width="70">제목</td>
				<td align="left">
					<input type="text" id="title" name="title" />
				</td>
			</tr>
			<tr>
				<td bgcolor="orange">작성자</td>
				<td align="left">
					<input type="text" id="writer" name="writer" size="10" />
				</td>
			</tr>
			<tr>
				<td bgcolor="orange">내용</td>
				<td align="left">
					<textarea id="content" name="content" cols="40" rows="10"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="file" name="uploadFile" id="uploadFile" multiple>
					<img id="blah" src="#" alt="your image" />
					<div>
					
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" name="write" id="write" >쓰기</button>
				</td>
			</tr>
		</table>
	</form>

	<hr>
	<a href="getBoardList.do">글 목록 가기</a>
</body>
<script type="text/javascript">
	$(document).ready(function (e){
		$('#write').click(function(){
				var frmArr = ["title","writer","content"];

				//입력 값 널 체크
				for(var i=0;i<frmArr.length;i++){
					//alert(arr[i]);
					if($.trim($('#'+frmArr[i]).val()) == ''){
						alert('빈 칸을 모두 입력해 주세요.');
						$('#'+frmArr[i]).focus();
						return false;
					}
				}

				//전송
				$('#frm').submit();
		});
		
		//파일 업로드
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize = 20971520;	//20MB
		
		function checkExtension(fileName,fileSize){
			
			if(fileSize >= maxSize){
				alert('파일 사이즈 초과');
				return false;
			}
			
			if(regex.test(fileName)){
				alert('해당 파일은 업로드가 불가능 합니다.');
				return false;
			}
			return true;
		}
		
		$("input[type='file']").change(function(e){
			//console.log(e);
			console.log($("input[type='file']")[0].files.length);
			console.log($("input[type='file']")[0]);
			   readURL(this);
			   
		});
		
		function readURL(input) {
			if (input.files && input.files[0]) { 
				var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
				reader.onload = function (e) { //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
					$('#blah').attr('src', e.target.result); //이미지 Tag의 SRC속성에 읽어들인 File내용을 지정 
					//(아래 코드에서 읽어들인 dataURL형식) 
				} 
			reader.readAsDataURL(input.files[0]); //File내용을 읽어 dataURL형식의 문자열로 저장 
			} 
		}//readURL()--

		
	});
</script>
</html>