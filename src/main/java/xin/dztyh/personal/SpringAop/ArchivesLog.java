package xin.dztyh.personal.SpringAop;

import java.lang.annotation.*;

/**
 * @author tyh
 * @Package xin.dztyh.personal.SpringAop
 * @Description:
 * @date 19-5-20 下午3:50
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ArchivesLog {
    /** 要执行的操作类型比如：add操作 **/
    String operationType() default "";

    /** 要执行的具体操作比如：添加用户 **/
    String operationName() default "";
}
