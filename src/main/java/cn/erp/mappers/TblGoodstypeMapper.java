package cn.erp.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.erp.pojo.TblGoodstype;

public interface TblGoodstypeMapper {
    int deleteByPrimaryKey(Long uuid);

    int insert(TblGoodstype record);

    int insertSelective(TblGoodstype record);

    TblGoodstype selectByPrimaryKey(Long uuid);

    int updateByPrimaryKeySelective(TblGoodstype record);

    int updateByPrimaryKey(TblGoodstype record);
    
    public List<TblGoodstype> selectList(@Param("supplierUuid")Long supplierUuid);
}