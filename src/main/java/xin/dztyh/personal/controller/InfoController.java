package xin.dztyh.personal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.dztyh.personal.SpringAop.ArchivesLog;
import xin.dztyh.personal.pojo.VisitedDayInfo;
import xin.dztyh.personal.service.InfoService;
import xin.dztyh.personal.service.MainService;
import xin.dztyh.personal.util.PagingUtils;
import xin.dztyh.personal.util.R;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author tyh
 * @Package xin.dztyh.personal.controller
 * @Description:
 * @date 19-6-10 下午1:37
 */
@Controller
@RequestMapping("/info")
public class InfoController {

    @Autowired
    InfoService infoService;

    @Autowired
    MainService mainService;

    /**
     * 获取反馈信息
     * @return
     */
    @ArchivesLog(operationName = "获取[反馈信息]", operationType = "获取信息")
    @ResponseBody
    @RequestMapping("/getFeedbackInfo")
    public Map<String,Object> getFeedbackInfo(@RequestParam(value = "pageSize",required = false) String pageSize,
                                              @RequestParam(value = "nowPage",required = false) String nowPage,
                                              @RequestParam(value = "limitName",required = false) String limitName,
                                              @RequestParam(value = "limitValue",required = false) String limitValue){
        PagingUtils paging=new PagingUtils(nowPage,pageSize);
        paging.setAllDataNum(infoService.getCount("feedback",limitName,limitValue,null,null));
        paging=infoService.getPagingInfo(paging,"feedback",limitName,limitValue,null,null);
        return R.ok().put("data",paging);
    }

    /**
     * 获取未读反馈信息数目
     * @return
     */
    @ArchivesLog(operationName = "获取[未读反馈信息数目]", operationType = "获取信息")
    @ResponseBody
    @RequestMapping("/getNotReadFeedbackNum")
    public Map<String,Object> getNotReadFeedbackNum(){
        int num=infoService.getCount("feedback","is_read","0",null,null);
        return R.ok().put("num",num);
    }

    /**
     * 阅读反馈信息
     * @return
     */
    @ArchivesLog(operationName = "修改[反馈信息状态]", operationType = "修改信息")
    @ResponseBody
    @RequestMapping("/readFeedbackInfo")
    public Map<String,Object> readFeedbackInfo(@RequestBody Map<String,Object> map){
        int id=Integer.valueOf(String.valueOf(map.get("id")));
        if (infoService.updateFeedbackInfoType(id)){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 获取反馈信息
     * @return
     */
    @ArchivesLog(operationName = "获取[访问信息]", operationType = "获取信息")
    @ResponseBody
    @RequestMapping("/getVisitedInfo")
    public Map<String,Object> getVisitedInfo(@RequestParam(value = "pageSize",required = false) String pageSize,
                                              @RequestParam(value = "nowPage",required = false) String nowPage,
                                              @RequestParam(value = "searchName",required = false) String searchName,
                                              @RequestParam(value = "searchValue",required = false) String searchValue){
        if ((searchName!=null&&searchName.equals("undefined"))||(searchValue!=null&&searchValue.equals("undefined"))){
            searchName=null;
            searchValue=null;
        }
        PagingUtils paging=new PagingUtils(nowPage,pageSize);
        paging.setAllDataNum(infoService.getCount("visited_info",null,null,searchName,searchValue));
        paging=infoService.getPagingInfo(paging,"visited_info",null,null,searchName,searchValue);
        return R.ok().put("data",paging);
    }

    /**
     * 获取近日访问量信息
     * @return
     */
    @ArchivesLog(operationName = "获取[近日访问量信息]", operationType = "获取信息")
    @ResponseBody
    @RequestMapping("/getDayVisitedNum")
    public Map<String ,Object> getDayVisitedNum(@RequestBody Map<String,Object> map, HttpServletRequest request){
        Integer typeNum=Integer.parseInt(String.valueOf(map.get("type")));
        PagingUtils pagingUtils=new PagingUtils(0,typeNum);
        List<?> list=infoService.getPagingInfo(pagingUtils,"visited_day_info",null,null,null,null).getList();
        List<LinkedHashMap<String,Object>> dayInfos=(List<LinkedHashMap<String,Object>>)list;
        if (typeNum>60){
            Map<String,Integer> dayData=new LinkedHashMap<>();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
            //初始化最初的月份和访问量
            String month=df.format(dayInfos.get(0).get("date"));
            dayData.put(month,Integer.parseInt(String.valueOf(dayInfos.get(0).get("num"))));
            for (LinkedHashMap<String,Object> visitedDayInfo:dayInfos){
                //如果当前月份与之前的月份相同则将map里此月份里的访问量加上今日的访问量
                if (month.equals(df.format(visitedDayInfo.get("date")))){
                    dayData.put(month,dayData.get(month)+Integer.parseInt(String.valueOf(visitedDayInfo.get("num"))));
                }else {
                    //如果当前的月数与之前的月份不同，则重新初始化月份，并在map里添加访问量
                    month=df.format(visitedDayInfo.get("date"));
                    dayData.put(month,Integer.parseInt(String.valueOf(visitedDayInfo.get("num"))));
                }
            }
            List<VisitedDayInfo> newDayData=new ArrayList<>();
            int i=0;
            for (Map.Entry<String,Integer> m:dayData.entrySet()){
                VisitedDayInfo visitedDayInfo=new VisitedDayInfo();
                visitedDayInfo.setId(i);
                visitedDayInfo.setNum(m.getValue());
                try {
                    visitedDayInfo.setDate(df.parse(m.getKey()));
                    //当前日期加一天，防止前端转换出错
                    Calendar cal=Calendar.getInstance();
                    cal.setTime(visitedDayInfo.getDate());
                    cal.add(Calendar.DATE,1);
                    visitedDayInfo.setDate(cal.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                    continue;
                }
                newDayData.add(visitedDayInfo);
                i++;
            }
            newDayData.remove(newDayData.size()-1);
            return R.ok().put("data",newDayData);
        }
        return R.ok().put("data",list);
    }

    /**
     * 获取IP连接池信息
     * @return
     */
    @ArchivesLog(operationName = "获取[IP连接池信息]", operationType = "获取信息")
    @ResponseBody
    @RequestMapping("/getIpAddressPoolInfo")
    public Map<String,Object> getIpAddressPoolInfo(@RequestParam(value = "pageSize",required = false) String pageSize,
                                             @RequestParam(value = "nowPage",required = false) String nowPage,
                                             @RequestParam(value = "searchName",required = false) String searchName,
                                             @RequestParam(value = "searchValue",required = false) String searchValue){
        if ((searchName!=null&&searchName.equals("undefined"))||(searchValue!=null&&searchValue.equals("undefined"))){
            searchName=null;
            searchValue=null;
        }
        PagingUtils paging=new PagingUtils(nowPage,pageSize);
        paging.setAllDataNum(infoService.getCount("ip_address_pool",null,null,searchName,searchValue));
        paging=infoService.getPagingInfo(paging,"ip_address_pool",null,null,searchName,searchValue);
        return R.ok().put("data",paging);
    }

}
