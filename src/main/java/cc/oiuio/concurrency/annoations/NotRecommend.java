package cc.oiuio.concurrency.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 课程里用来标【不推荐】的类或写法
 */
@Target(ElementType.TYPE)//作用目标:类
@Retention(RetentionPolicy.SOURCE)//作用范围:只在源文件
public @interface NotRecommend {

	String value() default "";
}
