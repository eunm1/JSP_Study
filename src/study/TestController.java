package study;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestController
 */
@WebServlet("/TestController")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if(action != null && action.equals("doTest")) {
			String text = request.getParameter("text");
			String select = request.getParameter("select");
			String chkbox = request.getParameter("chkbox");

			System.out.println(text);
			System.out.println(select);
			System.out.println(chkbox);
		}
		
		//request.setAttribute("test", "테스트 메시지~");

		//RequestDispatcher rd = request.getRequestDispatcher("/test");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/test.jsp");
		rd.forward(request, response);

	}


}
