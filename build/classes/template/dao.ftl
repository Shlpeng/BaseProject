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
package ${basePack}.mapper; 

import java.util.List;

import ${basePack}.model.${className}; 

/**
 * @ClassName: ${className}Dao
 * @Description: 
 * @author ${codeCreator}
 * @date ${.now}
 * 
 */
public interface ${className}Dao{

	public List<${className}> query${className}Msg(String filter);
	public void add${className}(${className} ${className?uncap_first});
	public void update${className}(${className} ${className?uncap_first});
	public void delete${className}s(List<String> ${className?uncap_first}Ids);
	public ${className} get${className}ByKey(String ${className?uncap_first}Id);
}
