package customer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository	// 저장소 역할을 하는 클래스를 객체로 만들 때 사용
public class CustomerDAO implements CustomerService {

	@Autowired @Qualifier("hanul") private SqlSession sql;
	
	@Override
	public void customer_insert(CustomerVO vo) {
		sql.insert("customer.mapper.insert", vo);
	}

	@Override
	public List<CustomerVO> customer_list() {
		return sql.selectList("customer.mapper.list");
	}

	@Override
	public CustomerVO customer_detail(int id) {
		return sql.selectOne("customer.mapper.detail", id);
	}

	@Override
	public void customer_update(CustomerVO vo) {
		sql.update("customer.mapper.update", vo);
	}

	@Override
	public void customer_delete(int id) {
		sql.delete("customer.mapper.delete", id);
	}

}
