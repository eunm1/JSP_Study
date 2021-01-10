<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>�Խù� �󼼺���</h1>

��ȣ : ${myData2.id}
<hr>
���� : ${myData2.title}
<hr> 
���� : ${myData2.body}
<hr>
<a href="/JSP/article?action=showUpdate&id=${myData2.id}">����</a>
<a href="/JSP/article?action=delete&id=${myData2.id}">����</a>

<c:forEach items ="${replies}" var = "reply">
	<%-- ${ reply.nickname }<br>
	${ reply.body }<br>
	${ reply.regDate } 
	<c:if test="${ reply.mid == loginedMember.id }">
	<a href="#" >����</a> <a href="/JSP/article?action=doDeleteReply&id=${ reply.id }&aid=${myData2.id}" >����</a>
	</c:if> --%>
	
	<c:choose>
		<c:when test="${ flag == 'u' && rid == reply.id }">
			${ loginedMember.nickname }<br>
			<form action="/JSP/article">
				<input type="text" name="rbody" value="${reply.body}"/>
				<input type="hidden" name="rid" value="${reply.id}"/>
				<input type="hidden" name="aid" value="${myData2.id}" />
				<input type="hidden" name="action" value="doUpdateReply" />
				<input type="submit" value="���" />
			</form>
		</c:when>
		
		<a href="/JSP/article?action=doLikeCheck&id=${myData2.id}">���ƿ�</a> ${myData2.likeCnt}
		
		<c:otherwise>
			${ reply.writer }<br>
			${ reply.body }<br>
			${ reply.regDate } 
			<c:if test="${ reply.mid == loginedMember.id }">
			<a href="/JSP/article?action=showReplyUpdate&id=${ reply.id }&aid=${myData2.id}" >����</a> <a href="/JSP/article?action=doDeleteReply&id=${ reply.id }&aid=${myData2.id}" >����</a>
			</c:if>
		</c:otherwise>
	</c:choose>
	<hr>
</c:forEach>
${ loginedMember.nickname }<br>

<form action="/JSP/article">
	<input type="text" name="rbody" placeholder="����� ���ܺ�����"/>
	<input type="hidden" name="aid" value="${myData2.id}" />
	<input type="hidden" name="mid" value="${loginedMember.id}" />
	<input type="hidden" name="action" value="doInsertReply" />
	<input type="submit" value="���" />
</form>
<hr>
</body>
</html>