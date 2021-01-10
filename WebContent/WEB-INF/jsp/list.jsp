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
<c:if test="${loginedMember != null}">
${loginedMember.nickname}님 반갑습니다!
<br>
<a href="/JSP/member?action=doLogout">로그아웃</a>
</c:if>
<c:if test="${loginedMember == null}">
<a href="/JSP/member?action=showLogin">로그인</a>
<a href="/JSP/member?action=showMember">회원가입</a>
</c:if>
<body>	
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
			<%-- <td><a href="/JSP/article?action=detail&id=${article.id}">${article.title}_${article.id}</a></td> --%>
			<td>
				<c:choose>
					<c:when test="${loginedMember == null}">
						<a href="/JSP/member?action=showLogin">${article.title}_${article.id}</a>
					</c:when>
					<c:otherwise>
						<a href="/JSP/article?action=detail&id=${article.id}">${article.title}_${article.id}</a>
					</c:otherwise>
				</c:choose>
			</td>
			<td>${article.nickname}</td>
			<td>${article.regDate}</td>
			<td>${article.hit}</td>
		</tr>
		</c:forEach>
						

	</table>
	<!-- <a href="/JSP/article?action=showAdd">글쓰기</a> -->
	<%-- <a href="/JSP/article?action=showAdd&mid=${ loginedMember.id }">글쓰기</a> --%>
	<a href="/JSP/article?action=showAdd">글쓰기</a>
	<br>
	<c:if test="${pagination.currentPageNo != 1}">
	<a href="/JSP/article?action=list&page=${pagination.currentPageNo -1}">이전</a>
	</c:if>
	
	<c:forEach begin="${pagination.startPageNoInCurrentBlock}"
	 end="${pagination.endPageNoInCurrentBlock}" var="i">
		<a href="/JSP/article?action=list&page=${i}">${i}</a>
	</c:forEach>
	
	<c:if test="${pagination.currentPageNo != pagination.lastPageNo}">
	<a href="/JSP/article?action=list&page=${pagination.currentPageNo + 1}">다음</a>
	</c:if>
	
	<form action="/JSP/article">
		<select name="dateInterval">
			<option value="all">전체기간</option>
			<option value="-1 day">1일</option>
			<option value="-1 week">1주</option>
			<option value="-1 month">1개월</option>
			<option value="-6 month">6개월</option>
			<option value="-1 year">1년</option>
		</select>

		<select name="sTarget">
			<option value="title&body">제목+내용</option>
			<option value="title">제목만</option>
			<option value="nickname">글작성자</option>
			<option value="rbody">댓글내용</option>
			<option value="rnickname">댓글작성자</option>
		</select>

		<input type="text" name="keyword"/>
		<input type="hidden" name="action" value="doSearch"/>
		<input type="submit" value="검색"/>
	</form>
	
</body>
</html>