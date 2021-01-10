package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/")
public class FrontController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 공통 작업
		System.out.println("== 공통 작업 코드 ==");

		response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=utf-8"); 	

	    PrintWriter out = response.getWriter();


	    // 요청 url 가져오기
	    String uri = request.getRequestURI();
	    String[] uris = uri.split("/");

//	    for(int i = 0; i < uris.length; i++) {
//	    	System.out.println(uris[i]);
//	    }

	    String module = uris[2];
	    String dest = "";

	    if(module.equals("article")) {	    	
	    	ArticleController controller = new ArticleController();
	    	dest = controller.doAction(request, response);
	    } 
	    else if(module.equals("member")) {
	    	MemberController controller = new MemberController();
	    	dest = controller.doAction(request, response);
	    }

	    if(dest.startsWith("redirect: ")) {
			// 리다이렉팅
			String[] bits = dest.split(" ");
			String url = bits[1];
			response.sendRedirect(url);

		} else {
			// 포워딩
			RequestDispatcher rd = request.getRequestDispatcher(dest);
			rd.forward(request, response);				
		}
	}

}
