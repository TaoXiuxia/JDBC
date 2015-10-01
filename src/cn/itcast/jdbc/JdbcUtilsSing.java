/*
 * 
 * 这是一个工具类，一般工具类不能被继承，定义成final
 * 
 * 不需要构造实例，用私有的构造方法
 * 	单例模式或者用static静态代码块，本例用static
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
		if (instance == null) { // 延迟加载
			synchronized (JdbcUtilsSing.class) { // 同步锁
				if (instance == null)
					instance = new JdbcUtilsSing();
			}
		}
		return instance;
	}

	// 注册驱动
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
