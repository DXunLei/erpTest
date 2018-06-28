package cn.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.erp.mappers.TblGoodsMapper;
import cn.erp.pojo.TblGoods;
import cn.erp.service.GoodsService;
@Service
public class GoodsServiceImpl implements GoodsService{
	@Autowired
	private TblGoodsMapper tblGoodsMapper;
	
	@Override
	public List<TblGoods> selectList(Long goodsTypeUuid) {
		List<TblGoods> list=tblGoodsMapper.selectList(goodsTypeUuid);
		return list;
	}

	@Override
	public TblGoods selectByPrimaryKey(Long uuid) {
		TblGoods list=tblGoodsMapper.selectByPrimaryKey(uuid);
		return list;
	}

}
