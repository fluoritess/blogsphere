package xin.dztyh.personal.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author tyh
 * @Package cn.itcast.ssm.util
 * @Description:
 * @date 2018/5/14 19:32
 */
public class EncryptUtil {

    private static String MD5Encrpt(String str){
        try {
            //做一个信息摘要器
            MessageDigest digest=MessageDigest.getInstance("md5");
            byte[] result=digest.digest(str.getBytes());
            StringBuffer buffer=new StringBuffer();
            //把每一个byte做一个与运算0xff
            for(byte b:result){
                int number=b&0xff;//加盐
                String s=Integer.toHexString(number);
                if(s.length()==1){
                    buffer.append("0");
                }
                buffer.append(s);
            }
            //标注md5加密结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String MD5ReEncrpt(String str){
        return MD5Encrpt(MD5Encrpt(str));
    }

}
