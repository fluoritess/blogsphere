package xin.dztyh.personal.dao;

import org.apache.ibatis.annotations.Param;
import xin.dztyh.personal.pojo.VisitedInfo;
import xin.dztyh.personal.pojo.VisitedInfoExample;

import java.util.List;

public interface VisitedInfoMapper {
    long countByExample(VisitedInfoExample example);

    int deleteByExample(VisitedInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(VisitedInfo record);

    int insertSelective(VisitedInfo record);

    List<VisitedInfo> selectByExample(VisitedInfoExample example);

    VisitedInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") VisitedInfo record, @Param("example") VisitedInfoExample example);

    int updateByExample(@Param("record") VisitedInfo record, @Param("example") VisitedInfoExample example);

    int updateByPrimaryKeySelective(VisitedInfo record);

    int updateByPrimaryKey(VisitedInfo record);
}