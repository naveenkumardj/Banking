package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import dto.Customer;

@WebServlet("/customerlogin")
public class CustomerLogin extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
int custid=Integer.parseInt(req.getParameter("custid"));
String password=req.getParameter("password");

CustomerDao dao=new CustomerDao();
Customer customer=dao.login(custid);
if(customer==null)
{
	res.getWriter().print("<h1>Inalid customer Id</h1>");
	req.getRequestDispatcher("Login.html").include(req, res);
}else{
	if(customer.getPassword().equals(password))
	{
		req.getSession().setAttribute("customer", customer);
		res.getWriter().print("<h1>Login success</h1>");
		req.getRequestDispatcher("CustomerHome.html").include(req, res);
	}
	else{
		res.getWriter().print("<h1>Inalid password</h1>");
		req.getRequestDispatcher("Login.html").include(req, res);
		
		
		
	}
	
}
}
}
