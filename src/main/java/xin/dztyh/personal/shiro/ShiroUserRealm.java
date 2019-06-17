package xin.dztyh.personal.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xin.dztyh.personal.pojo.User;
import xin.dztyh.personal.service.MainService;

/**
 * @author tyh
 * @Package cn.itcast.ssm.util
 * @Description: 用户自定义Realm
 * @date 18-7-20 下午3:30
 */
@Component
public class ShiroUserRealm extends AuthorizingRealm {

    @Autowired
    MainService mainService;
    /**
     * 授权
     * @param principals
     * @return
     *//*
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权...");

        User user = (User) principals.getPrimaryPrincipal();

        //用户权限列表
        Set<String> permsSet = userService.getUserPermissions(user);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }*/

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("用户认证...");
        //用户信息获取
        String userNameInput=(String)token.getPrincipal();
        String passwordInput=new String((char[])token.getCredentials());
        //查询用户信息
        User userInf=mainService.getUserInfoByUsername(userNameInput);
        //用户不存在
        if(userInf==null){
            throw new UnknownAccountException("用户不存在！");
        }
        //密码错误
        if(!passwordInput.equals(userInf.getPassword())){
            throw new IncorrectCredentialsException("密码错误！");
        }
        System.out.println("用户登陆成功!");
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userInf, userInf.getPassword(), getName());
        return info;
    }
}
