package xin.dztyh.personal.dao;

import org.apache.ibatis.annotations.Param;
import xin.dztyh.personal.pojo.Quotes;
import xin.dztyh.personal.pojo.QuotesExample;

import java.util.List;

public interface QuotesMapper {
    long countByExample(QuotesExample example);

    int deleteByExample(QuotesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Quotes record);

    int insertSelective(Quotes record);

    List<Quotes> selectByExample(QuotesExample example);

    Quotes selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Quotes record, @Param("example") QuotesExample example);

    int updateByExample(@Param("record") Quotes record, @Param("example") QuotesExample example);

    int updateByPrimaryKeySelective(Quotes record);

    int updateByPrimaryKey(Quotes record);
}