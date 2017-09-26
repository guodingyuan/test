package com.mike.token.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 类名称：IgnoreSecurity<br>
 * 类描述：忽略安全性检查<br>
 * 创建人：qinjiaxue<br>
 * 创建时间：2015年10月27日 上午11:03:36<br>  
 * @version v1.0
 *
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreSecurity {}
