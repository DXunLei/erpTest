package cn.erp.mappers;

import cn.erp.pojo.TblStore;

public interface TblStoreMapper {
    int deleteByPrimaryKey(Long uuid);

    int insert(TblStore record);

    int insertSelective(TblStore record);

    TblStore selectByPrimaryKey(Long uuid);

    int updateByPrimaryKeySelective(TblStore record);

    int updateByPrimaryKey(TblStore record);
}