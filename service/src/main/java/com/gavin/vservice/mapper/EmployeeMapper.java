package com.gavin.vservice.mapper;

import com.gavin.vservice.dto.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> selectByName(@Param("name") String name);

    List<Employee> selectByNameWithPage(@Param("name") String name,
                                        @Param("start") int start, @Param("offset") int offset);


}