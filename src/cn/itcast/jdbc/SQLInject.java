package cn.itcast.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;

public class SQLInject {

	public static void main(String[] args) throws SQLException {
		read("lisi");
		System.out.println("-----------------------");
		read("'or 1 or'"); //
	}

	static void read(String name) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;	
		ResultSet rs = null;

		try {
			conn = JdbcUtils.getConnection();
			
			String sql = "select id,name,birthday,money from user where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);		

			rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("id:" + rs.getInt("id") + "\tname:"
						+ rs.getString("name") + "\tbirthday:"
						+ rs.getDate("birthday") + "\tmoney:"
						+ rs.getFloat("money"));
			}

		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}
	
//	static void read(String name) throws SQLException {
//		Connection conn = null;
//		Statement st = null;
//		ResultSet rs = null; 
//
//		try {
//			conn = JdbcUtils.getConnection();
//
//			st = conn.createStatement();
//
//			String sql = "select id,name,birthday,money from user where name='"
//					+ name + "'";
//
//			rs = st.executeQuery(sql);
//
//			while (rs.next()) {
//				System.out.println("id:" + rs.getObject("id") + "\tname:"
//						+ rs.getObject("name") + "\tbirthday:"
//						+ rs.getObject("birthday") + "\tmoney:"
//						+ rs.getObject("money"));
//			}
//
//		} finally {
//			JdbcUtils.free(rs, st, conn);
//		}
//	}
}
