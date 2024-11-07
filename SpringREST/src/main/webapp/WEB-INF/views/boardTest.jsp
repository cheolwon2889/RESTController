<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.7.1.js"
		integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
		crossorigin="anonymous"></script>
		
		<script type="text/javascript">
		$(function() {
			$("#btnCreate").click(function(){
				var boardData = {
					"bno" : 100,
					"title" : $("#title").val(),
					"writer" : $("#content").val(),
					"content" : $("#writer").val()
				}
				$.ajax({
					url : "${contextPath}/boards",
					data : JSON.stringify(boardData), 
// 					data: boardData,
					contentType : "application/json",
					type : "POST" ,
					success : function(data) {
						alert("성공!!");	
						$('body').append(data);
						if(data == 'ADD_Success') {
							alert("글쓰기 성공!");
							$("#title").val(""),
							$("#content").val(""),
							$("#writer").val("")
						}
					}
				})
				
			});
			
			
		});
		</script>
 <h2> 게시판 CRUD - REST 방식으로 </h2>
 
 <h2> 게시판 글쓰기 </h2>
 
 <form action="">
 	제목 : <input type="text" name="title" id="title"> <br>
 	작성자 : <input type="text" name="writer"  id="writer"> <br>
 	내용 : <input type="text" name="content"  id="content"> <br>
 	
 	<input type="button" id="btnCreate" value="글쓰기(Create)">
 
 </form>
 
 
 
</body>
</html>