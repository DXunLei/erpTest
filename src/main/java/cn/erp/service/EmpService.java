package cn.erp.service;

import java.util.List;
import cn.erp.dto.EmpExample;
import cn.erp.pojo.TblEmp;

public interface EmpService {

	public TblEmp login(EmpExample empExample);
	
	public List<TblEmp> selectList(EmpExample empExample);

	public List<TblEmp> selectByName(EmpExample empExample);
}
