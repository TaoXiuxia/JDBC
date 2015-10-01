/*
 * 
 * ����һ�������࣬һ�㹤���಻�ܱ��̳У������final
 * 
 * ����Ҫ����ʵ������˽�еĹ��췽��
 * 	����ģʽ������static��̬����飬������static
 * 
 * */
package cn.itcast.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class JdbcUtilsSing {

	private static String url = "jdbc:mysql://localhost:3306/jdbc";
	private static String user = "root";
	private static String password = "123456";

	private static JdbcUtilsSing instance = null;

	private JdbcUtilsSing() {
	}

	public static JdbcUtilsSing getInstance() {
		if (instance == null) { // �ӳټ���
			synchronized (JdbcUtilsSing.class) { // ͬ����
				if (instance == null)
					instance = new JdbcUtilsSing();
			}
		}
		return instance;
	}

	// ע������
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	public void free(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
