package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BankDao;
import dao.CustomerDao;
import dto.BankAccount;
import dto.Customer;

@WebServlet("/createbankaccount")
public class CreateBankAccount extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String banktype=req.getParameter("banktype");
		
		Customer customer=(Customer)req.getSession().getAttribute("customer");
		List<BankAccount> list=customer.getAccounts();
		boolean flag=true;
		for(BankAccount account:list)
		{
			if(account.getType().equals(banktype))
			{
				flag=false;
				break;
		}
	}
		if(flag){
			
		
		
		
		BankAccount account=new BankAccount();
		account.setType(banktype);
		if(banktype.equals("savings"))
		account.setAclimit(10000);	
		else
		account.setAclimit(50000);
		
		account.setCustomer(customer);
		
		BankDao bankdao=new BankDao();
		bankdao.save(account);
		
		List<BankAccount> list2=list;
		list2.add(account);
		
		customer.setAccounts(list2);
		
		CustomerDao customerdao=new CustomerDao();
		customerdao.update(customer);
		
		res.getWriter().print("<h1>Account Created successfully wait for Management to approve your Account</h1>");
		req.getRequestDispatcher("Login.html").include(req, res);
		}
		else{
			res.getWriter().print("<h1>"+banktype+"Account already exists</h1>");
			req.getRequestDispatcher("CustomerHome.html").include(req, res);
		
		}
		
		}
		
	
}


