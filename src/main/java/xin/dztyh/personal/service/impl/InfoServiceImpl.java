package xin.dztyh.personal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.dztyh.personal.dao.FeedbackMapper;
import xin.dztyh.personal.dao.UtilMapper;
import xin.dztyh.personal.dao.VisitedDayInfoMapper;
import xin.dztyh.personal.pojo.Feedback;
import xin.dztyh.personal.pojo.VisitedDayInfo;
import xin.dztyh.personal.pojo.VisitedDayInfoExample;
import xin.dztyh.personal.service.InfoService;
import xin.dztyh.personal.util.PagingUtils;

import java.util.List;

/**
 * @author tyh
 * @Package xin.dztyh.personal.service.impl
 * @Description:
 * @date 19-6-10 下午1:40
 */
@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    UtilMapper utilMapper;

    @Autowired
    FeedbackMapper feedbackMapper;

    @Autowired
    VisitedDayInfoMapper visitedDayInfoMapper;

    @Override
    public Integer getCount(String tableName, String limitName, String limitValue,String searchName, String searchValue) {
        return utilMapper.selectCount(tableName, limitName, limitValue,searchName,searchValue);
    }

    @Override
    public PagingUtils getPagingInfo(PagingUtils paging, String tableName, String limitName, String limitValue,String searchName, String searchValue) {
        paging.setList(utilMapper.selectPaging(tableName, paging.getOffset(), paging.getPageSize(), limitName, limitValue,searchName,searchValue));
        return paging;
    }

    @Override
    public boolean updateFeedbackInfoType(int id) {
        Feedback feedback = new Feedback();
        feedback.setIsRead(1);
        feedback.setId(id);
        return feedbackMapper.updateByPrimaryKeySelective(feedback) != 0;
    }

    @Override
    public List<VisitedDayInfo> getDayVisitedNum() {
        VisitedDayInfoExample visitedDayInfoExample=new VisitedDayInfoExample();
        VisitedDayInfoExample.Criteria criteria=visitedDayInfoExample.createCriteria();
        return visitedDayInfoMapper.selectByExample(visitedDayInfoExample);
    }
}
