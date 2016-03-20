/**
 * 
 */
package com.atguigu.springmvc.crud.handler;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.springmvc.crud.dao.DepartmentDao;
import com.atguigu.springmvc.crud.dao.EmployeeDao;
import com.atguigu.springmvc.crud.entities.Employee;

/**
 * @author lyp
 *
 */
@Controller
public class EmployeeHandler {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@RequestMapping("/i18n2")
	 public String testi18n2( )
	 {
	  /* String v1 = messageSource.getMessage("i18n.username",null,locale);
	   String v2 = messageSource.getMessage("i18n.password",null,locale);
	   System.out.println(v1+"\t"+v2);*/
	   return "ok";
	 }

	
	/**
	 * 显示所有员工的信息
	 * @return
	 */
	@RequestMapping(value="emps",method=RequestMethod.GET)
	public String emps(Map<String,Object> mapStr){
		Collection<Employee> emps = employeeDao.getAll();
		mapStr.put("emps", emps);
		System.out.println("hhhhh");
		return "emp_list";
	}
	
	@RequestMapping(value="emp",method=RequestMethod.GET)
	public String emp(Map<String,Object> map){
		map.put("department", departmentDao.getDepartments());
		map.put("employee", new Employee());
		map.put("gender", this.getGenders());
		return "input";
	}
	
	public Map<String,String> getGenders(){
		Map<String,String> mapStr=new HashMap<String, String>();
		mapStr.put("1", "male");
		mapStr.put("0", "female");
		return mapStr;
	}
	
	@RequestMapping(value="emp",method=RequestMethod.POST)
	public String add(@Valid Employee employee,BindingResult bindingResult,Map<String,Object> map){
		int errorCount = bindingResult.getErrorCount();
		if(errorCount>0){
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		    for(FieldError filerror: fieldErrors){
		    	System.out.println(filerror.getField()+"--"+filerror.getDefaultMessage()); 
		    }
		    map.put("department", departmentDao.getDepartments());
			map.put("gender", this.getGenders());
		    return "input";
		}
		employeeDao.save(employee);
		return  "redirect:/emps";
	}
	
	@RequestMapping(value="emp/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id")Integer id){
		employeeDao.delete(id);
		return "redirect:/emps";
	 }
	
	@RequestMapping(value="emp/{id}",method=RequestMethod.GET)
	public String to(@PathVariable("id")Integer id,Map<String,Object> map){
		Employee employee = employeeDao.get(id);
		map.put("employee", employee);
		map.put("department", departmentDao.getDepartments());
		map.put("gender", this.getGenders());
		return "edit";
	}
	
	@RequestMapping(value="emp",method=RequestMethod.PUT)
	public String edit(Employee employee){
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	@ModelAttribute
	public  void getEmp(@RequestParam(value="id",required=false)Integer id,Map<String,Object> mapStr){
		if(null !=id){
			
			Employee employee = employeeDao.get(id);
			mapStr.put("employee",employee);
		}
		 
	}
	
	@RequestMapping(value="testConvertEmployee")
	public String testConvertEmployee(Employee employee){
		employeeDao.save(employee);
		return  "redirect:/emps";
	}
}
