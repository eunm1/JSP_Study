package study;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.article.Article;
import board.article.ArticleDao;

@WebServlet("/article2")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		ArticleDao dao = new ArticleDao();
		ArrayList<Article> articles = dao.getArticles();
		
		String action = request.getParameter("action");
		
		/*
		 * // jsp에 articles 넘기기 // 1. request 객체에 데이터 저장 request.setAttribute("myData",
		 * articles);
		 * 
		 * // 2. 위에서 저장한 request 객체를 이용해 새로운 jsp 요청 -> 목적지 jsp 필요 String dest = "";
		 * if(action.equals("list1")) { dest = "/list.jsp"; } else { dest =
		 * "/list2.jsp"; }
		 */
		
		
		String dest = "WEB-INF/jsp/list.jsp";
		
		if(action.equals("list")) {
			//http://localhost:8090/JSP/article?action=list
			// 1. request 객체에 데이터 저장
			request.setAttribute("myData", articles);
			
			// 2. 위에서 저장한 request 객체를 이용해 새로운 jsp 요청 -> 목적지 jsp 필요
				
		} else if(action.equals("insert")) {
			//http://localhost:8090/JSP/article?action=insert&title=제목&body=내용&mid=1
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			int mid = Integer.parseInt(request.getParameter("mid"));
			
			dao.insertArticle(title, body, mid);
			
		} else if(action.equals("update")) {
			//http://localhost:8090/JSP/article?action=update&id=1title=새제목&body=새내용
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			int id = Integer.parseInt(request.getParameter("id"));
			
			dao.updateArticle(title, body, id);
			
		} else if(action.equals("delete")) {
			//http://localhost:8090/JSP/article?action=delete&id=1
			int id = Integer.parseInt(request.getParameter("id"));
			dao.deleteArticle(id);
			
		} else if(action.equals("detail")) {
			//상세보기 화면 만들기 - detail.jsp
			//http://localhost:9000/web-exam1/article?action=detail?id=6
			int id = Integer.parseInt(request.getParameter("id"));
			Article article = dao.getArticleById(id);			
			request.setAttribute("myData2", article);
			dest = "WEB-INF/jsp/detail.jsp";
		}
		// 3. 요청하기
		RequestDispatcher rd = request.getRequestDispatcher(dest);
		rd.forward(request, response);	
	}



}
