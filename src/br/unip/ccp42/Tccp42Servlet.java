package br.unip.ccp42;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Tccp42Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		resp.getWriter().println("<html><body>");
		resp.getWriter().println("<h1>Em Breve</h1>");
		//resp.getWriter().println("<h2><a href=\"tccp42\">");
		
		@SuppressWarnings("unchecked")
		Enumeration<String> item = (Enumeration<String>)req.getParameterNames();
		
		while (item.hasMoreElements()) {
			resp.getWriter().println(item.nextElement());
			resp.getWriter().println("\n");
		} 
				
		//resp.getWriter().println("</a></h2>");
		resp.getWriter().println("</html></body>");
	}
	
}
