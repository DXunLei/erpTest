package cn.erp.service;

import java.util.List;

import cn.erp.pojo.TblGoodstype;

public interface GoodsTypeService {

	public List<TblGoodstype> selectList(Long supplierUuid);
}
