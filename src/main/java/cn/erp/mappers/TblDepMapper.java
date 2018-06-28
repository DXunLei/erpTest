package cn.erp.mappers;

import java.util.List;

import cn.erp.dto.DepExample;
import cn.erp.pojo.TblDep;

public interface TblDepMapper {
    int deleteByPrimaryKey(Long uuid);

    int insert(TblDep record);

    int insertSelective(TblDep record);

    TblDep selectByPrimaryKey(Long uuid);

    int updateByPrimaryKeySelective(TblDep record);

    int updateByPrimaryKey(TblDep record);
    
    List<TblDep> selectList(DepExample depExample);
}