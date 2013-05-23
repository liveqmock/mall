package com.hnfealean.sport.managers.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hnfealean.sport.model.user_acl_module.Permission;
import com.hnfealean.sport.web.JdbcUtils;
import com.hnfealean.sport.web.SystemException;

public class ReloadProcessorAll {
	

	private boolean checkSecurity(String moduleUrl,int permission,int administratorId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from t_module where url=?";//查找Module
			ps = conn.prepareStatement(sql);
			ps.setString(1,moduleUrl);
			rs = ps.executeQuery();
			if(rs==null) return true;
			int moduleId=0;
			while (rs.next()) {
				moduleId= rs.getInt("id");
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
				ps.setString(1,"Role");
				ps.setInt(2,rs.getInt(("roleId")));
				ps.setInt(3,moduleId);
				rs = ps.executeQuery();
				int aclState = 0;
				aclState = rs.getInt("aclState");
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
	}	
}
