package cn.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.erp.mappers.TblSupplierMapper;
import cn.erp.pojo.TblSupplier;
import cn.erp.service.SupplierService;
@Service
public class SupplierServiceImpl implements SupplierService{
	@Autowired
	private TblSupplierMapper tblSupplierMapper;
	
	@Override
	public List<TblSupplier> selectList() {
		List<TblSupplier> list=tblSupplierMapper.selectList();
		return list;
	}

}
