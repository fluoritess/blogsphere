package xin.dztyh.personal.dao;

import org.apache.ibatis.annotations.Param;
import xin.dztyh.personal.pojo.ProfessionalInfo;
import xin.dztyh.personal.pojo.ProfessionalInfoExample;

import java.util.List;

public interface ProfessionalInfoMapper {
    long countByExample(ProfessionalInfoExample example);

    int deleteByExample(ProfessionalInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProfessionalInfo record);

    int insertSelective(ProfessionalInfo record);

    List<ProfessionalInfo> selectByExample(ProfessionalInfoExample example);

    ProfessionalInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProfessionalInfo record, @Param("example") ProfessionalInfoExample example);

    int updateByExample(@Param("record") ProfessionalInfo record, @Param("example") ProfessionalInfoExample example);

    int updateByPrimaryKeySelective(ProfessionalInfo record);

    int updateByPrimaryKey(ProfessionalInfo record);
}