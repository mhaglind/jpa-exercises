package net.evolutionaryarchitecture.jpa.samples.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.evolutionaryarchitecture.jpa.samples.service.User;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;



public class UserServlet extends FrameworkServlet {

	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		WebApplicationContext factory = this.getWebApplicationContext();
		
		User myUser = (User) factory.getBean("myUser");
		
		out.println("<html><body>");
		
		String command = request.getParameter("cmd");
		if (command == null) command = "showform";
		
		if (command.equalsIgnoreCase("showform")) {
			out.println("<form>" +
					"Name:<input type=text name='name'/><br>" +
					"Descritpion:<input type=text name='description'/><br>" +
					"Email:<input type=text name='email'/><br>" +
					"<input type='hidden' name='cmd' value='post'>" +
					"<input type=submit>");
		} else if (command.equalsIgnoreCase("post")) {
			myUser.setName(request.getParameter("name"));
			myUser.setDescription(request.getParameter("description"));
			myUser.setEmail(request.getParameter("email"));
			out.println("<p>Populated user: </p>" + myUser.toString());
		} else if (command.equalsIgnoreCase("view")) {
			out.println("<p>Retrieved user instance: </p>" + myUser.toString());
		}

		out.println("</body></html>");
		
		
	}

}
