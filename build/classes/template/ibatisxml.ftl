<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://10.20.91.130/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${basePack}.mapper.${className}Dao">
	<resultMap id="${className?uncap_first}Map" type="${className}" >
	  <#list properties as prop>
	    <result column="${prop.columnName}"  property="${prop.fieldName}" />
	  </#list>
	</resultMap>

	<select id="query${className}Msg" resultMap="${className?uncap_first}Map"   parameterType="java.lang.String">
	  SELECT <#list properties as prop>${prop.columnName}<#if prop_has_next>,</#if></#list> FROM  ${tableName} T 
	  WHERE 1 = 1 
	</select>
	<insert id="add${className}" parameterType="${className}">
		INSERT INTO ${tableName}
			(
				<#list properties as prop>${prop.columnName}<#if prop_has_next>,</#if></#list>
			)
		VALUES
			(
				<#list properties as prop>#${r'{'}${prop.fieldName}${r'}'}<#if prop_has_next>,</#if></#list>
			)
			
	</insert>
	
	<update id="update${className}"   parameterType="${className}">
		UPDATE ${tableName} set
		<#list properties as prop>${prop.columnName}=#${r'{'}${prop.fieldName}${r'}'}<#if prop_has_next>,</#if></#list>
		where <#list keyMap?keys as key><#if key == "columnName">${keyMap[key]}</#if></#list> = <#list keyMap?keys as key><#if key == "fieldName">#${r'{'}${keyMap[key]}${r'}'}</#if></#list>
	</update>
	
	<delete id="delete${className}"   parameterType="java.lang.String">
			DELETE FROM ${tableName} WHERE <#list keyMap?keys as key><#if key == "columnName">${keyMap[key]}</#if></#list> = <#list keyMap?keys as key><#if key == "fieldName">#${r'{'}${keyMap[key]}${r'}'}</#if></#list>
	</delete>
	
	<select id="get${className}ByKey"  resultMap="${className?uncap_first}Map"   parameterType="java.lang.String" >
		select <#list properties as prop>${prop.columnName}<#if prop_has_next>,</#if></#list>
	  from  ${tableName} t where <#list keyMap?keys as key><#if key == "columnName">${keyMap[key]}</#if></#list> = #${r'{'}${className?uncap_first}Id${r'}'}
	</select>
</mapper>
