package cn.erp.service;

import java.util.List;

import cn.erp.dto.OrderDetailExample;
import cn.erp.pojo.TblOrderdetail;

public interface OrderDetailService {

	public List<TblOrderdetail> selectById(Long uuid);
	
	public List<TblOrderdetail> selectByUuid(Long uuid);
	
	void insertList(OrderDetailExample orderDetailExample);
}
