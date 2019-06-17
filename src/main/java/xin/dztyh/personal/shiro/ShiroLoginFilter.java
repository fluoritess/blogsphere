package xin.dztyh.personal.shiro;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.servlet.AdviceFilter;
import xin.dztyh.personal.pojo.User;
import xin.dztyh.personal.util.LogInfo;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tyh
 * @Package cn.itcast.ssm.shiro
 * @Description:
 * @date 18-7-21 下午12:31
 */
public class ShiroLoginFilter extends AdviceFilter {
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        User sysUser = (User) httpServletRequest.getSession().getAttribute("user");
        if (null == sysUser && !StringUtils.contains(httpServletRequest.getRequestURI(), "/login")) {
            String requestedWith = httpServletRequest.getHeader("X-Request-With");
            if (StringUtils.isNotEmpty(requestedWith) && StringUtils.equals(requestedWith, "XMLHttpRequest")) {//如果是ajax返回指定数据
                Map<String,Object> map = new HashMap<>();
                map.put("code", "-999");
                map.put("msg", "未登录");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentType("application/json");
                httpServletResponse.getWriter().write(JSONObject.toJSONString(map));
                return false;
            } else {//不是ajax进行重定向处理
                LogInfo.logger.info("登录拦截器!拦截地址:"+httpServletRequest.getRequestURI()+",Ip地址:"+httpServletRequest.getRemoteAddr());
                httpServletResponse.sendRedirect("/index/");
                return false;
            }
        }
        return true;
    }
}
