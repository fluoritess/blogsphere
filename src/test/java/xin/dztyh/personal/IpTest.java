package xin.dztyh.personal;

import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xin.dztyh.personal.dao.VisitedInfoMapper;
import xin.dztyh.personal.pojo.VisitedInfo;
import xin.dztyh.personal.pojo.VisitedInfoExample;
import xin.dztyh.personal.util.IpAddressUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tyh
 * @Package xin.dztyh.personal
 * @Description:
 * @date 19-6-16 下午7:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PersonalApplication.class)
@WebAppConfiguration
public class IpTest {
    @Autowired
    VisitedInfoMapper visitedInfoMapper;
    @Test
    public void testSql(){
        List<VisitedInfo> list=new ArrayList<>();
        VisitedInfoExample visitedInfoExample=new VisitedInfoExample();
        VisitedInfoExample.Criteria criteria=visitedInfoExample.createCriteria();
        list=visitedInfoMapper.selectByExample(visitedInfoExample);
        System.out.println(list.size());
//        alter table tablename auto_increment = 1;
    }
    public static void main(String[] args) {

        System.out.println(IpAddressUtils.getAddress("0:0:0:5:0:0:0:1"));
    }


}
