/************************** 版权声明 ************************
 * 
 * 版权所有：
 * 
 ************************* 变更记录 *************************
 *
 * 创建者：${codeCreator}   创建日期： ${.now}
 * 
 * 修改者：   修改日期：
 * 
 * 修改记录：        // TODO
 * 
 * @version ${projectVersion} 
 ************************************************************
 */
package ${basePack}.service.impl; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ${basePack}.model.${className}; 
import ${basePack}.mapper.${className}Dao; 
import ${basePack}.service.${className}Service; 

/**
 * @ClassName: ${className}ServiceImpl
 * @Description: 
 * @author ${codeCreator}
 * @date ${.now}
 * 
 */
 @Service("${className?uncap_first}Service")
public class ${className}ServiceImpl implements ${className}Service{

	@Autowired
	private ${className}Dao ${className?uncap_first}Dao;
	
	@Override
	public List<${className}> query${className}Msg(String filter){
		return ${className?uncap_first}Dao.query${className}Msg(filter);
	}

	@Override
	public void add${className}(${className} ${className?uncap_first}){
		${className?uncap_first}Dao.add${className}(${className?uncap_first});
	}

	@Override
	public void update${className}(${className} ${className?uncap_first}){
		${className?uncap_first}Dao.update${className}(${className?uncap_first});
	}

	@Override
	public void delete${className}s(List<String> ${className?uncap_first}Ids){
		${className?uncap_first}Dao.delete${className}s(${className?uncap_first}Ids);

	}
	
	@Override
	public ${className} get${className}ByKey(String ${className?uncap_first}Id){
		return ${className?uncap_first}Dao.get${className}ByKey(${className?uncap_first}Id);
	}
}
