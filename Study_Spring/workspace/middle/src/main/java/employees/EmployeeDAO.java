package employees;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {

	@Autowired @Qualifier("hr") private SqlSession sql;

	public List<EmployeeVO> employee_list() {
		return sql.selectList("employee.mapper.select");
	}

	


	public List<EmployeeVO> employee_list(String tes) {
		return sql.selectList("employee.mapper.select", tes);
	}

}
