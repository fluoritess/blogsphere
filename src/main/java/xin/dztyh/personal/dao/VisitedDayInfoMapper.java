package xin.dztyh.personal.dao;

import org.apache.ibatis.annotations.Param;
import xin.dztyh.personal.pojo.VisitedDayInfo;
import xin.dztyh.personal.pojo.VisitedDayInfoExample;

import java.util.List;

public interface VisitedDayInfoMapper {
    long countByExample(VisitedDayInfoExample example);

    int deleteByExample(VisitedDayInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VisitedDayInfo record);

    int insertSelective(VisitedDayInfo record);

    List<VisitedDayInfo> selectByExample(VisitedDayInfoExample example);

    VisitedDayInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VisitedDayInfo record, @Param("example") VisitedDayInfoExample example);

    int updateByExample(@Param("record") VisitedDayInfo record, @Param("example") VisitedDayInfoExample example);

    int updateByPrimaryKeySelective(VisitedDayInfo record);

    int updateByPrimaryKey(VisitedDayInfo record);
}