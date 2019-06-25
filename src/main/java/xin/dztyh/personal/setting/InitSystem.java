package xin.dztyh.personal.setting;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import xin.dztyh.personal.util.LogInfo;
import xin.dztyh.personal.util.UploadUtils;

import java.io.*;
import java.net.URL;
import java.util.Properties;

/**
 * @author tyh
 * @Package xin.dztyh.personal.setting
 * @Description:
 * @date 19-6-25 下午10:25
 */
@Component
@Order(value = 1)
public class InitSystem implements ApplicationRunner {

    private static final String FILEPATH="config/userSetting.properties";

    /**
     * 修改config配置文件
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Properties props = System.getProperties();
        String osName = props.getProperty("os.name").toLowerCase();
        String home = props.getProperty("user.home").toLowerCase();
        InputStream in = null;
        String setting = "";
        Properties properties = new Properties();
        try {
            in = UploadUtils.class.getClassLoader().getResourceAsStream(FILEPATH);
            properties.load(in);
            setting = properties.getProperty("SYSTEM");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读取配置信息失败！");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (osName.startsWith("win")) {
            if (!setting.equals("windows")) {
                changeSystem(properties,"windows");
            } else {
                LogInfo.logger.info("系统识别正确，无需更改！");
            }
        } else if (osName.equals("linux")) {
            if (home.equals("/home/tyh")) {
                if (!setting.equals("linux")) {
                    changeSystem(properties,"linux");
                } else {
                    LogInfo.logger.info("系统识别正确，无需更改！");
                }
            } else {
                if (!setting.equals("server")) {
                    changeSystem(properties,"server");
                } else {
                    LogInfo.logger.info("系统识别正确，无需更改！");
                }
            }
        }
    }

    /**
     * 修改文件
     * @param properties
     * @param value
     */
    private void changeSystem(Properties properties, String value) {
        OutputStream fos=null;
        try {
            URL url = InitSystem.class.getClassLoader().getResource(FILEPATH);
            fos = new FileOutputStream(url.getPath());
            properties.setProperty("SYSTEM", value);
            LogInfo.logger.info("系统识别为"+value+"！");
            properties.store(fos, "更新 SYSTEM ");

        } catch (IOException e) {
            e.printStackTrace();
            LogInfo.logger.error("更新系统识别出错！");
        }finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
