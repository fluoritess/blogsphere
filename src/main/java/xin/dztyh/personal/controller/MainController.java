package xin.dztyh.personal.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xin.dztyh.personal.SpringAop.ArchivesLog;
import xin.dztyh.personal.pojo.*;
import xin.dztyh.personal.service.MainService;
import xin.dztyh.personal.service.ManagerService;
import xin.dztyh.personal.util.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author tyh
 * @Package xin.dztyh.personal.controller
 * @Description:
 * @date 19-5-25 下午8:42
 */
@Controller
public class MainController {

    @Autowired
    ManagerService managerService;

    @Autowired
    MainService mainService;

    @Autowired
    private Producer producer;


    //    @ArchivesLog(operationName = "用户访问主页，获取[页面信息]", operationType = "获取信息")
    @ResponseBody
    @RequestMapping("/getMainInfo")
    public Map<String, Object> getMainInfo() {
        MainSiteInfo mainSiteInfo = managerService.getMainSiteInfo();
        List<Project> project = managerService.getProjectInfo();
        List<ProfessionalInfo> professionalInfos = managerService.getProfessionalInfo();
        List<Capacity> capacities = managerService.getCapacityInfo();
        List<DataStatistics> dataStatistics = managerService.getDataStatisticInfo();
        List<Resume> resumes = managerService.getResumeInfo();
        List<Quotes> quotes = managerService.getQuotesInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("mainSiteInfo", mainSiteInfo);
        map.put("project", project);
        map.put("professionalInfo", professionalInfos);
        map.put("capacity", capacities);
        map.put("dataStatistics", dataStatistics);
        map.put("resume", resumes);
        map.put("quotes", quotes);
        return map;
    }

    @ArchivesLog(operationName = "用户提交反馈信息，增加[反馈信息]", operationType = "添加信息")
    @ResponseBody
    @RequestMapping("/submitFeedback")
    public Map<String, Object> submitFeedback(@RequestParam(value = "feedbackInfo") String feedbackInfo,
                                              @RequestParam(required = false, value = "contactInfo") String contactInfo,
                                              HttpServletRequest request) {
        Feedback feedback = new Feedback();
        feedback.setValue(feedbackInfo);
        feedback.setContactInfo(contactInfo);
        feedback.setIpAddress(request.getRemoteAddr());
        feedback.setDate(new Date());
        if (mainService.addFeedBack(feedback)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 生成图形验证码
     *
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @ArchivesLog(operationName = "生成验证码", operationType = "用户基本操作")
    @GetMapping("/captcha.jpg")
    public void captcha(HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session（注意：如果没有securityManager配置，则暂时无法工作，测试时先注释掉）
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.flush();
    }

    @ResponseBody
    @ArchivesLog(operationName = "用户登录", operationType = "查询信息")
    @RequestMapping("/login")
    public Map<String, Object> login(@RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     @RequestParam("verify") String verify,
                                     HttpSession session, HttpServletRequest request) {

        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if (!kaptcha.equalsIgnoreCase(verify)) {
            return R.error("验证码不正确");
        }
        try {
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, EncryptUtil.MD5ReEncrpt(password));
            subject.login(token);
        } catch (UnknownAccountException e) {
            return R.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return R.error(e.getMessage());
        } catch (AuthenticationException e) {
            return R.error("账户验证失败");
        }
        LogInfo.logger.info("用户" + ShiroUtils.getUserEntity().getUsername() + "登录成功!Ip地址:" + request.getRemoteAddr());
        session.setAttribute("user", ShiroUtils.getUserEntity());
        session.setAttribute("id", ShiroUtils.getUserEntity().getId());
        return R.ok();
    }

    /**
     * 获取当前游客信息，并反馈以往数据
     * @param map
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getVisitedInfo")
    public Map<String, Object> getVisitedInfo(@RequestBody Map<String, Object> map,
                                              HttpServletRequest request,HttpServletResponse response) {
        ServletContext application = request.getServletContext();
        //初始化总访问人数，防止取人数时出错
        if (application.getAttribute("allNum") == null) {
            application.setAttribute("allNum", 0);
        }
        String url = String.valueOf(map.get("url"));
        //开始处理数据
        String cookieName = "tyh_visit";
        boolean isNewUser = true;
        int allNum = 0, nowNum = 0, dayNum = 0;
        //查询今日访问人数
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String nowDay = df.format(new Date());
        VisitedDayInfo visitedDayInfo = mainService.getVisitedDayInfo(nowDay);
        //查找cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    LogInfo.logger.info("游客存在!Ip:"+request.getRemoteAddr());
                    nowNum = Integer.parseInt(cookie.getValue());
                    isNewUser = false;
                    //拿出当天访问量人数
                    if (visitedDayInfo != null) {
                        dayNum = visitedDayInfo.getNum();
                    }
                    //拿出访问总人数
                    allNum = mainService.getVisitedAllNum();
                    application.setAttribute("allNum", allNum);
                    break;
                }
            }
        }
        //新用户
        if (isNewUser) {
            LogInfo.logger.info("新游客!Ip:"+request.getRemoteAddr());
            VisitedInfo visitedInfo = new VisitedInfo();
            visitedInfo.setIp(request.getRemoteAddr());
            visitedInfo.setPort(request.getRemotePort());
            visitedInfo.setVisitedUrl(url);
            //查询Ip地址池中是否含有此Ip
            IpAddressPool ipAddressPool=mainService.getIpAddressByPool(visitedInfo.getIp());
            if (ipAddressPool==null){
                //如果不含有此IP，则查询ip，并存入Ip地址池
                visitedInfo.setAddress(IpAddressUtils.getAddress(visitedInfo.getIp()));
                ipAddressPool=new IpAddressPool();
                ipAddressPool.setIp(visitedInfo.getIp());
                if (visitedInfo.getAddress()==null){
                    ipAddressPool.setAddress("");
                }else {
                    ipAddressPool.setAddress(visitedInfo.getAddress());
                }
                ipAddressPool.setVisitedNum(0);
                ipAddressPool.setModifyDate(new Date());
                if(mainService.addIpAddressPool(ipAddressPool)){
                    LogInfo.logger.info("Ip地址池更新成功！加入Ip"+visitedInfo.getIp()+",地址为:"+visitedInfo.getAddress());
                }else {
                    LogInfo.logger.error("Ip地址池更新失败！");
                }
            }else {
                //如果含有此IP
                visitedInfo.setAddress(ipAddressPool.getAddress());
                //判断地址池里的查询时间是否与当前时间相差一个月
                Calendar calendar=Calendar.getInstance();
                long now=calendar.getTimeInMillis();
                calendar.setTime(ipAddressPool.getModifyDate());
                long lastly=calendar.getTimeInMillis();
                Integer day=1000*60*60*24;
                long time=day.longValue()*30;
                if (now-lastly>=time||ipAddressPool.getAddress().equals("")
                        ||ipAddressPool.getAddress().equals("错误地址")){
                    //如果相差一个月，则重新查询ip地址
                    visitedInfo.setAddress(IpAddressUtils.getAddress(visitedInfo.getIp()));
                    if (!visitedInfo.getAddress().equals(ipAddressPool.getAddress())){
                        //如果查询出来的ip地址与当前存储的不一样则更新数据库ip地址
                        ipAddressPool.setAddress(visitedInfo.getAddress());
                        ipAddressPool.setModifyDate(new Date());
                        if (mainService.UpdateIpAddressPool(ipAddressPool)){
                            LogInfo.logger.info("Ip地址池更新成功！更新Ip"+visitedInfo.getIp()+",地址为:"+visitedInfo.getAddress());
                        }else {
                            LogInfo.logger.error("Ip地址池更新失败！");
                        }
                    }
                }else {
                    //如果没有相差一个月则直接存入ip地址
                    visitedInfo.setAddress(ipAddressPool.getAddress());
                    LogInfo.logger.info("Ip地址池取得Ip"+visitedInfo.getIp()+",地址为:"+visitedInfo.getAddress());
                }
            }
            visitedInfo.setDate(new Date());
            nowNum = mainService.addVisitedInfo(visitedInfo);
            if (nowNum == 0) {
                //如果插入出错就从内存里拿出总人数
                LogInfo.logger.error("插入访问信息出错！");
                nowNum = (int) application.getAttribute("allNum");
                allNum = nowNum;
            } else {
                //插入成功就将总人数设为当前用户，并将总人数存入内存防止出错
                allNum = nowNum;
                application.setAttribute("allNum", allNum);
            }
            //开始写入cookie
            Cookie cookie=new Cookie(cookieName,nowNum+"");
            cookie.setMaxAge(3600*6);
            cookie.setPath("/index");
            response.addCookie(cookie);
            LogInfo.logger.info("写入Cookie");
            //处理当天访问人数
            if (visitedDayInfo==null){
                //当前用户为当天第一人
                visitedDayInfo=new VisitedDayInfo();
                visitedDayInfo.setNum(1);
                ParsePosition pos = new ParsePosition(0);
                visitedDayInfo.setDate(df.parse(nowDay,pos));
                if (mainService.addVisitedDayInfo(visitedDayInfo)){
                    dayNum=visitedDayInfo.getNum();
                }else {
                    LogInfo.logger.error("新存入当天访问人数出错！");
                }
            }else {
                //取出当前访问人数
                dayNum=visitedDayInfo.getNum()+1;
                visitedDayInfo.setNum(visitedDayInfo.getNum()+1);
                if (!mainService.updateVisitedDayInfo(visitedDayInfo)){
                    try {
                        Thread.sleep(1000);
                        if (!mainService.updateVisitedDayInfo(visitedDayInfo)){
                            LogInfo.logger.error("存入当天访问人数出错！");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        Map<String,Object> data=new HashMap<>();
        data.put("nowNum",nowNum);
        data.put("allNum",allNum);
        data.put("dayNum",dayNum);
        return R.ok().put("data",data);
    }
}