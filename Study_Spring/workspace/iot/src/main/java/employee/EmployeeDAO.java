package employee;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository	//저장소역할~
public class EmployeeDAO implements EmployeeService {

	@Autowired @Qualifier("hr") SqlSession sql;
	@Override
	public List<EmployeeVO> employee_list() {
		return sql.selectList("employee.mapper.list");
	}

	@Override
	public EmployeeVO employee_detail(int id) {
		return sql.selectOne("employee.mapper.detail", id);
	}

	@Override
	public List<DepartmentVO> Employee_department() {
		return sql.selectList("employee.mapper.deptlist");
	}

	@Override
	public List<EmployeeVO> employee_list(String dept_id) {
		return sql.selectList("employee.mapper.dept_emp_list", dept_id);
	}

}
