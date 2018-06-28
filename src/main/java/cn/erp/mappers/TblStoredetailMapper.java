package cn.erp.mappers;

import cn.erp.pojo.TblStoredetail;

public interface TblStoredetailMapper {
    int deleteByPrimaryKey(Long uuid);

    int insert(TblStoredetail record);

    int insertSelective(TblStoredetail record);

    TblStoredetail selectByPrimaryKey(Long uuid);

    int updateByPrimaryKeySelective(TblStoredetail record);

    int updateByPrimaryKey(TblStoredetail record);
}