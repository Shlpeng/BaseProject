package com.shi.common.codegenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class CodeGenerator {
	public static void main(String[] args){
		//以下参数的含义可跳转至实体类里看属性详情
		GeneratorMsg generatorMsg = new GeneratorMsg();
		generatorMsg.setIp("127.0.0.1");
		generatorMsg.setPort("3306");
		generatorMsg.setDataBase("happygame");
		generatorMsg.setUsername("root");
		generatorMsg.setPassword("123");
		generatorMsg.setDbType(GeneratorConstants.DBTYPE_MYSQL);
		generatorMsg.setTableName("t_gold_record");
		generatorMsg.setBasePack("com.tmp.record");
		generatorMsg.setModelName("Record");//* 注：请保持首字母大写
		generatorMsg.setMvcFlag("");
//		generatorMsg.setMvcFlag(GeneratorConstants.MODEL_FLAG);
		generatorMsg.setConflictPath("com.tmp.");//* 注：请保持用"."结尾，eg:"com.tmp."
		generatorMsg.setCodeCreator("Shlpeng");
		generatorMsg.setProjectVersion("V1.0");
		generatorMsg.setMenuName("导航栏");
		generatorMsg.setContent("");
		generatorMsg.setPhysicsPath("/Users/shlpeng/Documents/workspace/J2EE/BaseProject/");//*注：路径里的斜杠用左斜杠（'/'）
		generate(generatorMsg);
	}

	/**
	 * @Title: generate
	 * @Description: 自动生成代码
	 * @param  generatorMsg 自动生成代码所需信息的封装类
	 * @return void 
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static void generate(GeneratorMsg generatorMsg){
		try {
			 //获取模版文件
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File("src/template"));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			Template controllerTemplate = cfg.getTemplate("controller.ftl");
			Template modelTemplate = cfg.getTemplate("model.ftl");
			Template serviceTemplate = cfg.getTemplate("service.ftl");
			Template serviceImplTemplate = cfg.getTemplate("serviceImpl.ftl");
			Template daoTemplate = cfg.getTemplate("dao.ftl");
			Template ibatisXMLTemplate = cfg.getTemplate("ibatisxml.ftl");
			
//			 //得到输出目录（包含物理路径）
//			String controllerRoot = generatorMsg.getPhysicsPath()+getPackStr("src",generatorMsg.getBasePack(),"controller");
//			String modelRoot = generatorMsg.getPhysicsPath()+getPackStr("src",generatorMsg.getBasePack(),"model");
//			String serviceRoot = generatorMsg.getPhysicsPath()+getPackStr("src",generatorMsg.getBasePack(),"service");
//			String serviceImplRoot = generatorMsg.getPhysicsPath()+getPackStr("src",generatorMsg.getBasePack(),"service.impl");
//			String daoRoot = generatorMsg.getPhysicsPath()+getPackStr("src",generatorMsg.getBasePack(),"mapper");
			//得到输出目录
			String controllerRoot = getPackStr("src",generatorMsg.getBasePack(),"controller");
			String modelRoot = getPackStr("src",generatorMsg.getBasePack(),"model");
			String serviceRoot = getPackStr("src",generatorMsg.getBasePack(),"service");
			String serviceImplRoot = getPackStr("src",generatorMsg.getBasePack(),"service.impl");
			String daoRoot = getPackStr("src",generatorMsg.getBasePack(),"mapper");
		
			// 创建文件夹，不存在则创建
			FileUtils.forceMkdir(new File(controllerRoot));
			FileUtils.forceMkdir(new File(modelRoot));
			FileUtils.forceMkdir(new File(serviceRoot));
			FileUtils.forceMkdir(new File(serviceImplRoot));
			FileUtils.forceMkdir(new File(daoRoot));
			
			// 得到输出文件流
			File controllerOutput = new File(controllerRoot + "/"+generatorMsg.getModelName()+"Controller.java");
			File modelOutput = new File(modelRoot + "/"+generatorMsg.getModelName()+".java");
			File serviceOutput = new File(serviceRoot + "/"+generatorMsg.getModelName()+"Service.java");
			File serviceImplOutput = new File(serviceImplRoot + "/"+generatorMsg.getModelName()+"ServiceImpl.java");
			File daoOutput = new File(daoRoot + "/"+generatorMsg.getModelName()+"Dao.java");
			File ibatisXMLOutput = new File(daoRoot + "/"+generatorMsg.getModelName()+"Mapper.xml");
			
			// 获取数据
			Map<String,Object> map = readData(generatorMsg);
			Set<Map<String, String>> properties = (Set<Map<String, String>>)map.get("properties");
			Map<String, String> keyMap = (Map<String, String>)map.get("keyMap");
			// 获取模版填充信息
			Map<String, Object> baseMap = getTemplateBaseMap(generatorMsg);
			Map<String, Object> controllerMap = getTemplateBaseMap(generatorMsg);
			controllerMap.put("menuName", generatorMsg.getMenuName());
			controllerMap.put("content", generatorMsg.getContent());
			controllerMap.put("keyMap", keyMap);
			Map<String, Object> modelMap = getTemplateBaseMap(generatorMsg);
			modelMap.put("properties", properties);
			Map<String, Object> ibatisXMLMap = getTemplateBaseMap(generatorMsg);
			ibatisXMLMap.put("tableName", generatorMsg.getTableName().toUpperCase());
			ibatisXMLMap.put("properties", properties);
			ibatisXMLMap.put("keyMap", keyMap);
			
			//生成代码文件
			if(generatorMsg.getMvcFlag().equals(GeneratorConstants.CONTROLLER_FLAG)){
				Writer controllerWriter = new FileWriter(controllerOutput);
				controllerTemplate.process(controllerMap, controllerWriter);
				controllerWriter.close();
			}else if(generatorMsg.getMvcFlag().equals(GeneratorConstants.DAO_FLAG)){
				Writer daoWriter = new FileWriter(daoOutput);
				daoTemplate.process(baseMap, daoWriter);
				daoWriter.close();
			}else if(generatorMsg.getMvcFlag().equals(GeneratorConstants.IBATISXML_FLAG)){
				Writer ibatisXMLWriter = new FileWriter(ibatisXMLOutput);
				ibatisXMLTemplate.process(ibatisXMLMap, ibatisXMLWriter);
				ibatisXMLWriter.close();
			}else if(generatorMsg.getMvcFlag().equals(GeneratorConstants.MODEL_FLAG)){
				Writer modelWriter = new FileWriter(modelOutput);
				modelTemplate.process(modelMap, modelWriter);
				modelWriter.close();
			}else if(generatorMsg.getMvcFlag().equals(GeneratorConstants.SERVICE_FLAG)){
				Writer serviceWriter = new FileWriter(serviceOutput);
				serviceTemplate.process(baseMap, serviceWriter);
				serviceWriter.close();
			}else if(generatorMsg.getMvcFlag().equals(GeneratorConstants.SERVICEIMPL_FLAG)){
				Writer serviceImplWriter = new FileWriter(serviceImplOutput);
				serviceImplTemplate.process(baseMap, serviceImplWriter);
				serviceImplWriter.close();
			}else{
				Writer controllerWriter = new FileWriter(controllerOutput);
				Writer daoWriter = new FileWriter(daoOutput);
				Writer ibatisXMLWriter = new FileWriter(ibatisXMLOutput);
				Writer modelWriter = new FileWriter(modelOutput);
				Writer serviceWriter = new FileWriter(serviceOutput);
				Writer serviceImplWriter = new FileWriter(serviceImplOutput);

				controllerTemplate.process(controllerMap, controllerWriter);
				controllerWriter.close();
				modelTemplate.process(modelMap, modelWriter);
				modelWriter.close();
				serviceTemplate.process(baseMap, serviceWriter);
				serviceWriter.close();
				serviceImplTemplate.process(baseMap, serviceImplWriter);
				serviceImplWriter.close();
				daoTemplate.process(baseMap, daoWriter);
				daoWriter.close();
				ibatisXMLTemplate.process(ibatisXMLMap, ibatisXMLWriter);
				ibatisXMLWriter.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Title: getTemplateBaseMap
	 * @Description: 生成模版填充的基础信息
	 * @param generatorMsg 自动生成代码所需信息的封装类
	 * @return Map<String, Object> 
	 * @throws
	 */
	private static Map<String, Object> getTemplateBaseMap(GeneratorMsg generatorMsg){
		Map<String, Object> baseMap = new HashMap<String, Object>();
		baseMap.put("basePack", generatorMsg.getBasePack());
		baseMap.put("className", generatorMsg.getModelName());
		baseMap.put("codeCreator", generatorMsg.getCodeCreator());
		baseMap.put("projectVersion", generatorMsg.getProjectVersion());
		return baseMap;
	}
	
	/**
	 * @Title: readData
	 * @Description: 读取表数据
	 * @param generatorMsg
	 * @return Map<String, Object> 
	 * @throws
	 */
	public static Map<String, Object> readData(GeneratorMsg generatorMsg){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Set<Map<String, String>> properties = new LinkedHashSet<Map<String, String>>();
		Map<String, String> keyMap = new HashMap<String, String>();
		Connection conn = null;
		ResultSet rs = null;
		ResultSet key = null;
		try {
			if(generatorMsg.getDbType().equals(GeneratorConstants.DBTYPE_MYSQL)){
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://"+generatorMsg.getIp()+":"+generatorMsg.getPort()+"/"+generatorMsg.getDataBase()+"?useUnicode=true&characterEncoding=UTF-8",generatorMsg.getUsername(), generatorMsg.getPassword());
			}
			DatabaseMetaData dbmd = conn.getMetaData();
			key = dbmd.getPrimaryKeys(null, null, generatorMsg.getTableName());
			while (key.next()) {
				keyMap.put("fieldName", getFieldName(key.getString("COLUMN_NAME")));//实体类属性名
				keyMap.put("columnName", key.getString("COLUMN_NAME").toUpperCase());//列名
				break;
			}
			rs = dbmd.getColumns(null, null, generatorMsg.getTableName(), null);
			while (rs.next()) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("fieldName", getFieldName(rs.getString("COLUMN_NAME")));
				map.put("columnName", rs.getString("COLUMN_NAME").toUpperCase());
				map.put("fieldType", getFieldType(rs.getString("TYPE_NAME")));
				map.put("fieldRemark", rs.getString("REMARKS"));
				properties.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn != null){
					conn.close();
				}
				if(rs != null){
					rs.close();
				}
				if(key != null){
					key.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		resultMap.put("properties", properties);
		resultMap.put("keyMap", keyMap);
		return resultMap;
	}
	
	/**
	 * @Title: getPackStr
	 * @Description: 根据包名获取相应的路径名
	 * @param root
	 * @param pack
	 * @param subPack
	 * @return String 
	 * @throws
	 */
	public static String getPackStr(String root,String pack,String subPack){
		String result = root;
		String [] dirs = pack.split("\\.");
		for(String dir : dirs){
			result += "/"+dir;
		}
		if(!subPack.trim().equals("")){
			String [] dirs2 = subPack.split("\\.");
			for(String dir2 : dirs2){
				result += "/"+dir2;
			}
		}
		return result;
	}
	
	/**
	 * @Title: getFieldName
	 * @Description: 根据表字段获取java中的字段名
	 * @param field
	 * @return String
	 * @throws
	 */
	public static String getFieldName(String field) {
		String result = "";
		String lowerFeild = field.toLowerCase();
		String[] fields = lowerFeild.split("_");
		result += fields[0];
		if (fields.length > 1) {
			for(int i=1;i<fields.length;i++){
				result += fields[i].substring(0,1).toUpperCase() + fields[i].substring(1, fields[i].length());
			}
		}
		return result;
	}
	
	/**
	 * @Title: getFieldType
	 * @Description: 根据表字段的类型生成对应的java的属性类型
	 * @param type
	 * @return String
	 * @throws
	 */
	public static String getFieldType(String type){
		String result = "String";
		if(type.toLowerCase().equals("varchar")){
			result = "String";
		}else if(type.toLowerCase().equals("char")){
			result = "String";
		}else if(type.toLowerCase().equals("double")){
			result = "double";
		}else if(type.toLowerCase().equals("int")){
			result = "int";
		}else if(type.toLowerCase().equals("date")){
			result = "Date";
		}else if(type.toLowerCase().equals("datetime")){
			result = "Date";
		}else if(type.toLowerCase().equals("timestamp")){
			result = "Date";
		}
		return result;
	}
}
