package ru.adamDev.callCenter.service.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Task exception handler
 * @see ru.adamDev.callCenter.service.response.hendler.TaskExceptionHandlerController TaskExceptionHandlerController
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TaskExceptionHandler {
}
