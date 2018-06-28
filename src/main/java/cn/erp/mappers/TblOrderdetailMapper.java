package cn.erp.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.erp.dto.OrderDetailExample;
import cn.erp.pojo.TblOrderdetail;

public interface TblOrderdetailMapper {
    int deleteByPrimaryKey(Long uuid);

    int insert(TblOrderdetail record);

    int insertSelective(TblOrderdetail record);

    TblOrderdetail selectByPrimaryKey(Long uuid);

    int updateByPrimaryKeySelective(TblOrderdetail record);

    int updateByPrimaryKey(TblOrderdetail record);
    
    List<TblOrderdetail> selectById(@Param("uuid")Long uuid);
    
    List<TblOrderdetail> selectByUuid(@Param("uuid")Long uuid);
    
    void insertList(OrderDetailExample orderDetailExample);
}