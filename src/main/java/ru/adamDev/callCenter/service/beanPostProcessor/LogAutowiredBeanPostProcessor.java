package ru.adamDev.callCenter.service.beanPostProcessor;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import ru.adamDev.callCenter.service.annotation.LogAutowired;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class LogAutowiredBeanPostProcessor implements BeanPostProcessor {

    /**
     * Takes all fields with the LogAutowired annotation and sets the Logger.getLogger with the class of this field.
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.getAnnotation(LogAutowired.class) != null)
                .collect(Collectors.toList())
                .forEach(field -> {
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, bean, LoggerFactory.getLogger(clazz));
                });
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
