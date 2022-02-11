package employee;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository	//저장소역할~
public class EmployeeDAO  {

	@Autowired @Qualifier("hr") SqlSession sql;
	public List<EmployeeVO> employee_list() {
		return sql.selectList("employee.mapper.list");
	}

	public EmployeeVO employee_detail(int id) {
		return sql.selectOne("employee.mapper.detail", id);
	}

	public List<EmployeeVO> Employee_department() {
		
		return sql.selectList("employee.mapper.deptlist");
	}

	public List<EmployeeVO> employee_list(String dept_id) {
		return sql.selectList("employee.mapper.dept_emp_list", dept_id);
	}
	
	public List<EmployeeVO2> employee_select(String data){
		return sql.selectList("employee.mapper.select", data);
	}

}
