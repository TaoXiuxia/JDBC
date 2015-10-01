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
			// 2.��������
			conn = JdbcUtils.getConnection();

			// 3.�������
			st = conn.createStatement();

			// 4.ִ�����
			rs = st.executeQuery("select * from user");

			// 5.������
			while (rs.next()) {
				System.out.println("id:" + rs.getObject(1) + "\tname:"
						+ rs.getObject(2) + "\tbirthday:" + rs.getObject(3)
						+ "\tmoney:" + rs.getObject(4));
			}

			// 6.�ͷ���Դ
		} finally {
			JdbcUtils.free(rs,st,conn);
		}
	}
}
