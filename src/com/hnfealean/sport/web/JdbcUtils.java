package com.hnfealean.sport.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public final class JdbcUtils {
	private static Properties pro = new Properties();
	public static String readUrl(String key){
		return (String) pro.get(key);
	}
	//private static String url = "jdbc:mysql://127.0.0.1:3306/59124";
	//private static String user = "root";
	//private static String password = "hnfealean";

	private JdbcUtils() {
	}

	static {
		try {
			pro.load(JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(readUrl("url"), readUrl("username"), readUrl("password"));
	}

	public static void free(ResultSet rs, Statement st, Connection conn) {
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
				if (conn != null)
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
	}
	public static boolean checkSecurity(String moduleUrl,String functionName,int administratorId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			
			String sql ;
			sql ="select roleId from t_administratorroles where administrator=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,administratorId);
			rs = ps.executeQuery();
			StringBuffer roleIds = new StringBuffer();
			while(rs.next()){
				roleIds.append(rs.getInt("roleId")).append(",");
			}
			if(roleIds.length()>0)
			if(roleIds.charAt(roleIds.length()-1)==",".charAt(0)){
				roleIds.deleteCharAt(roleIds.length()-1);
			}
			
			if(functionName==null){
				sql="select permission from t_acl where url=? and functionName is null and roleId in (?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1,moduleUrl);
				ps.setString(2,roleIds.toString());
			}else{
				sql="select permission from t_acl where url=? and functionName=? and roleId in (?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1,moduleUrl);
				ps.setString(2,functionName);
				ps.setString(3,roleIds.toString());
			}
			rs = ps.executeQuery();
			boolean permission;
			boolean temp=false;
			
				while (rs.next()) {
					temp=true;
					permission= rs.getBoolean("permission");
					if(permission==true)
						return true;
				}
			//没有记录，返回false
			if(temp==false)	return false;
		}catch (SQLException e) {
			throw new SystemException("不能连接数据库!");
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
		return false;
	}
/*	public static boolean checkSecurity(String moduleUrl,int permission,int administratorId){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from t_module where url=?";//查找Module
			ps = conn.prepareStatement(sql);
			ps.setString(1,moduleUrl);
			rs = ps.executeQuery();
			//if(rs==null) return true;
		
			int moduleId=0;
			while (rs.next()) {
				moduleId= rs.getInt("id");
			}
			if(isNull(moduleId)){
				return true;
			}
			sql = "select aclState from t_ACL where principalType=? and principalSn=? and resourceSn=?";//查找授权状态
			ps = conn.prepareStatement(sql);
			ps.setString(1,"Administrator");
			ps.setInt(2,administratorId);
			ps.setInt(3,moduleId);
			rs = ps.executeQuery();
			int directACL=0;
			while (rs.next()) {
				directACL= rs.getInt("aclState");
			}
			if(directACL!=0){
				if(permission==Permission.CREATE){
					//return (aclState%2==1?true:false);
					if(directACL%2==1) return true;
				}else if(permission==Permission.READ){
					//return (aclState/2%2==1?true:false);
					if(directACL/2%2==1) return true;
				}else if(permission==Permission.UPDATE){
					//return (aclState/4%2==1?true:false);
					if(directACL/4%2==1) return true;
				}else if(permission==Permission.DELETE){
					//return (aclState/8%2==1?true:false);
					if(directACL/8%2==1) return true;
				}	
			}
			sql ="select roleId from T_AdministratorRoles where id=?";//查找管理员拥有的角色的ID
			ps = conn.prepareStatement(sql);
			ps.setInt(1,administratorId);
			rs = ps.executeQuery();
			while (rs.next()) {
				directACL= rs.getInt("roleId");
				
				//以此查找角色对该模块的授权
				sql = "select aclState from t_ACL where principalType=? and principalSn=? and resourceSn=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1,"Role");
				ps.setInt(2,directACL);
				ps.setInt(3,moduleId);
				rs = ps.executeQuery();
				int aclState = 0;
				while (rs.next()) {
					aclState = rs.getInt("aclState");
				}
				if(isNull(aclState)){
					return false;
				}
				if(aclState!=0){
					if(permission==Permission.CREATE){
						//return (aclState%2==1?true:false);
						if(aclState%2==1) return true;
					}else if(permission==Permission.READ){
						//return (aclState/2%2==1?true:false);
						if(aclState/2%2==1) return true;
					}else if(permission==Permission.UPDATE){
						//return (aclState/4%2==1?true:false);
						if(aclState/4%2==1) return true;
					}else if(permission==Permission.DELETE){
						//return (aclState/8%2==1?true:false);
						if(aclState/8%2==1) return true;
					}
				}
			}
		
		} catch (SQLException e) {
			throw new SystemException("不能连接数据库!");
		} finally {
			JdbcUtils.free(rs, ps, conn);
		}
		return false;
	}	*/
/*	public static boolean isNull(int number) {
		
		return (number>0?false:true);
	}*/
}

