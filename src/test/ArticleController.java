package test;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.Pagination;
import board.article.Article;
import board.article.ArticleDao;
import board.article.Like;
import board.article.Reply;
import board.member.Member;

public class ArticleController {
	ArticleDao dao = new ArticleDao();

	String doAction(HttpServletRequest request, HttpServletResponse response) {

		String action = request.getParameter("action");
		String dest = "";

		if(action.equals("list")) {
			//out.println("<h1>안녕하세요</h1>");
			dest = list(request, response);

		} else if(action.equals("insert")) {
			dest = insert(request, response);

		} else if(action.equals("update")) {
			dest = update(request, response);

		} else if(action.equals("delete")) {
			dest = delete(request, response);

		} else if(action.equals("detail")) {
			dest = detail(request, response);

		} else if(action.equals("showAdd")) {
			dest = "WEB-INF/jsp/addForm.jsp";

		} else if(action.equals("showUpdate")) {
			
			dest = showUpdate(request, response);
			
		} else if (action.equals("doDeleteReply")) {
			
			dest = deleteReply(request, response);
			
		} else if (action.equals("doInsertReply")) {
			
			dest = insertReply(request, response);
			
		} else if(action.equals("showReplyUpdate")) {
			
			dest = showReplyUpdate(request, response);

		} else if(action.equals("doUpdateReply")) {
			
			dest = updateReply(request, response);
			
		}else if(action.equals("doSearch")) {
			
			dest = doSearch(request, response);
			
		}else if(action.equals("doLikeCheck")){
				dest = dolikecheck(request, response);
		}
		return dest;
	}
	
	private String dolikecheck(HttpServletRequest request, HttpServletResponse response) {
		int aid = Integer.parseInt(request.getParameter("id"));
		
		HttpSession session = request.getSession();
		
		//Member member = (Member)session.getAttribute("loginedMember");
		int mid = ((Member)session.getAttribute("loginedMember")).getId();

		Like like = dao.getLike(aid,mid);

		if(like == null){
			dao.insertLike(aid, mid);
		}else{
			dao.deleteLike(aid, mid);
		}
		return "redirect: /JSP/article?action=detail&id=" + aid;
	}

	private String doSearch(HttpServletRequest request, HttpServletResponse response) {

		String dateInterval = request.getParameter("dateInterval");
		String sTarget = request.getParameter("sTarget");
		String keyword = request.getParameter("keyword");

		ArrayList<Article> searchedArticles =dao.searchArticle(dateInterval, sTarget, keyword);
		request.setAttribute("myData", searchedArticles);

		return "WEB-INF/jsp/list.jsp";
	}
	
	private String updateReply(HttpServletRequest request, HttpServletResponse response) {

		int aid = Integer.parseInt(request.getParameter("aid"));
		int rid = Integer.parseInt(request.getParameter("rid"));
		String body = request.getParameter("rbody");

		dao.updateReply(body, rid);

		return "redirect: /JSP/article?action=detail&id=" + aid;
	}


	private String showReplyUpdate(HttpServletRequest request, HttpServletResponse response) {

		int aid = Integer.parseInt(request.getParameter("aid"));
		int id = Integer.parseInt(request.getParameter("id"));

		return "redirect: /JSP/article?action=detail&id=" + aid + "&flag=u&rid=" + id;
	}


	private String insertReply(HttpServletRequest request, HttpServletResponse response) {

		int aid = Integer.parseInt(request.getParameter("aid"));
		int mid = Integer.parseInt(request.getParameter("mid"));
		String body = request.getParameter("rbody");

		dao.insertReply(aid, body, mid);


		return "redirect: /JSP/article?action=detail&id=" + aid;
	}


	private String deleteReply(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("id"));
		int aid = Integer.parseInt(request.getParameter("aid"));
		dao.deleteReplyById(id);

		return "redirect: /JSP/article?action=detail&id=" + aid;
	}

	
	private String showUpdate(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Article article = dao.getArticleById(id);
		request.setAttribute("myData3", article);

		return "WEB-INF/jsp/updateForm.jsp";
	}

	private String detail(HttpServletRequest request, HttpServletResponse response) {

		int id = Integer.parseInt(request.getParameter("id"));
		
		String flag = request.getParameter("flag");

		Article article = dao.getArticleById(id);
		ArrayList<Reply> replies = dao.getRepliesByArticleId(id);

		if(flag != null) {
			int rid = Integer.parseInt(request.getParameter("rid"));

			request.setAttribute("flag", flag);
			request.setAttribute("rid", rid);
		}

		
		request.setAttribute("myData2", article);
		request.setAttribute("replies", replies);

		return "WEB-INF/jsp/detail.jsp";
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		dao.deleteArticle(id);

		return "WEB-INF/jsp/list.jsp";
	}

	private String update(HttpServletRequest request, HttpServletResponse response) {

		String title = request.getParameter("title");
		String body = request.getParameter("body");
		int id = Integer.parseInt(request.getParameter("id"));

		dao.updateArticle(title, body, id);

		return detail(request, response);
	}

	private String insert(HttpServletRequest request, HttpServletResponse response) {

		String title = request.getParameter("title");
		String body = request.getParameter("body");
		//int mid = Integer.parseInt(request.getParameter("mid"));

		dao.insertArticle(title, body, 1);
		return list(request, response);
		//response.sendRedirect("/web-exam1/article?action=list");
	}

	public String list(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * ArrayList<Article> articles = dao.getArticles();
		 * request.setAttribute("myData", articles);
		 */
		ArrayList<Article> articles = dao.getArticles();
		Pagination page = new Pagination(articles.size());
		
		int pageNo = 1;
		if( null != request.getParameter("page")) {
			pageNo = Integer.parseInt(request.getParameter("page"));					
		}
		page.setCurrentPageNo(pageNo);
		page.setCurrentPageBlockNo(pageNo);
		
		ArrayList<Article> articlesPerPage = dao.getArticlesForPaging(page);

		request.setAttribute("myData", articlesPerPage);
		request.setAttribute("pagination", page);
		
		return "WEB-INF/jsp/list.jsp";
	}
	
}
