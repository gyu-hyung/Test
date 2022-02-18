package customer;

import java.util.List;

public interface CustomerService {
	// CRUD(Create / Read / Update / Delete)
	
	// 고객 정보 삽입 저장 (C)
	void customer_insert(CustomerVO vo);
	
	// 고객 목록 조회 (R)
	List<CustomerVO> customer_list();
	
	// 고객 정보 상세 조회 (R)
	CustomerVO customer_detail(int id);
	
	// 고객 정보 변경 저장 (U)
	void customer_update(CustomerVO vo);
	
	// 고객 정보 삭제 (D)
	void customer_delete(int id);
}
