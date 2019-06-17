package xin.dztyh.personal.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author tyh
 * @Package xin.dztyh.personal.util
 * @Description:
 * @date 19-6-16 下午6:22
 */
public class IpAddressUtils {

    private static String getResult(String urlStr, String encoding) {
        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL(urlStr);
            //新建连接实例
            con = (HttpURLConnection) url.openConnection();
            //设置连接超时时间
            con.setConnectTimeout(2000);
            //设置数据读取超时时间
            con.setReadTimeout(4000);
            //是否打开输入流
            con.setDoInput(true);
            //提交方法
            con.setRequestMethod("GET");
            //是否缓存
            con.setUseCaches(false);
            //打开连接端口
            con.connect();
            //对服务器返回数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), encoding));
            //用BufferedReader流读取数据
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            //关闭输入流
            reader.close();
            return buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            LogInfo.logger.error("网络连接失败！");
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            LogInfo.logger.error("连接超时！");
        } catch (IOException e) {
            e.printStackTrace();
            LogInfo.logger.error("创建连接实例失败！");
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return null;
    }

    //查询主方法，将查询请求传递进去
    public static String getAddress(String ip) {
        String urlStr = "http://apidata.chinaz.com/CallAPI/ip?key=15788f56cd4d43b4ac5cda3162b26d9a&ip=";
        String encoding = "utf-8";
        if (RegexUtil.isIPv6Address(ip)){
            if (ip.equals("0:0:0:0:0:0:0:1")){
                LogInfo.logger.info("IPv6内网地址:"+ip);
                return "IPv6内网地址";
            }else {
                LogInfo.logger.info("IPv6地址:"+ip);
                return "IPv6地址";
            }
        }else if (!RegexUtil.isIPv4Address(ip)){
            LogInfo.logger.error("错误Ip地址:"+ip);
            return "错误地址";
        }
        if (RegexUtil.checkInnerIp(ip)) {
            LogInfo.logger.info("IPv4内网地址:"+ip);
            return "IPv4内网地址";
        }
        urlStr = urlStr + ip;
        String returnStr = IpAddressUtils.getResult(urlStr, encoding);

        if (returnStr == null || returnStr.equals("")) {
            LogInfo.logger.error("第一次Ip：" + ip + "查询失败！进行第二次查询...");
            returnStr = IpAddressUtils.getResult(urlStr, encoding);
            if (returnStr != null && returnStr.equals("")) {
                LogInfo.logger.info("第二次Ip：" + ip + "查询成功！");
            } else {
                LogInfo.logger.error("第二次Ip：" + ip + "查询失败！");
            }
        }

        if (returnStr != null) {
            LinkedHashMap<String, Object> maps = JSON.parseObject(returnStr, LinkedHashMap.class, Feature.OrderedField);
            if (Integer.parseInt(String.valueOf(maps.get("StateCode"))) == 1) {
                Map<String, Object> result = (Map<String, Object>) maps.get("Result");
                StringBuffer stringBuffer = new StringBuffer();
                int n = 0;
                for (Map.Entry map : result.entrySet()) {
                    if (map.getValue() != null && !map.getValue().equals("")) {
                        if (map.getKey().equals("IP")) {
                            continue;
                        }
                        if (n != 0) {
                            stringBuffer.append("-");
                        }
                        stringBuffer.append(map.getValue());
                        n++;
                    }
                }
                LogInfo.logger.info(returnStr);
                return stringBuffer.toString();
            } else {
                LogInfo.logger.error("Ip地址：" + ip + "错误！");
                return "错误地址";
            }
        }
        return null;
    }
}
