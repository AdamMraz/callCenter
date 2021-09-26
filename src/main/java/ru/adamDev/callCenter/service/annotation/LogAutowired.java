package ru.adamDev.callCenter.service.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Autowired to Logger.
 * @see ru.adamDev.callCenter.service.beanPostProcessor.LogAutowiredBeanPostProcessor LogAutowiredBeanPostProcessor
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAutowired {
}
