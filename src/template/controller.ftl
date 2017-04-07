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
package ${basePack}.controller; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import ${basePack}.model.${className}; 
import ${basePack}.service.${className}Service; 

/**
 * @ClassName: ${className}Controller
 * @Description: 
 * @author ${codeCreator}
 * @date ${.now}
 * 
 */
@Controller
@RequestMapping("/${className?uncap_first}")
public class ${className}Controller{

	private static final Logger logger = Logger.getLogger(${className}Controller.class);
	// 日志审计列表中“菜单路径”
	private static final String MENU_NAME = "${menuName}";
	// 日志审计列表中“日志内容”显示信息
	private static final String CONTENT = "${content}";

	@Autowired
	private ${className}Service ${className?uncap_first}Service;
	
	/**
	 * @Title: query${className}Msg
	 * @Description: 
	 * @param filter
	 * @return Map<String,Object> 
	 * @throws
	 */
	@RequestMapping(value = "/query${className}Msg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> query${className}MsgByPost(String filter){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<${className}> ${className?uncap_first}List = ${className?uncap_first}Service.query${className}Msg(filter);
		resultMap.put("${className?uncap_first}List", ${className?uncap_first}List);
		return resultMap;
	}
	/**
	 * @Title: query${className}Msg
	 * @Description: 
	 * @param filter
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value = "/query${className}Msg", method = RequestMethod.GET)
	public ModelAndView query${className}MsgByGet(String filter){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("${className?uncap_first}/${className?uncap_first}Index");
		List<${className}> ${className?uncap_first}List = ${className?uncap_first}Service.query${className}Msg(filter);
		mv.addObject("${className?uncap_first}List",JSONArray.toJSON(${className?uncap_first}List).toString());
		return mv;
	}

	/**
	 * @Title: addOrUpdate${className}
	 * @Description:  
	 * @param ${className?uncap_first}  
	 * @return Map<String,String> 
	 * @throws
	 */
	@RequestMapping(value = "/addOrUpdate${className}", method = RequestMethod.POST)
	@ResponseBody 
	public Map<String, String> addOrUpdate${className}(${className} ${className?uncap_first}){
		Map<String, String> map = new HashMap<String, String>();
		String ${className?uncap_first}Id = "";
		try {
		 	${className?uncap_first}Id = String.valueOf(${className?uncap_first}.<#list keyMap?keys as key><#if key == "fieldName">get${keyMap[key]?cap_first}());</#if></#list>
			if (${className?uncap_first}Id.isEmpty()) {
				${className?uncap_first}.<#list keyMap?keys as key><#if key == "fieldName">set${keyMap[key]?cap_first}</#if></#list>(UUID.randomUUID().toString());
				${className?uncap_first}Service.add${className}(${className?uncap_first});
			} else {
				${className?uncap_first}Service.update${className}(${className?uncap_first});
			}
			// 返回对象的id
			map.put("id", ${className?uncap_first}.<#list keyMap?keys as key><#if key == "fieldName">get${keyMap[key]?cap_first}());</#if></#list>
			map.put("mes", "操作成功");
		} catch (Exception e) {
			map.put("mes", new StringBuilder("操作失败:" + e.getMessage()).toString());
			if (${className?uncap_first}Id.isEmpty()) {
			} else {
			}
		}
		return map;
	}

	/**
	 * @Title: delete${className}s
	 * @Description:  
	 * @param ids  
	 * @return Map<String,String> 返回类型
	 * @throws
	 */
	@RequestMapping(value = "/delete${className}s", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete${className}s(@RequestParam("ids")String ids){
		Map<String, String> map = new HashMap<String, String>();
		try {
			List<String> ${className?uncap_first}Ids = new ArrayList<String>();
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				${className?uncap_first}Ids.add(id);
			}
			${className?uncap_first}Service.delete${className}s(${className?uncap_first}Ids);
			map.put("mes", "删除成功");
		} catch (Exception e) {
			map.put("mes", "删除失败");
		}
		return map;
	
	}
}
