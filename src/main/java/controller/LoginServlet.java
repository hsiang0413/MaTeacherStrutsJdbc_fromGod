package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CustomerBean;
import model.CustomerService;

@WebServlet("/secure/login.controller")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private CustomerService customerService = new CustomerService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收資料
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		CustomerBean bean = customerService.login(username, password);
		if(bean!=null){
			HttpSession session = request.getSession();
			session.setAttribute("custid", bean.getCustid());
			response.sendRedirect(request.getServletContext().getContextPath()+"/index.jsp");
		} else {
			request.setAttribute("result", "Fail 此帳號不存在或密碼錯誤");
			request.getRequestDispatcher("/secure/login.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
