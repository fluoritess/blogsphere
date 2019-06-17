package xin.dztyh.personal.dao;

import org.apache.ibatis.annotations.Param;
import xin.dztyh.personal.pojo.MainSiteInfo;
import xin.dztyh.personal.pojo.MainSiteInfoExample;

import java.util.List;

public interface MainSiteInfoMapper {
    long countByExample(MainSiteInfoExample example);

    int deleteByExample(MainSiteInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MainSiteInfo record);

    int insertSelective(MainSiteInfo record);

    List<MainSiteInfo> selectByExample(MainSiteInfoExample example);

    MainSiteInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MainSiteInfo record, @Param("example") MainSiteInfoExample example);

    int updateByExample(@Param("record") MainSiteInfo record, @Param("example") MainSiteInfoExample example);

    int updateByPrimaryKeySelective(MainSiteInfo record);

    int updateByPrimaryKey(MainSiteInfo record);
}