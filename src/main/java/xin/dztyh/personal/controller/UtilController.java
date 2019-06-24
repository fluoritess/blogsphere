package xin.dztyh.personal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.dztyh.personal.service.UtilService;
import xin.dztyh.personal.util.R;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author tyh
 * @Package xin.dztyh.personal.controller
 * @Description:
 * @date 19-6-23 下午8:22
 */
@Controller
@RequestMapping("/util")
public class UtilController {

    @Autowired
    UtilService utilService;

    @ResponseBody
    @RequestMapping("/saveMarkdown")
    public Map<String, Object> saveMarkdown(String content, String fileName, String url, HttpServletRequest request) {
        if (url != null && url.equals("maintain_log")) {
            if (request.getSession().getAttribute("user") == null)
                return R.error().put("msg", "对不起，您没有权限！");
            if (utilService.updateMaintainInfo(content)){
                return R.ok();
            }else {
                return R.error().put("msg","保存失败！");
            }
        }
        return R.ok();
    }

    @ResponseBody
    @RequestMapping("/getMarkdown")
    public Map<String,Object> getMarkdown(String url,HttpServletRequest request){
        if (url!=null&&url.equals("maintain_log")){
            if (request.getSession().getAttribute("user") == null)
                return R.error().put("msg", "对不起，您没有权限！");
            return R.ok().put("value",utilService.getMaintainInfo());
        }
        return R.error();
    }
}
