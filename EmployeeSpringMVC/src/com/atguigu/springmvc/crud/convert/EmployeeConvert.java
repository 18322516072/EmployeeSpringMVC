/**
 * 
 */
package com.atguigu.springmvc.crud.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.atguigu.springmvc.crud.entities.Department;
import com.atguigu.springmvc.crud.entities.Employee;




/**
 * @author lyp
 *
 */
@Component
public class EmployeeConvert implements Converter<String,Employee>{

	 
	@Override
	public Employee convert(String source) {
		Employee result=null;
		if(null == source) return null;
		String[] split = source.split(";");
		if(split!=null && split.length>0){
			result=new Employee();
			result.setLastName(split[0]);
			result.setEmail(split[1]);
			result.setGender(Integer.parseInt(split[2]) );
			Department department=new Department(); 
			department.setDepartmentName(split[3]);
			result.setDepartment(department);
		}
		return result;
	}

}
