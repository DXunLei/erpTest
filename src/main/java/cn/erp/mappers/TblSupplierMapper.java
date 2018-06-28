package cn.erp.mappers;

import java.util.List;

import cn.erp.pojo.TblSupplier;

public interface TblSupplierMapper {
    int deleteByPrimaryKey(Long uuid);

    int insert(TblSupplier record);

    int insertSelective(TblSupplier record);

    TblSupplier selectByPrimaryKey(Long uuid);

    int updateByPrimaryKeySelective(TblSupplier record);

    int updateByPrimaryKey(TblSupplier record);
    
    List<TblSupplier> selectList();
}