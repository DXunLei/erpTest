package cn.erp.service;

import java.util.List;

import cn.erp.dto.DepExample;
import cn.erp.pojo.TblDep;

public interface DepService {

	public List<TblDep> selectList(DepExample depExample);
}
