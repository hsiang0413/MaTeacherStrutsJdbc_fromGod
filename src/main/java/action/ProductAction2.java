package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.ProductBean;
import model.ProductService;

public class ProductAction2 extends ActionSupport {

	private static final long serialVersionUID = 1L;

	// 接收資料&封裝資料
	private ProductBean bean;
	private String production;

	public ProductBean getBean() {
		return bean;
	}

	public void setBean(ProductBean bean) {
		this.bean = bean;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public void validate() {
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
	
	public String execute() {
		
		ProductService service = new ProductService();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		if ("Select".equals(production)) {
			List<ProductBean> result = service.select(bean);
			if(result != null){
				request.setAttribute("result", result);
				return SUCCESS;
			}
		} else if ("Insert".equals(production)) {
			if(service.insert(bean) != null){
				request.setAttribute("result", production+" Success");
			}
		} else if ("Update".equals(production)) {
			if(service.update(bean) != null){
				request.setAttribute("result", production+" Success");
			}
		} else if ("Delete".equals(production)) {
			if(service.delete(bean)){
				request.setAttribute("result", production+" Success");
			}
		}
		
		// 若上述CRUD產生錯誤 (根據不同方法回傳不同訊息)
		if(request.getAttribute("result")!=null){
			request.setAttribute("result", ("Insert".equals(production)) ? 
					(production+" Fail 此筆資料(ID)已存在") : (production+" Fail 此筆資料(ID)不存在")
			);
		}
		
		return INPUT;
	}
	
}
