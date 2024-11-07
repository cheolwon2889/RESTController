<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" pageEncoding="UTF-8"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Hello world!</h1>
	<!-- JQuery CDN 추가  -->
	<script src="https://code.jquery.com/jquery-3.7.1.js"
		integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
		crossorigin="anonymous"></script>
	<script type="text/javascript">
		$(document).ready( function() {
			// 버튼을 클릭했을때, 비동기방식으로 RestController 호출
			// 결과를 받아와서 div에 정보 출력
			
			// 버튼을 클릭했을때
			$('#btnSend').click(function(){
				// 비동기방식으로 RestController 호출
// 				$.ajax({
// 					url : "${contextPath}/rest2/test1",
// 					success : function (data) {
// 						alert(" RESTController 다녀옴. ");
// 						alert(data);
// 					}
// 				});
				
				var boardData = {
					"bno" : 1000,
					"title" : "test1",
					"content" : "testContent1",
					"writer" : "ITWLL푸싼",
					
				};
				
				$.ajax({
					url : "${contextPath}/rest2/test2",
					type: "POST",
					//data : { "bno" : 1000 },
					//data : JSON.stringify(boardData),
				    // => 데이터 형태를 JSON데이터 구조로 변경(String)
					data : JSON.stringify(boardData),
// 					contentType : 전달하는 데이터의 타입설정,
// 					contentType : application/x-www-form/urlencoded; charset=UTF-8(디폴트),
					contentType : 'application/json;,
					success : function() {
						alert(" 성공 : RESTController 다녀옴 ");
					},
					error : function() {
						alert(" 실패!!");
					}
				})
				
				
				
				
			});// click
			
			
		});
		
	
	</script>
	<P>The time on the server is ${serverTime}.</P>

	<input type="button" id="btnSend" value="회원정보처리">

	<hr>
	<h2>결과</h2>
	<div id="result"></div>



</body>
</html>
