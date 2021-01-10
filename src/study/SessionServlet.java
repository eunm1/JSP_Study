package study;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SesstionServlet
 */
@WebServlet("/session")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("flag");
		HttpSession session = request.getSession();		

		if(flag != null && flag.equals("a")) {
			String msg = (String)request.getAttribute("test");
			System.out.println(msg);
		} else if(flag != null && flag.equals("b")) {
			String msg = (String)session.getAttribute("test");
			System.out.println(msg);
		}

		request.setAttribute("test", "테스트 메시지~");
		session.setAttribute("test", "테스트 메시지~");

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/test.jsp");
		rd.forward(request, response);
	}


}
