package in.ineuron.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.Student;
import in.ineuron.service.IStudentService;
import in.ineuron.serviceFactory.StudentServiceFactory;

@WebServlet("/controller/*")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doProcess(request,response);			
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		IStudentService studService=StudentServiceFactory.getStudentService();
		
		System.out.println("Request URI         : "+request.getRequestURI());
		System.out.println("Request pathInfo    : "+request.getPathInfo());
		System.out.println("Request servletPath : "+request.getServletPath());
		
		if(request.getPathInfo().endsWith("addform")) {
			
			String name=request.getParameter("sname");
			String age=request.getParameter("sage");
			String address=request.getParameter("saddress");
			
			Student student=new Student();
			student.setSname(name);
			student.setSage(Integer.parseInt(age));
			student.setSaddress(address);
			
			String status=studService.addStudent(student);
			PrintWriter out=response.getWriter();
			
			if(status.equals("success"))
				out.println("<h1 style='color:green; text-align:center;' >Registration Successfull</h1>");
			else
				out.println("<h1 style='color:red; text-align:center;' >Registration Failed</h1>");
			
			out.close();
		}
		
		if(request.getPathInfo().endsWith("searchform")) {
			
			String id=request.getParameter("sid");
			Student stud=studService.searchStudent(Integer.parseInt(id));
			PrintWriter out=response.getWriter();

			if(stud!=null) {
				out.println("<style>"
					+ "     	table,th,td{"
					+ "        		border: 1px solid black;"
					+ "        	}"
					+ "        	table{"
					+ "        		border-collapse:collapse;"
					+ "        	}"
						+ " </style>");
				
				out.println("<table style='margin:auto; border: 1px solid black;'>");				
				    out.println("<tr><th>Id</th><td>"+stud.getSid()+"</td></tr>");
				    out.println("<tr><th>Name</th><td>"+stud.getSname()+"</td></tr>");
				    out.println("<tr><th>Age</th><td>"+stud.getSage()+"</td></tr>");
				    out.println("<tr><th>Address</th><td>"+stud.getSaddress()+"</td></tr>");				  
				out.println("</table>");				
			}
			else
				out.println("<h1 style='color:red; text-align:center;' >Data not found...</h1>");
			
			out.close();
		}
		if(request.getPathInfo().endsWith("deleteform")) {
			
			String id=request.getParameter("sid");
			String status=studService.deleteStudent(Integer.parseInt(id));
			PrintWriter out=response.getWriter();
			
			if(status.equals("success"))
				out.println("<h1 style='color:green; text-align:center;' >Data deleted successfully...</h1>");
			else if(status.equals("failure"))
				out.println("<h1 style='color:red; text-align:center;' >Deletion failed...</h1>");
			else
				out.println("<h1 style='color:red; text-align:center;' >Data not found...</h1>");			
			
			out.close();
		}
		if(request.getPathInfo().endsWith("editform")) {
			
			String id=request.getParameter("sid");
			Student stud=studService.searchStudent(Integer.parseInt(id));
			PrintWriter out=response.getWriter();
			if(stud!=null) {
				out.println("<body>");
				out.println("<form method='post' action='controller/updateform'>");
				out.println("<table style='margin:auto'>");
				out.println("<tr><th>Id</th><td><input type='number' name='sid' min='1' value='"+stud.getSid()+"'></td></tr>");
				out.println("<tr><th>Name</th><td><input type='text' name='sname' value='"+stud.getSname()+"'></td></tr>");
				out.println("<tr><th>Age</th><td><input type='number' name='sage' min='1' value='"+stud.getSage()+"' ></td></tr>");
				out.println("<tr><th>Address</th><td><input type='text' name='saddress' value='"+stud.getSaddress()+"' ></td></tr>");
				out.println("<tr><th></th><td><input type='submit' value='Update' ></td></td></tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</body>");			
			}else
				out.println("<h1 style='color:red; text-align:center;' >Data not found...</h1>");			

						
			out.close();
		}
		if(request.getPathInfo().endsWith("updateform")) {
			
			String id=request.getParameter("sid");
			String name=request.getParameter("sname");
			String age=request.getParameter("sage");
			String address=request.getParameter("saddress");
			
			System.out.println(id);
			System.out.println(name);
			System.out.println(age);
			System.out.println(address);
			
			Student student=new Student();
			student.setSid(Integer.parseInt(id));
			student.setSname(name);
			student.setSage(Integer.parseInt(age));
			student.setSaddress(address);
			
			String status=studService.updateStudent(student);
			PrintWriter out=response.getWriter();
			
			if(status.equals("success"))
				out.println("<h1 style='color:green; text-align:center;' >Data updated successfully...</h1>");
			else
				out.println("<h1 style='color:red; text-align:center;' >Updation failed...</h1>");
			
			out.close();
		}
		
		
	}

}
