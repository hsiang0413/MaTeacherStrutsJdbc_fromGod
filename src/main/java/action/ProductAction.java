package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.ProductBean;
import model.ProductService;

public class ProductAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	// 接收資料&封裝資料
	private ProductBean bean;

	public ProductBean getBean() {
		return bean;
	}

	public void setBean(ProductBean bean) {
		this.bean = bean;
	}
	
	ProductService service = new ProductService();
	HttpServletRequest request = ServletActionContext.getRequest();
	
	public void validate() {
		
		String production = null;
		
		if(request.getParameter("method:prodSelect") != null){
			production = request.getParameter("method:prodSelect");
		} else if(request.getParameter("method:prodInsert") != null) {
			production = request.getParameter("method:prodInsert");
		} else if(request.getParameter("method:prodUpdate") != null) {
			production = request.getParameter("method:prodUpdate");
		} else if(request.getParameter("method:prodDelete") != null) {
			production = request.getParameter("method:prodDelete");
		}

		if (!"Select".equals(production)) {
			if (bean.getId() <= 0) {
				addFieldError("bean.id", "ID請輸入大於0的整數");
			}
			if (bean.getName() == null || bean.getName().trim().length() == 0) {
				addFieldError("bean.name", "Name請勿空白");
			}
			if (bean.getPrice() <= 0) {
				addFieldError("bean.price", "Price請輸入大於0的整數");
			}
			if (bean.getMake() == null) {
				addFieldError("bean.make", "Make請輸入1970-01-01~2016-05-26的日期");
			}
			if (bean.getExpire() <= 0) {
				addFieldError("bean.expire", "Expire請輸入大於0的整數");
			}
		} else {
			if (bean.getId() < 0) {
				addFieldError("bean.id", "查詢單筆請輸入大於0的整數，查詢全部資料請空白");
			}
		}
	}

	public String prodSelect(){
		
		List<ProductBean> result = service.select(bean);
		if(result != null){
			request.setAttribute("result", result);
			return SUCCESS;
		} else {		
			request.setAttribute("result", "Select Fail 此筆資料(ID)不存在");
		}

		return INPUT;
	}
	
	public String prodInsert(){
		
		if(service.insert(bean) != null){
			request.setAttribute("result", "Insert Success");
		} else {
			request.setAttribute("result", "Insert Fail 此筆資料(ID)已存在");			
		}
		return INPUT;
	}
	
	public String prodUpdate(){
		
		if(service.update(bean) != null){
			request.setAttribute("result", "Update Success");
		} else {
			request.setAttribute("result", "Update Fail 此筆資料(ID)不存在");			
		}
		return INPUT;
	}
	
	public String prodDelete(){
		
		if(service.delete(bean)){
			request.setAttribute("result", "Delete Success");
		} else {
			request.setAttribute("result", "Delete Fail 此筆資料(ID)不存在");
		}
		return INPUT;
	}
}
