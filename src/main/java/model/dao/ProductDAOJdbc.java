package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.*;
import javax.sql.*;

import model.ProductBean;
import model.ProductDAO;

public class ProductDAOJdbc implements ProductDAO {
	
	private static final String SELECT_BY_ID = "select * from product where id=?";
	
	public Connection getConnetion(){
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/java");
			conn = ds.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	@Override
	public ProductBean select(int id) {
		
		ProductBean result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnetion();
			pstmt = conn.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new ProductBean();
				result.setId(rs.getInt(1));
				result.setName(rs.getString(2));
				result.setPrice(rs.getFloat(3));
				result.setMake(rs.getDate(4));
				result.setExpire(rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	private static final String SELECT_ALL = "select * from product";

	@Override
	public List<ProductBean> select() {

		List<ProductBean> result = null;
		ProductBean pb = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			result = new ArrayList<>();
			conn = getConnetion();
			pstmt = conn.prepareStatement(SELECT_ALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pb = new ProductBean();
				pb.setId(rs.getInt(1));
				pb.setName(rs.getString(2));
				pb.setPrice(rs.getFloat(3));
				pb.setMake(rs.getDate(4));
				pb.setExpire(rs.getInt(5));
				result.add(pb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	private static final String INSERT = "insert into product (id, name, price, make, expire) values (?, ?, ?, ?, ?)";

	@Override
	public ProductBean insert(ProductBean bean) {

		ProductBean result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnetion();
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setInt(1, bean.getId());
			pstmt.setString(2, bean.getName());
			pstmt.setDouble(3, bean.getPrice());
			pstmt.setDate(4, new java.sql.Date(bean.getMake().getTime()));
			pstmt.setInt(5, bean.getExpire());
			if (pstmt.executeUpdate() == 1)
				result = select(bean.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	private static final String UPDATE = "update product set name=?, price=?, make=?, expire=? where id=?";

	@Override
	public ProductBean update(String name, double price, java.util.Date make, int expire, int id) {

		ProductBean result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnetion();
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, name);
			pstmt.setDouble(2, price);
			pstmt.setDate(3, new java.sql.Date(make.getTime()));
			pstmt.setInt(4, expire);
			pstmt.setInt(5, id);
			if (pstmt.executeUpdate() == 1)
				result = select(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	private static final String DELETE = "delete from product where id=?";

	@Override
	public boolean delete(int id) {

		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnetion();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			if (pstmt.executeUpdate() == 1)
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
