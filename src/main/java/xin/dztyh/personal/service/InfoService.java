package xin.dztyh.personal.service;

import xin.dztyh.personal.pojo.VisitedDayInfo;
import xin.dztyh.personal.util.PagingUtils;

import java.util.List;

/**
 * @author tyh
 * @Package xin.dztyh.personal.service
 * @Description:
 * @date 19-6-10 下午1:39
 */
public interface InfoService {

    Integer getCount(String tableName, String limitName, String limitValue, String searchName, String searchValue);

    PagingUtils getPagingInfo(PagingUtils paging, String tableName, String limitName, String limitValue,String searchName, String searchValue);

    boolean updateFeedbackInfoType(int id);

    List<VisitedDayInfo> getDayVisitedNum();

}
