package xin.dztyh.personal.util;


import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Properties;

/**
 * @author tyh
 * @Package xin.dztyh.personal.util
 * @Description:
 * @date 2019/6/24 9:33
 */
public class FileIOUtils {

    private static String SYSTEM;

    static {
        initInfo();
    }

    /**
     * 初始化配置文件
     */
    private static void initInfo() {
        InputStream in = null;
        try {
            //加载配置文件
            Properties properties = new Properties();
            in = UploadUtils.class.getClassLoader().getResourceAsStream("config/userSetting.properties");
            properties.load(in);
            //得到配置信息
            SYSTEM = properties.getProperty("SYSTEM");
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
    }

    private static File getDirFiles(String fileName) {
        String filesPath = "";
        if (SYSTEM.equals("windows")) {
            filesPath = "G:/JAVA/IDEA_Workspace/personal/src/main/resources/";
        } else if (SYSTEM.equals("linux")) {
            filesPath = "personal/src/main/resources/";
        } else if (SYSTEM.equals("server")) {
            HttpServletRequest request = ServletUtil.getRequest();
            filesPath = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/");
        } else {
            LogInfo.logger.error("当前环境配置文件错误！");
            return null;
        }
        String pattern = getExtendsName(fileName);
        if (pattern.equals("MD") || pattern.equals("md")) {
            filesPath = filesPath + fileName;
        }
        File fileDir = new File(filesPath);
        if (!fileDir.exists()) {
            // 递归生成文件夹
            fileDir.getParentFile().mkdir();
            try {
                //创建文件
                fileDir.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileDir;
    }

    public static String writeFile(String fileName, String value) {
        File fileDir = getDirFiles(fileName);
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileDir), "UTF-8"));
            String[] values = value.split("\n");
            for (int i = 0; i < values.length; i++) {
                out.write(values[i]);
                if (i != values.length - 1) {
                    out.newLine();
                }
            }
            return fileDir.getName();
        } catch (IOException e) {
            e.printStackTrace();
            LogInfo.logger.error("文件读取错误！");
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                LogInfo.logger.error("文件关闭错误！");
            }
        }
        return null;
    }

    public static String readFile(String fileName) {
        File fileDir = getDirFiles(fileName);
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileDir), "UTF-8"));
            StringBuffer sb = new StringBuffer();
            String str = null;
            while ((str = in.readLine()) != null) {
                sb.append(str);
                sb.append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            LogInfo.logger.error("文件读取错误！");
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                LogInfo.logger.error("文件关闭错误！");
            }
        }
        return null;
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName
     * @return
     */
    public static String getExtendsName(String fileName) {
        //文件扩展名
        String extendsName = fileName.substring(fileName.indexOf("."));
        return translateExtends(extendsName);
    }

    /**
     * 将文件后缀名转换为所需要的字符串
     *
     * @param extendsName
     * @return
     */
    private static String translateExtends(String extendsName) {
        extendsName = extendsName.toUpperCase();
        if (extendsName.equals(".PNG") || extendsName.equals(".JPG") || extendsName.equals(".BMP")
                || extendsName.equals(".GIF") || extendsName.equals(".JPEG")) {
            return "IMG";
        } else if (extendsName.equals(".PDF")) {
            return "PDF";
        } else if (extendsName.equals(".MD")) {
            return "MD";
        }
        return "DEFAULT";
    }

}
