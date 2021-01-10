package study;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.article.Article;
import board.article.ArticleDao;

@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleDao articleDao = new ArticleDao();
		ArrayList<Article> articles =  articleDao.getArticles();
		
		/*
		 1. response에게 printWriter열기
		 2. 인코딩 설정, ContentType 설정
		 3. printWirter로 html 게시판을 작성하기
		 4. 작성된 html에 데이터 바인딩하기
		 
		 //선수과정
		 1. webContent - web-inf - lib에 mysql conntector jar파일 추가
		 2. webContent에 html 생성
		 3. src - 패키지 - servlet파일 생성
		 */
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<table border=\"1\">");
		out.println("<tr>");
		out.println("<td>");
		out.println("제목");
		out.println("</td>");
		out.println("<td>");
	    out.println("작성자");
	    out.println("</td>");
	    out.println("<td>");
	    out.println("작성일");
	    out.println("</td>");
	    out.println("</tr>");
	    
	    for(int i = 0; i < articles.size(); i++) {	    	
	    	out.println("<tr>");
	    	out.println("<td>" + articles.get(i).getTitle() + "</td>");
	    	out.println("<td>" + articles.get(i).getNickname() + "</td>");
	    	out.println("<td>" + articles.get(i).getRegDate() + "</td>");
	    	out.println("</tr>");
	    }
	    
	    
	    out.println("</table>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
