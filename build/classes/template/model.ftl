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
package ${basePack}.model; 

import java.io.Serializable;
  <#list properties as prop>
  	<#if prop.fieldType == "Date">
import java.util.Date;
		<#break>
  	</#if>
  </#list>
/**
 * 
 * @ClassName: ${className}
 * @Description: 
 * @author ${codeCreator}
 * @date  ${.now}
 * 
 */
public class ${className} implements Serializable{ 

  <#list properties as prop>
  	/**
	 * ${prop.fieldRemark}
	 */
    private ${prop.fieldType} ${prop.fieldName};
  </#list>

  <#list properties as prop>
    public ${prop.fieldType} get${prop.fieldName?cap_first}(){
      return ${prop.fieldName};
    }
    public void set${prop.fieldName?cap_first}(${prop.fieldType} ${prop.fieldName}){
      this.${prop.fieldName} = ${prop.fieldName};
    }
  </#list>
}
