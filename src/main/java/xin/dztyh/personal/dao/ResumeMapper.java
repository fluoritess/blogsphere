package xin.dztyh.personal.dao;

import org.apache.ibatis.annotations.Param;
import xin.dztyh.personal.pojo.Resume;
import xin.dztyh.personal.pojo.ResumeExample;

import java.util.List;

public interface ResumeMapper {
    long countByExample(ResumeExample example);

    int deleteByExample(ResumeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Resume record);

    int insertSelective(Resume record);

    List<Resume> selectByExample(ResumeExample example);

    Resume selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Resume record, @Param("example") ResumeExample example);

    int updateByExample(@Param("record") Resume record, @Param("example") ResumeExample example);

    int updateByPrimaryKeySelective(Resume record);

    int updateByPrimaryKey(Resume record);
}