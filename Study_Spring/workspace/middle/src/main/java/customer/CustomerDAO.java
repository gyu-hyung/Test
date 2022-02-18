package customer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository	// 저장소 역할을 하는 클래스를 객체로 만들 때 사용
public class CustomerDAO  {

	@Autowired @Qualifier("hanul") private SqlSession sql;
	
	public void customer_insert(CustomerVO vo) {
		// TODO Auto-generated method stub

	}

	public List<CustomerVO> customer_list() {
		return sql.selectList("customer.mapper.list");
	}

	public CustomerVO customer_detail(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void customer_update(CustomerVO vo) {
		int result = sql.update("customer.mapper.update",vo);
		System.out.println(result);

	}

	public void customer_delete(int id) {
		int result = sql.delete("customer.mapper.delete",id);
		System.out.println(result);

	}

}
