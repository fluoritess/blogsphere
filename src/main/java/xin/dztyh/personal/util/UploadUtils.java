package xin.dztyh.personal.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

/**
 * @author tyh
 * @Package xin.dztyh.personal.util
 * @Description:
 * @date 19-5-20 上午10:56
 */
public class UploadUtils {

    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    public static String IMG_PATH_PREFIX;

    public static String PDF_PATH_PREFIX;

    public static String DEFAULT_PATH_PREFIX;

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
            in = UploadUtils.class.getClassLoader().getResourceAsStream("config/uploadConfig.properties");
            properties.load(in);
            //得到配置信息
            IMG_PATH_PREFIX = properties.getProperty("IMG_PATH_PREFIX");
            PDF_PATH_PREFIX = properties.getProperty("PDF_PATH_PREFIX");
            DEFAULT_PATH_PREFIX = properties.getProperty("DEFAULT_PATH_PREFIX");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读取信息失败！");
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
     * 获取文件目录
     *
     * @param pattern
     * @return
     */
    private static File getDirFile(String pattern) {

        String fileDirPath = "personal/src/main/resources/static/";
        // 构建上传文件的存放 "文件夹" 路径
        if (pattern.equals("img") || pattern.equals("IMG")) {
            fileDirPath = fileDirPath + IMG_PATH_PREFIX;
        } else if (pattern.equals("pdf") || pattern.equals("PDF")) {
            fileDirPath = fileDirPath + PDF_PATH_PREFIX;
        } else if (pattern.equals("default") || pattern.equals("DEFAULT")) {
            fileDirPath = fileDirPath + DEFAULT_PATH_PREFIX;
        }
        File fileDir = new File(fileDirPath);
        if (!fileDir.exists()) {
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }

    /**
     * 上传图片
     *
     * @param file 图片文件
     * @return
     */
    public static String uploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        //文件扩展名
        String extendsName = fileName.substring(fileName.indexOf("."));
        String nowName = fileName.substring(0, fileName.indexOf("."));
        //设置UUID
        UUID id = UUID.randomUUID();
        String[] idd = id.toString().split("-");
        String newId = idd[0] + idd[4];
        //最终的保存文件名和路径
        String newFileName = nowName + newId + extendsName;
        //转换后缀名
        extendsName = UploadUtils.translateExtends(extendsName);
        File fileDir = UploadUtils.getDirFile(extendsName);
        try {
            //写入文件
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + newFileName);
            file.transferTo(newFile);
            LogInfo.logger.info(LogInfo.getTime() + "文件" + newFile.getAbsolutePath() + "存入成功！");
            if (extendsName.equals("IMG")) {
                return UploadUtils.IMG_PATH_PREFIX + "/" + newFileName;
            } else if (extendsName.equals("PDF")) {
                return UploadUtils.PDF_PATH_PREFIX + "/" + newFileName;
            } else {
                return UploadUtils.DEFAULT_PATH_PREFIX + "/" + newFileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
//        return null;
    }

    /**
     * 删除文件
     *
     * @param fileUrl 文件地址
     * @return
     */
    public static boolean deleteFile(String fileUrl) {
        if (fileUrl != null) {
            String fileAbsolutePath = UploadUtils.getDirFile("").getAbsolutePath() + "/" + fileUrl;
            File file = new File(fileAbsolutePath);
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    LogInfo.logger.info(LogInfo.getTime() + "文件" + fileAbsolutePath + "删除成功！");
                    return true;
                } else {
                    LogInfo.logger.error(LogInfo.getTime() + "文件" + fileAbsolutePath + "删除失败！");
                    return false;
                }
            } else {
                LogInfo.logger.error(LogInfo.getTime() + "文件" + fileAbsolutePath + "删除失败，文件不存在！");
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
     * @param file
     * @return
     */
    public static String getExtendsName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
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
        }
        return "DEFAULT";
    }

}
