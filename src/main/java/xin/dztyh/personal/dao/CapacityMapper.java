package xin.dztyh.personal.dao;

import org.apache.ibatis.annotations.Param;
import xin.dztyh.personal.pojo.Capacity;
import xin.dztyh.personal.pojo.CapacityExample;

import java.util.List;

public interface CapacityMapper {
    long countByExample(CapacityExample example);

    int deleteByExample(CapacityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Capacity record);

    int insertSelective(Capacity record);

    List<Capacity> selectByExample(CapacityExample example);

    Capacity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Capacity record, @Param("example") CapacityExample example);

    int updateByExample(@Param("record") Capacity record, @Param("example") CapacityExample example);

    int updateByPrimaryKeySelective(Capacity record);

    int updateByPrimaryKey(Capacity record);
}