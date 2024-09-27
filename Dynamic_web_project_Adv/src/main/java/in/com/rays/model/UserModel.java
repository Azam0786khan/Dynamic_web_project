package in.com.rays.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.com.rays.bean.UserBean;
import in.com.rays.util.JdbcDataSourceByRb;

public class UserModel {

	public Integer nextPk() throws Exception {
		Connection con = JdbcDataSourceByRb.getConnection();
		PreparedStatement psmt = con.prepareStatement("select max(id) from user");

		int pk = 0;
		ResultSet rs = psmt.executeQuery();

		while (rs.next()) {
			pk = rs.getInt(1);
		}
		return pk + 1;
	}

	public void add(UserBean bean) throws Exception {
		Connection con = JdbcDataSourceByRb.getConnection();
		PreparedStatement psmt = con.prepareStatement("insert into user values(?,?,?,?,?,?,?)");

		psmt.setInt(1, nextPk());
		psmt.setString(2, bean.getFirstName());
		psmt.setString(3, bean.getLastName());
		psmt.setString(4, bean.getLoginId());
		psmt.setString(5, bean.getPassword());
		psmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
		psmt.setString(7, bean.getAddress());

		int i = psmt.executeUpdate();
		System.out.println(" Data inserted : " + i);
	}

	public void update(UserBean bean) throws Exception {
		Connection con = JdbcDataSourceByRb.getConnection();
		PreparedStatement psmt = con.prepareStatement(
				"update user set first_name = ?, last_name = ?, login_id = ?, password = ?, address = ? where id = ?");

		psmt.setString(1, bean.getFirstName());
		psmt.setString(2, bean.getLastName());
		psmt.setString(3, bean.getLoginId());
		psmt.setString(4, bean.getPassword());
		psmt.setString(5, bean.getAddress());
		psmt.setInt(6, bean.getId());

		int i = psmt.executeUpdate();
		System.out.println(" Data updated : " + i);

	}

	public void delete(int id) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		PreparedStatement psmt = con.prepareStatement("delete from user where id = ?");

		psmt.setInt(1, id);

		int i = psmt.executeUpdate();
		System.out.println(" Data deleted : " + i);

	}

	public UserBean findByPk(int id) throws Exception {
		Connection con = JdbcDataSourceByRb.getConnection();
		PreparedStatement psmt = con.prepareStatement("select * from user where id = ?");
		psmt.setInt(1, id);

		UserBean bean = null;
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginId(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setAddress(rs.getString(6));
		}
		return bean;
	}

	public UserBean findByLoginId(int loginId) throws Exception {
		Connection con = JdbcDataSourceByRb.getConnection();
		PreparedStatement psmt = con.prepareStatement("select * from user where login_id = ?");
		psmt.setInt(1, loginId);

		UserBean bean = null;
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginId(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setAddress(rs.getString(6));
		}
		return bean;
	}

	public List search(UserBean bean, int pageNo, int pageSize) throws Exception {
		Connection con = JdbcDataSourceByRb.getConnection();
		StringBuffer sql = new StringBuffer("select * from user where 1=1");

		if (bean != null) {
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "%'");
			}

			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" and last_name like '" + bean.getLastName() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		PreparedStatement psmt = con.prepareStatement(sql.toString());

		List list = new ArrayList();
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginId(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
			bean.setAddress(rs.getString(7));
			list.add(bean);
		}

		return list;
	}

	public UserBean authenticate(String loginId, String password) throws Exception {

		Connection conn = JdbcDataSourceByRb.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from user where login_id = ? and password = ?");

		pstmt.setString(1, loginId);
		pstmt.setString(2, password);

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLoginId(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setDob(rs.getDate(6));
			bean.setAddress(rs.getString(7));
		}

		JdbcDataSourceByRb.closeConnection(conn);

		return bean;
	}

}