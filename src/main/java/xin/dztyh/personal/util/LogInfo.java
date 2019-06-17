package xin.dztyh.personal.util;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tyh
 * @Package xin.dztyh.personal.util
 * @Description:
 * @date 19-5-20 下午3:42
 */
public class LogInfo {
    public final static Logger logger = Logger.getLogger("user");

    public static String getTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}
