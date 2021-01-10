package study;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// localhost:8090/JSP/TestServlet
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public TestServlet() {
        // TODO Auto-generated constructor stub
    } 
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		String loginId = request.getParameter("loginId"); //localhost:8090/JSP/TestServlet?loginId=aaa
		System.out.print(loginId);
		out.println("<h1 style = 'color : blue' >" + loginId+"�� �ȳ��ϼ���.</h1>");
		
		String loginPw = request.getParameter("loginPw");//localhost:8090/JSP/TestServlet?loginId=aaa&loginPw=1234
		System.out.print(loginPw);
		out.println("<h1 style = 'color : blue' > loginPw = " + loginPw+"</h1>");
		
		int limit = 9;
		String gugu1 = request.getParameter("action");
		String limit3 = request.getParameter("limit");
		if(limit3 != null)
			limit = Integer.parseInt(limit3);
		
		if(gugu1.equals("printGuGu1")) {
			printGuGu(limit, out);
		}else if(gugu1.equals("printGuGu2")) {
			printGuGu(limit, out);
		}else if(gugu1.equals("hello")) {
			String name = request.getParameter("name");
			String age = request.getParameter("age");
			
			out.print("�ȳ��ϼ��� "+age+"�� "+name+"�Դϴ�.");
		}
		
    }

    public void printGuGu(int limit, PrintWriter out) {
    	for (int i = 2; i <= limit; i++) {
		      for (int j = 1; j <= 9; j++) {
		    	  out.println("<p>"+i + " * " + j + " = " + String.format("%2d", i * j)+"</p>");
		    	  System.out.println(i + " * " + j + " = " + String.format("%2d", i * j));
		      }
		      out.println();
		    }
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
