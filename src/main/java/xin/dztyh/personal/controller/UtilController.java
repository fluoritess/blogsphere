package xin.dztyh.personal.controller;

import com.sun.org.apache.bcel.internal.generic.I2D;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import xin.dztyh.personal.SpringAop.ArchivesLog;
import xin.dztyh.personal.service.UtilService;
import xin.dztyh.personal.util.R;
import xin.dztyh.personal.util.RegexUtil;
import xin.dztyh.personal.util.ServletUtil;
import xin.dztyh.personal.util.UploadUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @ArchivesLog(operationName = "工具类，存储[markdown文档]", operationType = "工具类，更新信息")
    @ResponseBody
    @RequestMapping("/saveMarkdown")
    public Map<String, Object> saveMarkdown(String content, String fileName, String url, HttpServletRequest request) {
        if (url != null && url.equals("maintain_log")) {
            HttpSession session=request.getSession();
            if (session.getAttribute("user") == null)
                return R.error().put("msg", "对不起，您没有权限！");
            if (session.getAttribute("imageList")!=null){
                List<String> imageList=(List<String>) session.getAttribute("imageList");
                for (String imageUrl:imageList){
                    if (!content.contains(imageUrl)){
                        UploadUtils.deleteFile(imageUrl);
                    }
                }
                session.removeAttribute("imageList");
            }
            if (session.getAttribute("imageListUsed")!=null){
                List<String> imageList=(List<String>) session.getAttribute("imageListUsed");
                for (String imageUrl:imageList){
                    if (!content.contains(imageUrl)){
                        UploadUtils.deleteFile("/upload/img/"+imageUrl);
                    }
                }
                session.removeAttribute("imageListUsed");
            }
            if (utilService.updateMaintainInfo(content)){
                return R.ok();
            }else {
                return R.error().put("msg","保存失败！");
            }
        }
        return R.ok();
    }

    @ArchivesLog(operationName = "工具类，获取[markdown文档]", operationType = "工具类，获取信息")
    @ResponseBody
    @RequestMapping("/getMarkdown")
    public Map<String,Object> getMarkdown(String url,HttpServletRequest request){
        if (url!=null&&url.equals("maintain_log")){
            HttpSession session=request.getSession();
            if (session.getAttribute("user") == null)
                return R.error().put("msg", "对不起，您没有权限！");
            String source=utilService.getMaintainInfo();
            List<String> urls= RegexUtil.getUrlString(source);
            if (urls.size()>0){
                request.getSession().setAttribute("imageListUsed",urls);
            }
            return R.ok().put("value",source);
        }
        return R.error();
    }

    @ResponseBody
    @RequestMapping("/uploadImage")
    public Map<String,Object> uploadImage(@RequestParam(required = true,value = "editormd-image-file")MultipartFile image,
                                          HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        if (request.getSession().getAttribute("user")==null){
            map.put("success",0);
            map.put("message","对不起，您没有权限！");
            map.put("url","");
            return map;
        }
        String url=utilService.uploadImage(image);
        if (url!=null){
            map.put("success",1);
            map.put("message","图片上传成功！");
            map.put("url",ServletUtil.getBasePath() +url);
            HttpSession session=request.getSession();
            if (session.getAttribute("imageList")==null){
                List<String> imageList=new ArrayList<>();
                imageList.add(url);
                session.setAttribute("imageList",imageList);
            }else {
                List<String> imageList=(List<String>) session.getAttribute("imageList");
                imageList.add(url);
                session.setAttribute("imageList",imageList);
            }
            return map;
        }
        map.put("success",0);
        map.put("message","图片上传失败！");
        map.put("url","");
        return map;
    }
}
