package cn.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.erp.dto.DepExample;
import cn.erp.mappers.TblDepMapper;
import cn.erp.pojo.TblDep;
import cn.erp.service.DepService;
@Service
public class DepServiceImpl implements DepService{
	@Autowired
	private TblDepMapper tblDepMapper;
	
	@Override
	public List<TblDep> selectList(DepExample depExample) {
		
		List<TblDep> list=tblDepMapper.selectList(depExample);
		return list;
	}

}
