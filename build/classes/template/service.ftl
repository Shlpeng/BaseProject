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
package ${basePack}.service; 

import java.util.List;

import ${basePack}.model.${className}; 

/**
 * @ClassName: ${className}Service
 * @Description: 
 * @author ${codeCreator}
 * @date ${.now}
 * 
 */
public interface ${className}Service{
	/**
	 * @Title: query${className}Msg
	 * @Description: 
	 * @param filter
	 * @return List<${className}> 
	 * @throws
	 */
	public List<${className}> query${className}Msg(String filter);

	/**
	 * @Title: add${className}
	 * @Description:  
	 * @param ${className?uncap_first}  
	 * @return void 
	 * @throws
	 */
	public void add${className}(${className} ${className?uncap_first});

	/**
	 * 
	 * @Title: update${className}
	 * @Description:  
	 * @param ${className?uncap_first}  
	 * @return void 
	 * @throws
	 */
	public void update${className}(${className} ${className?uncap_first});

	/**
	 * @Title: delete${className}s
	 * @Description:  
	 * @param ${className?uncap_first}Ids  
	 * @return void 
	 * @throws
	 */
	public void delete${className}s(List<String> ${className?uncap_first}Ids);

	/**
	 * @Title: get${className}ByKey
	 * @Description:  
	 * @param ${className?uncap_first}Id
	 * @return ${className} 
	 * @throws
	 */
	public ${className} get${className}ByKey(String ${className?uncap_first}Id);
}
