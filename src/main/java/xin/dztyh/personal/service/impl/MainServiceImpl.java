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
import java.util.Map;

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
        List<Map<String, Object>> list = utilMapper.selectPaging("visited_day_info", 0, 5, null, null, "date", nowDay);
        if (list.size() != 0) {
            VisitedDayInfo visitedDayInfo = new VisitedDayInfo();
            visitedDayInfo.setId(Integer.parseInt(String.valueOf(list.get(0).get("id"))));
            visitedDayInfo.setNum(Integer.parseInt(String.valueOf(list.get(0).get("num"))));
            visitedDayInfo.setDate((Date) list.get(0).get("date"));
            return visitedDayInfo;
        }
        return null;
    }

    @Override
    public Integer getVisitedAllNum() {
        return Integer.parseInt(String.valueOf(utilMapper.selectPaging("visited_info",
                0, 1, null, null, null, null).get(0).get("id")));
    }

    @Override
    public boolean addVisitedDayInfo(VisitedDayInfo visitedDayInfo) {
        return visitedDayInfoMapper.insertSelective(visitedDayInfo) != 0;
    }

    @Override
    public boolean updateVisitedDayInfo(VisitedDayInfo visitedDayInfo) {
        return visitedDayInfoMapper.updateByPrimaryKeySelective(visitedDayInfo) != 0;
    }
}
