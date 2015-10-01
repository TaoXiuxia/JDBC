package cn.itcast.jdbc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//blob最大64K
public class BlobTest {

	public static void main(String[] args) throws SQLException, IOException {
//		create();
		read();
	}

	// 跟clob差不多，只是修改了Reader为InputStream，并setBinaryStream()设置二进制流
	static void create() throws SQLException, IOException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = JdbcUtils.getConnection();
			String sql = "insert into blob_test(big_bit)values(?)";
			ps = conn.prepareStatement(sql);

			File file = new File("mudan.jpg");
			InputStream in = new BufferedInputStream(new FileInputStream(file));

			ps.setBinaryStream(1, in, (int) file.length()); // 字节流

			int i = ps.executeUpdate();

			in.close();
			System.out.println("i=" + i);

		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
	}

	//这两个方法的主要复杂点不再jdbc上，而是在输入输出上的；
	static void read() throws SQLException, IOException {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = JdbcUtils.getConnection(); 
			st = conn.createStatement();

			rs = st.executeQuery("select big_bit from blob_test");

			while (rs.next()) {
//				Blob blob = rs.getBlob("1");	//方法1 
//				InputStream in = blob.getBinaryStream();
				
				InputStream in=rs.getBinaryStream(1);	//方法2，这个方法更好
				File file = new File("COPY.jpg");
				OutputStream out = new BufferedOutputStream(new FileOutputStream(file)) ;
				byte[] buff = new byte[1024]; // 缓冲区
				for (int i = 0; (i = in.read(buff)) > 0;)
					out.write(buff, 0, i);

				out.close();
				in.close();
			}

		} finally {
			JdbcUtils.free(rs, st, conn);
		}
	}
}
