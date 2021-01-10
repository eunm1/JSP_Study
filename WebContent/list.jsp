<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="board.article.ArticleDao" %>
<%@ page import="board.article.Article" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시물 목록</h1>
	
	<%-- <%  
	ArrayList<Article> articles = (ArrayList<Article>)request.getAttribute("myData");
	%>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
		<% for(int i = 0; i < articles.size(); i++) { %>
		<tr>
			<td><%= articles.get(i).getId() %></td>
			<td><%= articles.get(i).getTitle() %></td>
			<td><%= articles.get(i).getNickname() %></td>
			<td><%= articles.get(i).getRegDate() %></td>
			<td><%= articles.get(i).getHit() %></td>
		</tr>
		<% } %>
	</table> --%>
	
	<h1>게시물 목록</h1>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
		 
		
		<c:forEach var="article" items="${myData}">
		<tr>
			<td>${article.id}</td>
			<td>${article.title}</td>
			<td>${article.nickname}</td>
			<td>${article.regDate}</td>
			<td>${article.hit}</td>
		</tr>
		</c:forEach>
						

	</table>
	<a href="http://localhost:9000/web-exam1/TestServlet">링크1</a>
	내용1
</body>
</html>