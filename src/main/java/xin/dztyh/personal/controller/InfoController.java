package xin.dztyh.personal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.dztyh.personal.SpringAop.ArchivesLog;
import xin.dztyh.personal.service.InfoService;
import xin.dztyh.personal.util.PagingUtils;
import xin.dztyh.personal.util.R;

import java.util.Map;

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
}
