package xin.dztyh.personal.service;

import xin.dztyh.personal.pojo.*;

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

    IpAddressPool getIpAddressByPool(String ip);

    boolean UpdateIpAddressPool(IpAddressPool ipAddressPool);

    boolean addIpAddressPool(IpAddressPool ipAddressPool);

}
