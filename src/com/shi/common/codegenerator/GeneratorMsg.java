package com.shi.common.codegenerator;

/**
 * 
 * @ClassName: GeneratorMsg
 * @Description: 自动生成代码所需信息的封装类
 * @author Shlpeng
 * @date 2016-4-14 17:22:54
 * 
 */
public class GeneratorMsg {
	
	/**
	 * 数据库ip
	 */
	private String ip;    
	/**
	 * 数据库端口
	 */
	private String port;    
	/**
	 * 数据库实例名
	 */
	private String dataBase;    
	/**
	 * 连接用户名
	 */
	private String username;         
	/**
	 * 连接密码
	 */
	private String password; 
	/**
	 * 数据库类型， 此为代码生成工具第一版，先默认数据库类型为Mysql,且只是对单表进行自动生成增删改差代码
	 */
	private String dbType; 
	/**
	 * 数据库表名，用于获取字段名，字段类型，字段注释
	 */
	private String tableName;
	/**
	 * 模块包名(在此包名下建controller，service，impl等包)
	 */
	private String basePack;
	/**
	 * 实体类名字，因为表名有前缀（如T_MD;T_BASE,愿意是通过表名直接生成实体类名，但前缀是多余的。判断前缀麻烦，创建者直接给出实体名）
	 * 注：请保持首字母大写
	 */
	private String modelName;
	/**
	 * 标识是控制层、服务层、服务实现层、Dao层还是Ibatis,JSP的配置文件，用于选择不同模版文件自动生成代码，可为空，为空为生成全部
	 */
	private String mvcFlag; 
	/**
	 * 代码创建者名字
	 */
	private String codeCreator;
	/**
	 * 工程版本
	 */
	private String projectVersion;
	/**
	 * Controller控制类里的menuName属性
	 */
	private String menuName;
	/**
	 * Controller控制类里的content属性
	 */
	private String content;
	/**
	 * face路径头部与src路径头部不同之处
	 * 注：请保持用"."结尾，eg:"com.tmp."
	 */
	private String conflictPath;
	/**
	 * 物理路径部分，eg:"/Users/shlpeng/Documents/workspace/J2EE/HappyGame/"
	 * 注：路径里的斜杠用左斜杠（'/'）
	 */
	private String physicsPath;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getDataBase() {
		return dataBase;
	}
	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getBasePack() {
		return basePack;
	}
	public void setBasePack(String basePack) {
		this.basePack = basePack;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getMvcFlag() {
		return mvcFlag;
	}
	public void setMvcFlag(String mvcFlag) {
		this.mvcFlag = mvcFlag;
	}
	public String getCodeCreator() {
		return codeCreator;
	}
	public void setCodeCreator(String codeCreator) {
		this.codeCreator = codeCreator;
	}
	public String getProjectVersion() {
		return projectVersion;
	}
	public void setProjectVersion(String projectVersion) {
		this.projectVersion = projectVersion;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getConflictPath() {
		return conflictPath;
	}
	public void setConflictPath(String conflictPath) {
		this.conflictPath = conflictPath;
	}
	public String getPhysicsPath() {
		return physicsPath;
	}
	public void setPhysicsPath(String physicsPath) {
		this.physicsPath = physicsPath;
	}
	
}
