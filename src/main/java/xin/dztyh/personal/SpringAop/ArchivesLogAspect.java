package xin.dztyh.personal.SpringAop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import xin.dztyh.personal.util.LogInfo;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

/**
 * @author tyh
 * @Package xin.dztyh.personal.SpringAop
 * @Description:
 * @date 19-5-20 下午3:53
 */
@Component
@Aspect
public class ArchivesLogAspect {

    private long startTimeMillis = 0; // 开始时间
    private long endTimeMillis = 0; // 结束时间

    @Pointcut("execution(* xin.dztyh.personal.controller..*Controller.*(..)) && !execution(* xin.dztyh.personal.controller.MainController.get*(..))")
    public void userLog(){}

    @Before("userLog()")
    public void before(){
        //记录时间
        startTimeMillis=System.currentTimeMillis();
    }

    @After("userLog()")
    public void after(JoinPoint joinPoint){
        //获取类名
        String targetName=joinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName=joinPoint.getSignature().getName();
        Object[] arguments=joinPoint.getArgs();
        Class targetClass=null;
        try {
            targetClass=Class.forName(targetName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //获得方法列表
        Method[] methods=targetClass.getMethods();
        String operationName="";
        //遍历方法列表，取得方法对象
        for(Method method:methods){
            if(method.getName().equals(methodName)){
                //获得方法的形参类型
                Class[] classes=method.getParameterTypes();
                //验证是否符合要求
                if(classes!=null&&classes.length==arguments.length&&method.getAnnotation(ArchivesLog.class)!=null){
                    //取得注释
                    operationName=method.getAnnotation(ArchivesLog.class).operationName();
                    break;
                }
            }
        }
        endTimeMillis=System.currentTimeMillis();
        //格式化开始时间
        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTimeMillis);
        //格式化结束时间
        String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTimeMillis);
        LogInfo.logger.info(" 操作方法: "+operationName+" 操作开始时间: "+startTime +" 操作结束时间: "+endTime);
    }

}
