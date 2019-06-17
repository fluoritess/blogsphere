package xin.dztyh.personal.dao;

import org.apache.ibatis.annotations.Param;
import xin.dztyh.personal.pojo.DataStatistics;
import xin.dztyh.personal.pojo.DataStatisticsExample;

import java.util.List;

public interface DataStatisticsMapper {
    long countByExample(DataStatisticsExample example);

    int deleteByExample(DataStatisticsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataStatistics record);

    int insertSelective(DataStatistics record);

    List<DataStatistics> selectByExample(DataStatisticsExample example);

    DataStatistics selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataStatistics record, @Param("example") DataStatisticsExample example);

    int updateByExample(@Param("record") DataStatistics record, @Param("example") DataStatisticsExample example);

    int updateByPrimaryKeySelective(DataStatistics record);

    int updateByPrimaryKey(DataStatistics record);
}