package com.hnfealean.sport.model.user_acl_module;


public class ACL20101229 {

	private static final String TYPE_USER="Administrator";
	private static final String TYPE_ROLE="Role";
	
    public static final int ACL_YES = 1;
    public static final int ACL_NO = 0;
    public static final int ACL_NEUTRAL = -1;
    
	/**
	 * @hibernate.id
	 * 		generator-class="native"
	 */
	private int id;
	
	/**
	 * 主体类型，可以是ROLE后者USER
	 * @hibernate.property not-null="true"
	 */
	private String principalType;
	
	/**
	 * 主体标识，就是Role的ID或者Administrator的ID
	 * @hibernate.property not-null="true"
	 */
	private int principalSn;
	
	/**
	 * 资源标识，就是资源的ID,就是module的ID
	 * @hibernate.property not-null="true"
	 */
	private int resourceSn;
	
	/**
	 * 授权状态，用后4位标识CRUD，分别表示创建、读取、更新、删除操作，0表示拒绝访问，1表示允许
	 * @hibernate.property not-null="true"
	 */
	private int aclState;
	
	/**
	 * 表示继承,继承状态
	 * @hibernate.property not-null="true"
	 */
	private int aclTriState;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getPrincipalType() {
		return principalType;
	}

	public void setPrincipalType(String principalType) {
		this.principalType = principalType;
	}

	public int getPrincipalSn() {
		return principalSn;
	}

	public void setPrincipalSn(int principalSn) {
		this.principalSn = principalSn;
	}

	public int getResourceSn() {
		return resourceSn;
	}

	public void setResourceSn(int resourceSn) {
		this.resourceSn = resourceSn;
	}

	public int getAclState() {
		return aclState;
	}

	public void setAclState(int aclState) {
		this.aclState = aclState;
	}

	public int getAclTriState() {
		return aclTriState;
	}

	public void setAclTriState(int aclTriState) {
		this.aclTriState = aclTriState;
	}

	public static String getTYPE_USER() {
		return TYPE_USER;
	}

	public static String getTYPE_ROLE() {
		return TYPE_ROLE;
	}

	/**
	 * acl 实例跟主体和资源相关联
	 * 针对此实例进行授权，某种操作是否允许
	 * @param permission，只可以取值1，2，3
	 * @param yes true 表示允许，false表示不允许
	 */
	public void setPermission(int permission,boolean yes){
		int tmp = 1;
		tmp = tmp << permission;
		if(yes){
			aclState |= tmp;
		}else{
			aclState &= ~tmp;
		}
	}
	  /**
     * 获得ACL授权
     * @param permission C/R/U/D权限
     * @return 授权标识：允许/不允许/不确定
     */
    public int getPermission(int permission){
            
            //如果继承，则返回未定的授权信息
            if(aclTriState == 0xFFFFFFFF){
                    return ACL_NEUTRAL;
            }
            
            int tmp = 1;
            
            tmp = tmp << permission;
            
            tmp &= aclState;
            
            if(tmp != 0){
                    return ACL_YES;
            }
            
            return ACL_NO;
    }
    
    /**
     * 设置本授权是否是继承的
     * @param yes true表示继承，false表示不继承
     */
    public void setExtends(boolean yes){
            if(yes){
                    aclTriState = 0xFFFFFFFF;
            }else{
                    aclTriState = 0;
            }
    }
}
