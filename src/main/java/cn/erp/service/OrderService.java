package cn.erp.service;

import java.util.List;

import cn.erp.dto.OrderDetailExample;
import cn.erp.dto.OrderExample;
import cn.erp.pojo.TblOrder;

public interface OrderService {

	public List<TblOrder> selectList(OrderExample orderExample);
	
	public List<TblOrder> selectByName(OrderExample orderExample);
	
	public List<TblOrder> selectById(Long uuid);
	
	public void updateType(Long uuid);
	
	public void insertOrderAndOrderDetail(OrderExample orderExample,OrderDetailExample orderDetailExample);

}
