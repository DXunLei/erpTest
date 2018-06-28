package cn.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.erp.mappers.TblGoodstypeMapper;
import cn.erp.pojo.TblGoodstype;
import cn.erp.service.GoodsTypeService;
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService{
	@Autowired
	private TblGoodstypeMapper tblGoodstypeMapper;
	
	@Override
	public List<TblGoodstype> selectList(Long supplierUuid) {
		List<TblGoodstype> list=tblGoodstypeMapper.selectList(supplierUuid);
		return list;
	}

}
