package model;

import java.util.ArrayList;
import java.util.List;

import model.dao.ProductDAOJdbc;

public class ProductService {

	private ProductDAO productDao = new ProductDAOJdbc();

	public static void main(String[] args) {

		ProductService service = new ProductService();

		// Test select all
		List<ProductBean> beans = service.select(null);
		beans.forEach(bean -> System.out.println(bean));
		System.out.println();

		// System.out.println("beans="+beans);

		ProductBean pb = new ProductBean();
		pb.setId(11);
		pb.setName("ChiHao");
		pb.setPrice(25);
		pb.setMake(new java.util.Date());
		pb.setExpire(400);

		// Test insert
		System.out.println("insert: " + service.insert(pb) + "\n");

		// Test select
		beans = service.select(pb);
		System.out.print("select: ");
		beans.forEach(bean -> System.out.println(bean + "\n"));

		// Test update
		pb.setName("HuangChiHao");
		System.out.println("update: " + service.update(pb) + "\n");

		// Test delete
		System.out.println("delete: " + service.delete(pb) + "\n");

	}

	public List<ProductBean> select(ProductBean bean) {
		List<ProductBean> result = null;
		if (bean != null && bean.getId() != 0) {
			ProductBean temp = productDao.select(bean.getId());
			if (temp != null) {
				result = new ArrayList<ProductBean>();
				result.add(temp);
			}
		} else {
			result = productDao.select();
		}
		return result;
	}

	public ProductBean insert(ProductBean bean) {
		ProductBean result = null;
		if (bean != null) {
			result = productDao.insert(bean);
		}
		return result;
	}

	public ProductBean update(ProductBean bean) {
		ProductBean result = null;
		if (bean != null) {
			result = productDao.update(bean.getName(), bean.getPrice(), bean.getMake(), bean.getExpire(), bean.getId());
		}
		return result;
	}

	public boolean delete(ProductBean bean) {
		boolean result = false;
		if (bean != null) {
			result = productDao.delete(bean.getId());
		}
		return result;
	}

}
