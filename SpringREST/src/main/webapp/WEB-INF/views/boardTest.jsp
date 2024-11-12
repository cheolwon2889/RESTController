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
 <hr>
 <h2> 게시판 목록(List)</h2>
 
 <input type="button" value="게시판 조회" id="btnRead">
 
 <div id="divRead"></div>


<script>
	$(function() {
		$("#btnRead").click(function() {
			// alert("리스트");
			// 정보를 REST 방식으로 호출
			// 글조회 : /boards/all GET 방식(ALL)
			// => 결과를 divRead에 저장
			
			$.ajax({
				url :"/boards/all",
				type : "GET",
				success : function(data) {
					console.log(data);
					
					$(data).each(function(idx, item) {
						console.log(idx);
						console.log(item);
						
						$("#divRead").append(function() {
							return "<span>"+item.bno+  " // "+item.title +"</span> <br>"
						});
					});
				},
				
			});
		});
		
	});
</script> 

	<hr>
	<h2> 게시판 특정 글 조회 (READ)</h2>
	
	<input type="text" name="bno" id="bno">
	<input type="button" value="게시판 특정 글조회" id="btnReadBno">
	<div id="divReadBno"></div>
	
	<!-- bno=1 인 게시판 글의 정보를 조회  -->
	
	<script>
		$(function() {
			$("#btnReadBno").click(function(){
				var bno = $("#bno").val();
				
				$.ajax({
					url : "/boards/"+bno,
					type : "GET",
					success : function(data) {
						alert(data);
						console.log(data);
						$("#divReadBno").append(function() {
							return "<span>" +data.bno +  " // "+ data.title+	 "</span>";
						});
						
					}
				});
			});
			
			
		});
	
	</script>
	
	
<hr>
<h2> 게시판 글 수정</h2>
<form action="">
 	제목 : <input type="text" name="title" id="utitle"> <br>
 	작성자 : <input type="text" name="writer"  id="uwriter"> <br>
 	내용 : <input type="text" name="content"  id="ucontent"> <br>
 	
 	<input type="button" id="btnUpdate" value="글 수정(Update)">
 
 </form>
 
 <script type="text/javascript">
 	$(function () {
		$("#btnUpdate").click(function() {
			var updateData = {
					"bno" : 1,
					"title" : $("#utitle").val(),
					"writer" : $("#ucontent").val(),
					"content" : $("#uwriter").val()
			};
			console.log(updateData);
			
			$.ajax({
				url : "/boards/1",
				type : "PUT",
				contentType : "application/json",
				data : JSON.stringify(updateData),
				success : function(data) {
					console.log(data);
				}
			})
		});
 	});
 
 </script>
	
	<hr>
<h2> 게시판 글 삭제</h2>
<input type="text" name="bno" id="dbno">
<input type="button" value="게시판 특정 글 삭제" id="btnDeleteBno">
 
 <script type="text/javascript">
 	$(function () {
		$("#btnDeleteBno").click(function() {
			var bno = $("#dbno").val();
			$.ajax({
				url : "/boards/"+bno,
				type : "DELETE",
				success : function(data) {
					console.log(data);
				}
			})
		});
 	});
 
 </script>
	
	
	
	
	
	
	
	
	
	
	


 
 
</body>
</html>