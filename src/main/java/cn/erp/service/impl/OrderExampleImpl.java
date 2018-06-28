package cn.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.erp.dto.OrderDetailExample;
import cn.erp.dto.OrderExample;
import cn.erp.mappers.TblOrderMapper;
import cn.erp.mappers.TblOrderdetailMapper;
import cn.erp.pojo.TblOrder;
import cn.erp.service.OrderService;
@Service
public class OrderExampleImpl implements OrderService{
	@Autowired
	private TblOrderMapper tblOrderMapper;
	@Autowired
	private TblOrderdetailMapper tblOrderdetailMapper;
	
	@Override
	public List<TblOrder> selectList(OrderExample orderExample) {

		List<TblOrder> list=tblOrderMapper.selectList(orderExample);
		return list;
	}

	@Override
	public List<TblOrder> selectByName(OrderExample orderExample) {
		List<TblOrder> list=tblOrderMapper.selectByName(orderExample);
		return list;
	}

	@Override
	public List<TblOrder> selectById(Long uuid) {
		List<TblOrder> list=tblOrderMapper.selectById(uuid);
		return list;
	}

	@Override
	public void updateType(Long uuid) {
		tblOrderMapper.updateType(uuid);		
	}

	@Override
	public void insertOrderAndOrderDetail(OrderExample orderExample,OrderDetailExample orderDetailExample) {
		tblOrderMapper.insertSupplierUuid(orderExample);
		System.out.println("把orderUuid设置到orderDetail中...");
		orderDetailExample.setOrderUuid(orderExample.getUuid());
		System.out.println("设置orderUuid成功");
		tblOrderdetailMapper.insertList(orderDetailExample);
		
		
	}
	

}
