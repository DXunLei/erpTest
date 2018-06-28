package cn.erp.mappers;

import cn.erp.pojo.Test;

public interface TestMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);
}