package cn.erp.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.erp.pojo.TblGoods;

public interface TblGoodsMapper {
    int deleteByPrimaryKey(Long uuid);

    int insert(TblGoods record);

    int insertSelective(TblGoods record);

    TblGoods selectByPrimaryKey(Long uuid);

    int updateByPrimaryKeySelective(TblGoods record);

    int updateByPrimaryKey(TblGoods record);
    
    public List<TblGoods> selectList(@Param("goodsTypeUuid")Long goodsTypeUuid);
    
    
}