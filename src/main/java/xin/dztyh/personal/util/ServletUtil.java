package xin.dztyh.personal.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tyh
 * @Package xin.dztyh.personal.util
 * @Description:
 * @date 2019/6/19 14:39
 */
public class ServletUtil {

    /**
     * 获取request对象
     * @return
     */
    public static HttpServletRequest getRequest(){
        HttpServletRequest request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

}
