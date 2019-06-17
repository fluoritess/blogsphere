package xin.dztyh.personal.service;

import xin.dztyh.personal.pojo.Feedback;
import xin.dztyh.personal.pojo.User;
import xin.dztyh.personal.pojo.VisitedDayInfo;
import xin.dztyh.personal.pojo.VisitedInfo;

/**
 * @author tyh
 * @Package xin.dztyh.personal.service
 * @Description:
 * @date 19-6-7 下午11:46
 */
public interface MainService {

    boolean addFeedBack(Feedback feedback);

    User getUserInfo(Integer id);

    User getUserInfoByUsername(String username);

    Integer addVisitedInfo(VisitedInfo visitedInfo);

    VisitedDayInfo getVisitedDayInfo(String nowDay);

    Integer getVisitedAllNum();

    boolean addVisitedDayInfo(VisitedDayInfo visitedDayInfo);

    boolean updateVisitedDayInfo(VisitedDayInfo visitedDayInfo);

}
