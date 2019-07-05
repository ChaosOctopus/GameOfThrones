package cn.zyc.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhaoyuanchao on 2019-07-05.
 */
@Target(ElementType.TYPE) //作用于类
@Retention(RetentionPolicy.CLASS) //注解的生命周期  编译期
public @interface BindPath {
    String value();
}
