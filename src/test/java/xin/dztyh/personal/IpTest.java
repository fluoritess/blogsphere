package xin.dztyh.personal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xin.dztyh.personal.dao.VisitedInfoMapper;
import xin.dztyh.personal.pojo.VisitedDayInfo;
import xin.dztyh.personal.pojo.VisitedInfo;
import xin.dztyh.personal.pojo.VisitedInfoExample;
import xin.dztyh.personal.service.InfoService;
import xin.dztyh.personal.service.MainService;
import xin.dztyh.personal.util.IpAddressUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author tyh
 * @Package xin.dztyh.personal
 * @Description:
 * @date 19-6-16 下午7:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PersonalApplication.class)
@WebAppConfiguration
@Component
public class IpTest {
    @Autowired
    VisitedInfoMapper visitedInfoMapper;
    @Autowired
    InfoService infoService;
    @Autowired
    MainService mainService;
    @Test
    public void testSql(){
        List<VisitedInfo> list=new ArrayList<>();
        VisitedInfoExample visitedInfoExample=new VisitedInfoExample();
        VisitedInfoExample.Criteria criteria=visitedInfoExample.createCriteria();
        list=visitedInfoMapper.selectByExample(visitedInfoExample);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(list.size());
//        System.out.println(df.format(list.get(0).getDate()).substring(0,10));
        List<VisitedDayInfo> visitedDayInfos=new ArrayList<>();
        Map<String,Integer> day=new LinkedHashMap<>();
        List<Integer> dayNumList=new ArrayList<>();
        Map<String,Integer> newDay=new LinkedHashMap<>();
        for (VisitedInfo visitedInfo:list){
            String nowDate=df.format(visitedInfo.getDate()).substring(0,10);
            if (day.get(nowDate)==null){
                day.put(nowDate,1);
            }else {
                day.put(nowDate,day.get(nowDate)+1);
            }
        }
        for (Map.Entry<String,Integer> m:day.entrySet()){
            dayNumList.add(Integer.parseInt(m.getKey().replace("-","")));
        }
        Collections.sort(dayNumList);
        for (Integer dayNum:dayNumList){
            StringBuffer sb=new StringBuffer(dayNum.toString());
            sb.insert(6,"-");
            sb.insert(4,"-");
            newDay.put(sb.toString(),day.get(sb.toString()));
        }

        List<VisitedDayInfo> dayInfos=infoService.getDayVisitedNum();
        for (VisitedDayInfo visitedDayInfo:dayInfos){
            newDay.put(df.format(visitedDayInfo.getDate()).substring(0,10),visitedDayInfo.getNum());
        }
        ParsePosition pos = new ParsePosition(0);
        for (Map.Entry<String,Integer> m:newDay.entrySet()){
            VisitedDayInfo v=new VisitedDayInfo();
            v.setNum(m.getValue());
//            Date d=df.parse(m.getKey(),pos);
            try {
                v.setDate(df.parse(m.getKey()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (v.getDate()==null){
                continue;
            }
            mainService.addVisitedDayInfo(v);
        }
        System.out.println(newDay.toString());
//        System.out.println(dayNumList);
//        System.out.println(day.toString());
    }
    @Test
    public void testIp() {

        System.out.println(IpAddressUtils.getAddress("0:0:0:5:0:0:0:1"));
    }

    @Test
    @Scheduled(fixedRate = 2000)
    public void testTasks(){
        System.out.println("定时器");
    }


}
