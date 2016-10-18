package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.CustomerBean;
import model.CustomerService;

public class LoginAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean validUser(String username, String password){
		System.out.println("LoginAction validUser method");
		CustomerService service = new CustomerService();
		CustomerBean tmp = service.login(username, password);
		if(tmp!=null){
			return true;
		}
		return false;
	}

	public String execute() {
		
		System.out.println("LoginAction execute method");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		//if(!validUser(bean.getCustid(), new String(bean.getPassword(), StandardCharsets.UTF_8))){
		if(!validUser(username, password)){
			request.setAttribute("result", "Fail");
			return INPUT;
		} else {
			session.setAttribute("username", username);
			return SUCCESS;
		}
	}
}
