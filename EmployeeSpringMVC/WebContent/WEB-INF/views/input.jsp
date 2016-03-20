<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/testConvertEmployee" method="post">
   Employee:<input type="text" name="employee" size="50" value="ee;ee@163.com;0;105"/>
   <input type="submit" value="commitConvertEmployee">
 </form>
	
	<form:form action="${pageContext.request.contextPath}/emp" method="post" modelAttribute="employee" >
		lastName:<form:input path="lastName" /> <form:errors path="lastName"/><br>
		email:<form:input path="email"/> <form:errors path="email"/> <br>
  		gender:<form:radiobuttons path="gender" items="${requestScope.gender}"/><br>  
  		department: <form:select path="department.id" items="${requestScope.department }" 
 		            itemLabel="departmentName" itemValue="id"></form:select> 
					<br>
	  birth :<form:input path="birth"/><br>
	  salary:<form:input path="salary"/>
		<input type="submit" value="save_commit">
	</form:form>
	ss<fmt:message key="i18n.username"></fmt:message>
   aa<fmt:message key="i18n.password"></fmt:message>
</body>
</html>