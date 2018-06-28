package cn.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.erp.dto.EmpExample;
import cn.erp.mappers.TblEmpMapper;
import cn.erp.pojo.TblEmp;
import cn.erp.service.EmpService;
@Service
public class EmpServiceImpl implements EmpService{
	@Autowired
	private TblEmpMapper tblEmpMapper;
	
	@Override
	public TblEmp login(EmpExample empExample) {	
		List<TblEmp> list=tblEmpMapper.selectByExample(empExample);
		if(list.size()>0 && list!=null) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<TblEmp> selectList(EmpExample empExample) {
		List<TblEmp> list=tblEmpMapper.selectByExample(empExample);
		return list;
	}

	@Override
	public List<TblEmp> selectByName(EmpExample empExample) {
		List<TblEmp> list=tblEmpMapper.selectByName(empExample);
		return list;
	}


}
