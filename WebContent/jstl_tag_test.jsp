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
	<!-- prefix�� forEach�� �տ� �ٿ��� �±׸� �ν��Ѵ� -->
		<div>hi</div>
	</c:forEach>
	
	<!-- ���� ���� -->
	<% 
		int num = 21;
	%>
	
	<c:set var="num2" value="11" />
	
	<!-- ���� ��� -->
	<%
		out.println("jsp : " + num);
	%>
	<br>
	
	<c:out value="jstl : ${num2}" />
	<br>
	
	<!-- ���ǹ� -->
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
	<!-- �ݺ��� -->
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
	<h1>jstl ���� ����</h1>
	<h3>����1 - ���δ��</h3>
	
	<c:set var="age" value="27" />
	
	<c:choose>
		<c:when test="${age <= 19}">
			���δ��
		</c:when>
		<c:when test="${age >= 60}">
			���δ��
		</c:when>
		<c:otherwise>
			���δ��X
		</c:otherwise>
	</c:choose>
	<hr>
	<h3>����2 - 1~100�� ¦��</h3>
	<c:forEach var="i" begin = "1" end="100" step="1">
		<c:if test="${i % 2 == 0}">
			${i}
		</c:if>
	</c:forEach>
	<hr>
	
	<h3>����3 - ���뱸����</h3>
	<!-- Ȧ�� * ¦�� -->
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
		
	<h3>����4 - �ﰢ�� �׸���</h3>
	<c:set var="y" value="5"/>
	
	<c:forEach var="i" begin="1" end="${y}">
		<c:forEach var="j" begin="1" end="${i}">
			*
		</c:forEach>
		<br>
	</c:forEach>
	<br>
	<hr>
	<h3>���� for�� ����</h3>
	<%
		ArrayList<String> strList = new ArrayList<String>();
		
		strList.add("�ȳ�");
		strList.add("�ݰ���");
		strList.add("�߰�");
		
		out.println("jsp �Ϲ� for�� : ");
		for(int i = 0; i < strList.size(); i++) {
			String str = strList.get(i);
			out.println(str);
		}
		out.println("<br>");
		out.println("jsp ���� for�� : ");
		for(String str : strList) {
			out.println(str);
		}
		out.println("<br>");
	
	%>
	<!-- jstl�� �̿��ؼ� arrayList����� ���� �Ʒ��� ���� -->
	<jsp:useBean id="strList2" class="java.util.ArrayList"/>
	<c:set var="noUse" value="${strList2.add('�ȳ�')}"/>
	<c:set var="noUse" value="${strList2.add('�ݰ���')}"/>
	<c:set var="noUse" value="${strList2.add('�߰�')}"/>
	
	jstl �Ϲ� for�� : 
	<c:forEach var="i" begin="0" end="${strList2.size() - 1}">
		${strList2.get(i)}
	</c:forEach>
	<br>
	
	jstl ���� for�� :
	<c:forEach var="str" items="${strList2}">
		${str}
	</c:forEach>
</body>
</html>