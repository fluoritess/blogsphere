package xin.dztyh.personal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.dztyh.personal.dao.*;
import xin.dztyh.personal.pojo.*;
import xin.dztyh.personal.service.MainService;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author tyh
 * @Package xin.dztyh.personal.service.impl
 * @Description:
 * @date 19-6-7 下午11:47
 */
@Service
public class MainServiceImpl implements MainService {

    @Autowired
    FeedbackMapper feedbackMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    VisitedInfoMapper visitedInfoMapper;

    @Autowired
    VisitedDayInfoMapper visitedDayInfoMapper;

    @Autowired
    UtilMapper utilMapper;

    @Override
    public boolean addFeedBack(Feedback feedback) {
        return feedbackMapper.insertSelective(feedback) != 0;
    }

    @Override
    public User getUserInfo(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getUserInfoByUsername(String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public Integer addVisitedInfo(VisitedInfo visitedInfo) {
        if (visitedInfoMapper.insertSelective(visitedInfo) != 0) {
            if (visitedInfo.getId() != null) {
                //返回插入后的id
                return visitedInfo.getId();
            }
        }
        return 0;
    }

    @Override
    public VisitedDayInfo getVisitedDayInfo(String nowDay) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date nowDayDate=df.parse(nowDay,pos);
        VisitedDayInfoExample visitedDayInfoExample=new VisitedDayInfoExample();
        VisitedDayInfoExample.Criteria criteria=visitedDayInfoExample.createCriteria();
        criteria.andDateEqualTo(nowDayDate);
        List<VisitedDayInfo> infos=visitedDayInfoMapper.selectByExample(visitedDayInfoExample);
        if(infos.size()!=0){
            return infos.get(0);
        }
        return null;
    }

    @Override
    public Integer getVisitedAllNum() {
        return utilMapper.selectCount("visited_info",null,null,null,null);
    }

    @Override
    public boolean addVisitedDayInfo(VisitedDayInfo visitedDayInfo) {
        return visitedDayInfoMapper.insertSelective(visitedDayInfo)!=0;
    }

    @Override
    public boolean updateVisitedDayInfo(VisitedDayInfo visitedDayInfo) {
        return visitedDayInfoMapper.updateByPrimaryKeySelective(visitedDayInfo)!=0;
    }
}
