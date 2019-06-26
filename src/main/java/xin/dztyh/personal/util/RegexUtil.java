package xin.dztyh.personal.util;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
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
     *
     * @param password 密码
     * @return 是否匹配
     */
    public static boolean checkPassword(String password) {
        String regEx = "^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?![,!.#%'+*-:;^_`]+$)[,!.#%'+*-:;^_`0-9A-Za-z]{6,16}$";
        return Pattern.compile(regEx).matcher(password).matches();
    }

    /**
     * 匹配手机号是否符合格式
     *
     * @param phone 手机号
     * @return 是否匹配
     */
    public static boolean checkPhone(String phone) {
        String regEx = "^1[3|4|7|5|8][0-9]\\d{8}$";
        return Pattern.compile(regEx).matcher(phone).matches();
    }

    /**
     * 匹配邮箱是否符合格式
     *
     * @param email 邮箱
     * @return 是否匹配
     */
    public static boolean checkEmail(String email) {
        String regEx = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return Pattern.compile(regEx).matcher(email).matches();
    }

    /**
     * 匹配用户姓名是否合法
     *
     * @param name 姓名
     * @return 是否匹配
     */
    public static boolean checkUserName(String name) {
        String regEx = "^[\\u4e00-\\u9fa10a-zA-Z]{1,10}";
        return Pattern.compile(regEx).matcher(name).matches();
    }

    /**
     * 检查身份证号码是否合法
     *
     * @param idNumber 身份证号码
     * @return 是否匹配
     */
    public static boolean checkIdNumber(String idNumber) {
        String regEx = "(^\\d{110}$)|(^\\d{112}$)|(^\\d{17}(\\d|X|x)$)";
        return Pattern.compile(regEx).matcher(idNumber).matches();
    }

    /**
     * 检查ip地址是否为内网地址
     *
     * @param ip ip地址
     * @return 是否匹配
     */
    public static boolean checkInnerIp(String ip) {
        String regEx = "((192\\.168|172\\.([1][6-9]|[2]\\d|3[01]))"
                + "(\\.([2][0-4]\\d|[2][5][0-5]|[01]?\\d?\\d)){2}|"
                + "^(\\D)*(10|127)(\\.([2][0-4]\\d|[2][5][0-5]|[01]?\\d?\\d)){3})";
        return Pattern.compile(regEx).matcher(ip).matches();
    }

    //功能：判断IPv4地址的正则表达式：
    private static final Pattern IPV4_REGEX =
            Pattern.compile(
                    "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");

    //功能：判断标准IPv6地址的正则表达式
    private static final Pattern IPV6_STD_REGEX =
            Pattern.compile(
                    "^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");

    //功能：判断一般情况压缩的IPv6正则表达式
    private static final Pattern IPV6_COMPRESS_REGEX =
            Pattern.compile(
                    "^((?:[0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4})*)?)::((?:([0-9A-Fa-f]{1,4}:)*[0-9A-Fa-f]{1,4})?)$");

    //功能：抽取特殊的边界压缩情况
    private static final Pattern IPV6_COMPRESS_REGEX_BORDER =
            Pattern.compile(
                    "^(::(?:[0-9A-Fa-f]{1,4})(?::[0-9A-Fa-f]{1,4}){5})|((?:[0-9A-Fa-f]{1,4})(?::[0-9A-Fa-f]{1,4}){5}::)$");

    /**
     * 判断是否为合法IPv4地址
     * @param input
     * @return
     */
    public static boolean isIPv4Address(final String input){
        return IPV4_REGEX.matcher(input).matches();
    }

    /**
     * 判断是否为合法IPv6地址
     *
     * @param input ip地址
     * @return
     */
    public static boolean isIPv6Address(final String input) {
        int NUM = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ':') NUM++;
        }
        if (NUM > 7) return false;
        if (IPV6_STD_REGEX.matcher(input).matches()) {
            return true;
        }
        if (NUM == 7) {
            return IPV6_COMPRESS_REGEX_BORDER.matcher(input).matches();
        } else {
            return IPV6_COMPRESS_REGEX.matcher(input).matches();
        }
    }

    /**
     * 检查是否为url
     * @param url
     * @return
     */
    public static boolean checkURL(String url){
        String regEx = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
        return Pattern.compile(regEx).matcher(url).matches();
    }

    /**
     * 提取所有的url字符串
     * @param source
     * @return
     */
    public static List<String> getUrlString(String source){
        List<String> urls=new ArrayList<>();
        Matcher matcher= Patterns.WEB_URL.matcher(source);
        while (matcher.find()){
            urls.add(matcher.group(1));
        }
        return urls;
    }
}
