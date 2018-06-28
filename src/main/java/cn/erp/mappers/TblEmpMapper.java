package cn.erp.mappers;

import java.util.List;

import cn.erp.dto.EmpExample;
import cn.erp.pojo.TblEmp;

public interface TblEmpMapper {
    int deleteByPrimaryKey(Long uuid);

    int insert(TblEmp record);

    int insertSelective(TblEmp record);

    TblEmp selectByPrimaryKey(Long uuid);

    int updateByPrimaryKeySelective(TblEmp record);

    int updateByPrimaryKey(TblEmp record);
    
    List<TblEmp> selectByExample(EmpExample empExample);
    
    List<TblEmp> selectByName(EmpExample empExample);
}