package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductBean;
import model.ProductService;

@WebServlet(urlPatterns = { "/pages/product.controller" })
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = -678147059575990301L;

	private ProductService productService = new ProductService();
	private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 接收資料
		String temp1 = request.getParameter("id");
		String name = request.getParameter("name");
		String temp2 = request.getParameter("price");
		String temp3 = request.getParameter("make");
		String temp4 = request.getParameter("expire");
		String production = request.getParameter("production");
		
		System.out.println(production);
		
		// 轉換資料
		Map<String, String> errorMsg = new HashMap<>();
		int id = 0;
		if (temp1 != null && temp1.length() != 0) {
			try {
				id = Integer.parseInt(temp1);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errorMsg.put("id", "id必須是整數");
			}
		}

		double price = 0;
		if (temp2 != null && temp2.length() != 0) {
			try {
				price = Double.parseDouble(temp2);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errorMsg.put("price", "price必須是數字");
			}
		}

		java.util.Date make = null;
		if (temp3 != null && temp3.length() != 0) {
			try {
				make = sFormat.parse(temp3);
			} catch (ParseException e) {
				e.printStackTrace();
				errorMsg.put("make", "make必須是日期，並且具備YYYY-MM-DD的格式");
			}
		}

		int expire = 0;
		if (temp4 != null && temp4.length() != 0) {
			try {
				expire = Integer.parseInt(temp4);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errorMsg.put("expire", "expire必須是整數");
			}
		}

		// 驗證資料
		if ("Insert".equals(production) || "Update".equals(production) || "Delete".equals(production)) {
			if (temp1 == null || temp1.length() == 0) {
				errorMsg.put("id", "請輸入id以便執行" + production);
			}
		}

		if (errorMsg != null && !errorMsg.isEmpty()) {
			request.getRequestDispatcher("/pages/product.jsp").forward(request, response);
			return;
		}

		// 封裝資料
		ProductBean bean = new ProductBean();
		bean.setId(id);
		bean.setName(name);
		bean.setPrice(price);
		bean.setMake(make);
		bean.setExpire(expire);
		
		// 根據情況呼叫對應的方法並轉導頁面
		if ("Select".equals(production)) {
			List<ProductBean> result = productService.select(bean);
			request.setAttribute("select", result);
			request.setAttribute("rows", result.size());
			request.getRequestDispatcher("/pages/display.jsp").forward(request, response);
			return;
		} else if ("Insert".equals(production)) {
			ProductBean result = productService.insert(bean);
			request.setAttribute("insert", result);
		} else if ("Update".equals(production)) {
			ProductBean result = productService.update(bean);
			request.setAttribute("update", result);
		} else if ("Delete".equals(production)) {
			boolean result = productService.delete(bean);
			request.setAttribute("delete", result);
		} else {
			errorMsg.put("action", "Unknown action : " + production);
		}
		request.getRequestDispatcher("/pages/product.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
