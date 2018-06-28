package cn.erp.service;

import java.util.List;

import cn.erp.pojo.TblGoods;

public interface GoodsService {

	public List<TblGoods> selectList(Long goodsTypeUuid);
	
    public TblGoods selectByPrimaryKey(Long uuid);

}
