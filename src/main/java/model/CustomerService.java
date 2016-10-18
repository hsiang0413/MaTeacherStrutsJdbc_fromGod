package model;

import java.util.Arrays;

import model.dao.CustomerDAOJdbc;

public class CustomerService {

	private CustomerDAO customerDao = new CustomerDAOJdbc();
	
	public static void main(String[] args){
		CustomerService customerService = new CustomerService();
		CustomerBean bean = new CustomerBean();
		System.out.println(bean);
		customerService.changePassword("Ellen", "E", "ABC");
	}
	
	public CustomerBean login(String username, String password){
		CustomerBean bean = customerDao.select(username);
		if(bean!=null){
			if(password!=null && password.length() !=0){
				byte[] pass = bean.getPassword();
				byte[] temp = password.getBytes();
				if(Arrays.equals(pass, temp)){
					return bean;
				}
			}
		}
		return null;
	}
	
	public boolean changePassword(String username, String oldPassword, String newPassword){
		CustomerBean bean = this.login(username, oldPassword);
		if(bean!=null){
			byte[] temp = newPassword.getBytes();
			return customerDao.update(temp, bean.getEmail(), bean.getBirth(), username);
		}
		return false;
	}
}
