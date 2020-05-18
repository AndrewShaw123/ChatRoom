package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
	
	static Properties pros=null;//��ȡ�ʹ�����Դ�ļ��е���Ϣ
	
	static {
		
		pros=new Properties();
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));//��ȡ��Դ�ļ���Ϣ
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {//��������
		Connection conn = null;
		try {
			Class.forName(pros.getProperty("MySQLDriver"));
			conn = DriverManager.getConnection(pros.getProperty("MySQLurl"), pros.getProperty("MySQLuser"), pros.getProperty("MySQLpassword"));
		} catch (ClassNotFoundException e) {
			System.out.println("���ݿ���������");
		} catch (SQLException e) {
			System.out.println("���ݿ����Ӵ���");
		}
		return conn;
	}

	public static void clear(Connection conn,PreparedStatement ps,ResultSet rs) {//������Դ
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
