package cn.itcast.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Base {

	public static void main(String[] args) {
		try {
			template();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void template() throws Exception {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// 2.建立连接
			conn = JdbcUtils.getConnection();

			// 3.创建语句
			st = conn.createStatement();

			// 4.执行语句
			rs = st.executeQuery("select * from user");

			// 5.处理结果
			while (rs.next()) {
				System.out.println("id:" + rs.getObject(1) + "\tname:"
						+ rs.getObject(2) + "\tbirthday:" + rs.getObject(3)
						+ "\tmoney:" + rs.getObject(4));
			}

			// 6.释放资源
		} finally {
			JdbcUtils.free(rs,st,conn);
		}
	}
}
