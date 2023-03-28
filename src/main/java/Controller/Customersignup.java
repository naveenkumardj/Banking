package Controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import dto.Customer;

@WebServlet("/customersignup")
public class Customersignup extends HttpServlet {
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
     CustomerDao dao=new CustomerDao();
	String name=req.getParameter("name");
	long mobile=Long.parseLong(req.getParameter("mobile"));
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	String gender=req.getParameter("gender");
	Date date=Date.valueOf(req.getParameter("dob"));
	
	//Period period=Period.between(date.toLocalDate(), LocalDate.now());
	int age=Period.between(date.toLocalDate(), LocalDate.now()).getYears();
	if(age<18)
	{
		res.getWriter().print("<h1> you have to be 18+ to create an account</h1> ");
		req.getRequestDispatcher("Signup.html").include(req, res);
		
	}else{
		if(dao.check(mobile).isEmpty()&&dao.check(email).isEmpty())
		{
		Customer customer=new Customer();
		customer.setName(name);
		customer.setGender(gender);
		customer.setPassword(password);
		customer.setDate(date);
		customer.setEmail(email);
		customer.setMobile(mobile);
		
		dao.save(customer);
		
		Customer customer2=dao.check(email).get(0);
		
		res.getWriter().print("<h1>Account created successfully</h1>");
		if(customer2.getGender().equals("male"))
			res.getWriter().print("<h1>hello sir</h1>");
		else
			res.getWriter().print("<h1>hello mam</h1>");
		res.getWriter().print("<h1>customer id :"+customer2.getCust_id()+"</h1>");
		req.getRequestDispatcher("Home.html").include(req, res);
		
		
		req.getRequestDispatcher("Home.html").include(req, res);
		
	}else{
		res.getWriter().print("<h1>email or phone number already exists</h1>");
		req.getRequestDispatcher("Signup.html").include(req, res);
	}
		
	
	
	
//	res.getWriter().print("<h1>"
//			+ "<br>name:"+name
//			+ "<br>mobile:"+mobile
//			+ "<br>email:"+email
//			+ "<br>password:"+password
//			+ "<br>gender:"+gender
//			+ "<br>date_of_birth:"+date
//			
//			+ "</h1>");
//	
	
	
	
}
}}
