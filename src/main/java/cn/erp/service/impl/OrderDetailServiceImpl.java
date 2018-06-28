package cn.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.erp.dto.OrderDetailExample;
import cn.erp.mappers.TblOrderdetailMapper;
import cn.erp.pojo.TblOrderdetail;
import cn.erp.service.OrderDetailService;
@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	@Autowired
	private TblOrderdetailMapper tblOrderdetailMapper;
	
	@Override
	public List<TblOrderdetail> selectById(Long uuid ) {
		List<TblOrderdetail> list=tblOrderdetailMapper.selectById(uuid);
		return list;
	}

	@Override
	public List<TblOrderdetail> selectByUuid(Long uuid) {
		List<TblOrderdetail> list=tblOrderdetailMapper.selectByUuid(uuid);
		return list;
	}

	@Override
	public void insertList(OrderDetailExample orderDetailExample) {
		tblOrderdetailMapper.insertList(orderDetailExample);
		
	}

}
