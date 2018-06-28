package cn.erp.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.erp.dto.OrderExample;
import cn.erp.pojo.TblOrder;

public interface TblOrderMapper {
    int deleteByPrimaryKey(Long uuid);

    int insert(TblOrder record);

    int insertSelective(TblOrder record);

    TblOrder selectByPrimaryKey(Long uuid);

    int updateByPrimaryKeySelective(TblOrder record);

    int updateByPrimaryKey(TblOrder record);
    
    List<TblOrder> selectList(OrderExample orderExample);
    
    List<TblOrder> selectByName(OrderExample orderExample);
    
    List<TblOrder> selectById(@Param("uuid")Long uuid);
    
    void updateType(@Param("uuid")Long uuid);
   
    public void insertSupplierUuid(OrderExample orderExample);

}