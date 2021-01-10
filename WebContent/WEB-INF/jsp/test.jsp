<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>session 테스트</h1>

  <h3>${ test }</h3>

	<a href="/JSP/session?flag=a">테스트1</a>
	<a href="/JSP/session?flag=b">테스트2</a>
	
	<form action="TestController">
		<input type="text" name ="text" placeholder="텍스트를 입력해주세요."/>

		<!-- select : 목록 박스 -->
		<select name="select">
			<option value="1">선택지1</option>
			<option value="2">선택지2</option>
			<option value="3">선택지3</option>	
		</select>
		<input type="checkbox" name="chkbox" value="true"/>
		<input type="hidden" name="action" value="doTest" />
		<input type="submit" value="보내기" />
	 </form>
	
</body>
</html> 