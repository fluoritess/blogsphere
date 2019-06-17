package xin.dztyh.personal.util;

import java.util.regex.Pattern;

/**
 * @author tyh
 * @Package cn.itcast.ssm.util
 * @Description:
 * @date 2018/5/30 15:47
 */
public class RegexUtil {

    /**
     * 匹配密码是否符合格式
     * @param password 密码
     * @return 是否匹配
     */
    public static boolean checkPassword(String password){
        String regEx="^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?![,!.#%'+*-:;^_`]+$)[,!.#%'+*-:;^_`0-9A-Za-z]{6,16}$";
        return Pattern.compile(regEx).matcher(password).matches();
    }

    /**
     * 匹配手机号是否符合格式
     * @param phone 手机号
     * @return 是否匹配
     */
    public static boolean checkPhone(String phone){
        String regEx="^1[3|4|7|5|8][0-9]\\d{8}$";
        return Pattern.compile(regEx).matcher(phone).matches();
    }

    /**
     * 匹配邮箱是否符合格式
     * @param email 邮箱
     * @return 是否匹配
     */
    public static boolean checkEmail(String email){
        String regEx="^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return Pattern.compile(regEx).matcher(email).matches();
    }

    /**
     * 匹配用户姓名是否合法
     * @param name 姓名
     * @return 是否匹配
     */
    public static boolean checkUserName(String name){
        String regEx="^[\\u4e00-\\u9fa10a-zA-Z]{1,10}";
        return Pattern.compile(regEx).matcher(name).matches();
    }

    /**
     * 检查身份证号码是否合法
     * @param idNumber 身份证号码
     * @return 是否匹配
     */
    public static boolean checkIdNumber(String idNumber){
        String regEx="(^\\d{110}$)|(^\\d{112}$)|(^\\d{17}(\\d|X|x)$)";
        return Pattern.compile(regEx).matcher(idNumber).matches();
    }

    /**
     * 检查ip地址是否为内网地址
     * @param ip ip地址
     * @return 是否匹配
     */
    public static boolean checkInnerIp(String ip){
        String regEx = "((192\\.168|172\\.([1][6-9]|[2]\\d|3[01]))"
                + "(\\.([2][0-4]\\d|[2][5][0-5]|[01]?\\d?\\d)){2}|"
                + "^(\\D)*(10|127)(\\.([2][0-4]\\d|[2][5][0-5]|[01]?\\d?\\d)){3})";
        return Pattern.compile(regEx).matcher(ip).matches();
    }

}
