package cn.erp.mappers;

import cn.erp.pojo.TblOperdetail;

public interface TblOperdetailMapper {
    int deleteByPrimaryKey(Long uuid);

    int insert(TblOperdetail record);

    int insertSelective(TblOperdetail record);

    TblOperdetail selectByPrimaryKey(Long uuid);

    int updateByPrimaryKeySelective(TblOperdetail record);

    int updateByPrimaryKey(TblOperdetail record);
}