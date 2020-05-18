package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import dto.User;
import util.JDBCUtil;


public class LoginAndRegisterDao {
	
	private static Connection conn=null;
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	
	public static boolean queryByUserNameAndPassWord(User user) {
		
		conn = JDBCUtil.getConnection();
		try {
			String sql = "select * from chatroom.user where userName=? and passWord=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassWord());
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn,ps,rs);
		}
		return false;
	}
	
	public static boolean queryByUserName(String userName) {
		
		conn = JDBCUtil.getConnection();
		
		try {
			String sql = "select * from chatroom.user where userName=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,userName);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn,ps,rs);
		}
		return false;
	}
	
	
	public static void saveUser(User user) {
		conn = JDBCUtil.getConnection();
		try {
			String sql = "INSERT INTO chatroom.user(`userName`, `passWord`) VALUES (?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1,user.getUserName());
			ps.setString(2, user.getPassWord());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.clear(conn, ps, null);
		}
	}
	
	

}
