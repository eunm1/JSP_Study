package study;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.article.Article;
import board.article.ArticleDao;
import board.member.Member;
import board.member.MemberDao;

/**
 * Servlet implementation class FormServlet
 */
@WebServlet("/article3")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
response.setCharacterEncoding("UTF-8");
		

		ArticleDao dao = new ArticleDao();
		MemberDao mdao = new MemberDao();
		
		ArrayList<Article> articles = dao.getArticles();
		
		String action = request.getParameter("action");
	
		
		String dest = "WEB-INF/jsp/list.jsp";
		
		if(action.equals("list")) {
			
			// 1. request 객체에 데이터 저장
			request.setAttribute("myData", articles);
			
			// 2. 위에서 저장한 request 객체를 이용해 새로운 jsp 요청 -> 목적지 jsp 필요		
		} else if(action.equals("insert")) {
			
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			int mid = Integer.parseInt(request.getParameter("mid"));
			
			Member loginedMember = mdao.getMemberById(mid);
			request.setAttribute("loginedMember", loginedMember);
			
			dao.insertArticle(title, body, mid);
			
		} else if(action.equals("update")) {
			
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			int id = Integer.parseInt(request.getParameter("id"));
			
			dao.updateArticle(title, body, id);
			
		} else if(action.equals("delete")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			dao.deleteArticle(id);
			
		} else if(action.equals("detail")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Article article = dao.getArticleById(id);			
			request.setAttribute("myData2", article);
			dest = "WEB-INF/jsp/detail.jsp";
		} else if(action.equals("showAdd")) {
			/*
			 * int mid = Integer.parseInt(request.getParameter("mid")); Member loginedMember
			 * = mdao.getMemberById(mid);
			 * 
			 * System.out.println("====" + loginedMember.getId()); System.out.println("===="
			 * + loginedMember.getNickname()); request.setAttribute("loginedMember",
			 * loginedMember);
			 */
			
			dest = "WEB-INF/jsp/addForm.jsp";
			
		} else if(action.equals("showUpdate")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			Article article = dao.getArticleById(id);
			request.setAttribute("myData3", article);
			
			dest = "WEB-INF/jsp/updateForm.jsp";
			
		} else if(action.equals("showLogin")) {
			dest = "WEB-INF/jsp/loginForm.jsp";
		} else if(action.equals("doLogin")) {
			
			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");
			Member loginedMember = mdao.getMemberByLoginIdAndLoginPw(loginId, loginPw);
			
			if(loginedMember != null) {
				
				//request.setAttribute("loginedMember", loginedMember);
				//세선 저장소 저장하는 법
				HttpSession session = request.getSession();
				session.setAttribute("loginedMember", loginedMember);
				
				dest = "WEB-INF/jsp/list.jsp";
			} else {
				dest = "WEB-INF/jsp/loginFailed.jsp";
			}
			
		} else if(action.equals("showMember")) {
			dest = "WEB-INF/jsp/memberForm.jsp";
			
		} else if(action.equals("doInsertMember")) {
			
			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");
			String nickname = request.getParameter("nickname");
			
			mdao.insertMember(loginId, loginPw, nickname);
			
			dest = "WEB-INF/jsp/loginForm.jsp";
		} 
		
		request.setAttribute("myData", dao.getArticles());
		
		// 3. 요청하기
		RequestDispatcher rd = request.getRequestDispatcher(dest);
		rd.forward(request, response);	
		
	}



}
