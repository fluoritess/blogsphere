package xin.dztyh.personal;

import xin.dztyh.personal.util.IpAddressUtils;

/**
 * @author tyh
 * @Package xin.dztyh.personal
 * @Description:
 * @date 19-6-16 下午7:24
 */
public class IpTest {
    public static void main(String[] args) {
        System.out.println(IpAddressUtils.getAddress("0:0:0:5:0:0:0:1"));
    }
}
