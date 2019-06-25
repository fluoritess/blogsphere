package xin.dztyh.personal.dao;

import org.apache.ibatis.annotations.Param;
import xin.dztyh.personal.pojo.IpAddressPool;
import xin.dztyh.personal.pojo.IpAddressPoolExample;

import java.util.List;

public interface IpAddressPoolMapper {
    long countByExample(IpAddressPoolExample example);

    int deleteByExample(IpAddressPoolExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(IpAddressPool record);

    int insertSelective(IpAddressPool record);

    List<IpAddressPool> selectByExample(IpAddressPoolExample example);

    IpAddressPool selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") IpAddressPool record, @Param("example") IpAddressPoolExample example);

    int updateByExample(@Param("record") IpAddressPool record, @Param("example") IpAddressPoolExample example);

    int updateByPrimaryKeySelective(IpAddressPool record);

    int updateByPrimaryKey(IpAddressPool record);
}