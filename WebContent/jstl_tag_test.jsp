<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ page import="java.util.ArrayList" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="i" begin="1" end="5"> 
	<!-- prefix를 forEach문 앞에 붙여야 태그를 인식한다 -->
		<div>hi</div>
	</c:forEach>
	
	<!-- 변수 선언 -->
	<% 
		int num = 21;
	%>
	
	<c:set var="num2" value="11" />
	
	<!-- 변수 출력 -->
	<%
		out.println("jsp : " + num);
	%>
	<br>
	
	<c:out value="jstl : ${num2}" />
	<br>
	
	<!-- 조건문 -->
	<%
		if(num % 2 == 0) {
			out.println("even");
		} 
		if(num % 2 == 1){
			out.println("odd");
		}
		
		if(num % 2 == 0) {
			out.println("even");
		} else {
			out.println("odd");
		}
	%>
	<c:if test ="${num2 % 2 == 0}">
		even
	</c:if>
	<c:if test ="${num2 % 2 == 1}">
		even
	</c:if>
	<!--if, else if, else -->
	<c:choose>
		<c:when test="${num2 % 2 == 0}">
			even
		</c:when>
		<c:otherwise>
			odd
		</c:otherwise>
	</c:choose>
	<br>
	<!-- 반복문 -->
	<%
		for(int i = 1; i <= 10; i++) {
			out.println(i + " ");
		}
	%>
	<br>
	<c:forEach var="i" begin="1" end="10" step="1">
		${i}
	</c:forEach>
	
	
	<br>
	<h1>jstl 연습 문제</h1>
	<h3>문제1 - 할인대상</h3>
	
	<c:set var="age" value="27" />
	
	<c:choose>
		<c:when test="${age <= 19}">
			할인대상
		</c:when>
		<c:when test="${age >= 60}">
			할인대상
		</c:when>
		<c:otherwise>
			할인대상X
		</c:otherwise>
	</c:choose>
	<hr>
	<h3>문제2 - 1~100중 짝수</h3>
	<c:forEach var="i" begin = "1" end="100" step="1">
		<c:if test="${i % 2 == 0}">
			${i}
		</c:if>
	</c:forEach>
	<hr>
	
	<h3>문제3 - 응용구구단</h3>
	<!-- 홀수 * 짝수 -->
	<c:set var="n" value="4"/>
	<c:set var="m" value="19"/>
	<c:set var="limit" value="11"/>
	
		<c:forEach var="dan" begin="${n}" end="${m}">
			<c:if test="${dan % 2 == 1}">
				<c:forEach var="i" begin="1" end="${limit}">
					<c:if test="${ i % 2 == 0 }">
						${dan} * ${i} = ${dan * i}<br> 
					</c:if>
				</c:forEach>
			</c:if>
		</c:forEach>
		
	<h3>문제4 - 삼각형 그리기</h3>
	<c:set var="y" value="5"/>
	
	<c:forEach var="i" begin="1" end="${y}">
		<c:forEach var="j" begin="1" end="${i}">
			*
		</c:forEach>
		<br>
	</c:forEach>
	<br>
	<hr>
	<h3>향상된 for문 사용법</h3>
	<%
		ArrayList<String> strList = new ArrayList<String>();
		
		strList.add("안녕");
		strList.add("반가워");
		strList.add("잘가");
		
		out.println("jsp 일반 for문 : ");
		for(int i = 0; i < strList.size(); i++) {
			String str = strList.get(i);
			out.println(str);
		}
		out.println("<br>");
		out.println("jsp 향상된 for문 : ");
		for(String str : strList) {
			out.println(str);
		}
		out.println("<br>");
	
	%>
	<!-- jstl을 이용해서 arrayList만드는 법은 아래와 같다 -->
	<jsp:useBean id="strList2" class="java.util.ArrayList"/>
	<c:set var="noUse" value="${strList2.add('안녕')}"/>
	<c:set var="noUse" value="${strList2.add('반가워')}"/>
	<c:set var="noUse" value="${strList2.add('잘가')}"/>
	
	jstl 일반 for문 : 
	<c:forEach var="i" begin="0" end="${strList2.size() - 1}">
		${strList2.get(i)}
	</c:forEach>
	<br>
	
	jstl 향상된 for문 :
	<c:forEach var="str" items="${strList2}">
		${str}
	</c:forEach>
</body>
</html>