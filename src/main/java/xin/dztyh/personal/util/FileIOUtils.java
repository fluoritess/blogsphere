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

    public static String FILE_PATH_PREFIX;

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

    /**
     * 获取文件
     * @param fileName
     * @param isTemp
     * @return
     */
    private static File getDirFiles(String fileName,boolean isTemp) {
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
        if (isTemp){
            if (SYSTEM.equals("server")){
                filesPath="/home/webData/index/upload/temporary/";
                FILE_PATH_PREFIX="/uploadFile/temporary/";
            }else {
                filesPath+="/static/upload/temporary/";
                FILE_PATH_PREFIX="/upload/temporary/";
            }
        }
        filesPath = filesPath + fileName;
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

    /**
     * 写入文件
     * @param fileName
     * @param value
     * @param isTemp
     * @return
     */
    public static String writeFile(String fileName, String value,boolean isTemp) {
        String pattern = getExtendsName(fileName);
        if (!pattern.equals("MD") && !pattern.equals("md")) {
            return null;
        }
        File fileDir = getDirFiles(fileName,isTemp);
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
            if (isTemp){
                return FILE_PATH_PREFIX+fileDir.getName();
            }else {
                return fileDir.getName();
            }
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

    /**
     * 读取文件
     * @param fileName
     * @param isTemp
     * @return
     */
    public static String readFile(String fileName,boolean isTemp) {
        File fileDir = getDirFiles(fileName,isTemp);
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
     * 删除文件夹下所有文件
     * @param fileDir 文件夹名
     * @param isTemp 是否为临时文件夹
     * @return
     */
    public static boolean deleteAllFiles(String fileDir,boolean isTemp){
        if (fileDir!=null){
            File dir=null;
            if (isTemp){
                dir=FileIOUtils.getDirFiles("",isTemp);
            }else {
                dir=FileIOUtils.getDirFiles(fileDir,false);
            }
            if (!dir.isDirectory()){
                return false;
            }
            String[] fileList=dir.list();
            for (String fileName:fileList){
                FileIOUtils.deleteFile(fileName,isTemp);
            }
            return true;
        }
        return false;
    }

    /**
     * 删除文件
     *
     * @param fileName 文件名
     * @return
     */
    public static boolean deleteFile(String fileName,boolean isTemp) {
        if (fileName != null) {
            File file=FileIOUtils.getDirFiles(fileName,isTemp);
//            String fileAbsolutePath = absolutePath + "/" + fileUrl;
//            File file = new File(fileAbsolutePath);
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    LogInfo.logger.info(LogInfo.getTime() + "文件" + file.getAbsolutePath() + "删除成功！");
                    return true;
                } else {
                    LogInfo.logger.error(LogInfo.getTime() + "文件" + file.getAbsolutePath() + "删除失败！");
                    return false;
                }
            } else {
                LogInfo.logger.error(LogInfo.getTime() + "文件" + file.getAbsolutePath() + "删除失败，文件不存在！");
                return false;
            }
        } else {
            LogInfo.logger.error(LogInfo.getTime() + "删除文件失败，文件路径不存在！");
            return false;
        }
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
