package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
	
	static Properties pros=null;//读取和处理资源文件中的信息
	
	static {
		
		pros=new Properties();
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));//获取资源文件信息
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {//建立连接
		Connection conn = null;
		try {
			Class.forName(pros.getProperty("MySQLDriver"));
			conn = DriverManager.getConnection(pros.getProperty("MySQLurl"), pros.getProperty("MySQLuser"), pros.getProperty("MySQLpassword"));
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动错误");
		} catch (SQLException e) {
			System.out.println("数据库连接错误");
		}
		return conn;
	}

	public static void clear(Connection conn,PreparedStatement ps,ResultSet rs) {//清理资源
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
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

}
