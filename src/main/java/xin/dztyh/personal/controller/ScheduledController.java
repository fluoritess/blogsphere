package xin.dztyh.personal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xin.dztyh.personal.SpringAop.ArchivesLog;
import xin.dztyh.personal.pojo.IpAddressPool;
import xin.dztyh.personal.pojo.VisitedDayInfo;
import xin.dztyh.personal.service.InfoService;
import xin.dztyh.personal.service.MainService;
import xin.dztyh.personal.util.LogInfo;
import xin.dztyh.personal.util.PagingUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author tyh
 * @Package xin.dztyh.personal.controller
 * @Description:
 * @date 2019/6/25 16:20
 */
@Component
public class ScheduledController {

    @Autowired
    InfoService infoService;

    @Autowired
    MainService mainService;

    /**
     * 定时器开启，补齐昨日访问人数
     */
    @ArchivesLog(operationName = "定时器开启，[检查昨日是否有人访问]", operationType = "定时器")
    @Scheduled(cron = "0 05 00 * * ?")
    public void visitedInitTasks(){
        //拿到过去的一天时间
        Calendar cal=Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE,-1);
        Date date=cal.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateString=df.format(date);
        try {
            Date newDate=df.parse(dateString);
            Date nowData=df.parse(df.format(new Date()));
            //查询昨日是否有人访问
            List<VisitedDayInfo> list=infoService.getDayVisitedByDate(newDate);
            if (list==null||list.size()==0){
                VisitedDayInfo visitedDayInfo=new VisitedDayInfo();
                visitedDayInfo.setDate(newDate);
                visitedDayInfo.setNum(0);
                mainService.addVisitedDayInfo(visitedDayInfo);
                LogInfo.logger.info("初始化每日访问人数"+dateString+"访问量为:0");
                List<VisitedDayInfo> nowList=infoService.getDayVisitedByDate(nowData);
                if (nowList!=null&&nowList.size()>0){
                    VisitedDayInfo nowVisitedDay=nowList.get(0);
                    infoService.deleteDayVisitedById(nowVisitedDay.getId());
                    nowVisitedDay.setId(null);
                    if (mainService.addVisitedDayInfo(nowVisitedDay)){
                        LogInfo.logger.info("重新初始化每日访问人数"+df.format(nowData)+"访问量为:"+nowVisitedDay.getNum());
                    }else {
                        LogInfo.logger.error("重新初始化每日访问人数"+df.format(nowData)+"错误！");
                    }
                }
            }else {
                LogInfo.logger.info(df.format(list.get(0).getDate())+"访问量为:"+list.get(0).getNum());
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    /**
     * 定时器开启，统计昨日ip地址访问数
     */
    @ArchivesLog(operationName = "定时器开启，[统计昨日ip地址访问数]", operationType = "定时器")
    @Scheduled(cron = "0 10 00 * * ?")
    public void visitedCountTasks(){
        Calendar cal=Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE,-1);
        Date date=cal.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateString=df.format(date);
        PagingUtils pagingUtils=new PagingUtils(0,0);
        pagingUtils.setOffset(-1);
        pagingUtils.setPageSize(-1);
        pagingUtils=infoService.getPagingInfo(pagingUtils,"visited_info",null,null,"date",dateString);
        List<Map<String,Object>> list=(List<Map<String,Object>>)pagingUtils.getList();
        Map<String,Integer> ipMap=new HashMap<>();
        for (Map<String,Object> map:list){
            String ip=String.valueOf(map.get("ip"));
            if (ipMap.containsKey(ip)){
                ipMap.put(ip,ipMap.get(ip)+1);
            }else {
                ipMap.put(ip,1);
            }
        }
        for (Map.Entry<String,Integer> m:ipMap.entrySet()){
            IpAddressPool ipAddressPool=mainService.getIpAddressByPool(m.getKey());
            ipAddressPool.setVisitedNum(ipAddressPool.getVisitedNum()+m.getValue());
            mainService.UpdateIpAddressPool(ipAddressPool);
            LogInfo.logger.info(dateString+"日，Ip "+m.getKey()+"访问"+m.getValue()+"次");
        }
    }

}
