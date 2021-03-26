package com.wzt.demo.aop;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author wangzitao
 * @create 2018-05-16 17:11
 */

@Aspect
@Component
public class CommonLoggerAspect {
    /**
     * @Aspect 该注解标示该类为切面类，切面是由通知和切点组成的
     * @Component 注册到Spring容器，必须加入这个注解
     */
    private static final Logger log = LoggerFactory.getLogger(CommonLoggerAspect.class);

    /**
     * execution() 方法主体
     * 第一个 "*"标示返回的值任意类型
     * com.wzt.example.ctrl 路径
     * 包名后面的".." 当前包及其子包
     * 第二个"*", 表明为所有类下的所有方法
     * (..)括号标示参数，".."任意入参的方法
     */
    @Pointcut("execution (* com.wzt.demo.*.ctrl..*(..))")
    public void controllerAspect() {
    }

    @Pointcut("execution (* com.wzt.demo.*.service..*(..)) || execution (* com.wzt.demo.*.mapper..*(..))")
    public void methodAspect() {
    }

    @Around("controllerAspect()")
    public Object controllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        /**
         * 接口增加日志
         */
        String classAndMethodName = null;
        //获取当前请求属性集
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取请求
        HttpServletRequest request = sra.getRequest();
        //获取请求地址
        String requestUrl = request.getRequestURL().toString();
        //记录请求地址
        //记录请求开始时间
        long startTime = System.currentTimeMillis();
        try {
            Class<?> target = joinPoint.getTarget().getClass();
            Class<?>[] par = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
            String methodName = joinPoint.getSignature().getName();
            //获取当前执行方法
            Method currentMethod = target.getMethod(methodName, par);
            //拼接输出字符串
            classAndMethodName = target.getClass() + "的" + currentMethod.getName() + "方法";
            log.info("正在执行：{}", classAndMethodName);
            //获取切点参数
            List<Object> list = Arrays.asList(joinPoint.getArgs());
            if (list != null) {
                if (list.size() > 1) {
                    list.stream().filter(x -> x instanceof Serializable).forEach(x -> {
                        if (x instanceof MultipartFile) {
                            log.debug("入参:{}", null == x ? "空文件" : ((MultipartFile) x).getName());
                        } else {
                            log.debug("入参:{}", JSONObject.toJSONString(x, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty));
                        }
                    });
                } else {
                    if (list.size() > 0 && list.get(0) instanceof Serializable) {
                        log.debug("入参：{}", JSONObject.toJSONString(list.get(0), SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty));
                    }
                }
            }

        } catch (Throwable e) {
            log.error("记录日志的时候出错:", e);
        }
        Object object = joinPoint.proceed();
        if (object != null) {
            log.debug("执行方法：{}，返回参数:{}", classAndMethodName, JSONObject.toJSONString(object));
        } else {
            log.debug("执行方法：{}，无返回参数！", classAndMethodName);
        }
        long endTime = System.currentTimeMillis();
        log.info("请求路径[{}]响应时间{}毫秒", requestUrl, endTime - startTime);
        return object;
    }

    @Around("methodAspect()")
    public Object methodAround(ProceedingJoinPoint joinPoint) throws Throwable {
        /**
         * 方法增加日志
         */
        Object object;
        Method currentMethod = null;
        String classAndMethodName = null;
        try {
            //获取当前执行点
            MethodSignature mig = (MethodSignature) joinPoint.getSignature();
            //获取当前切点目标对象
            Object target = joinPoint.getTarget();
            //获取切点参数
            List<Object> list = Arrays.asList(joinPoint.getArgs());
            //获取当前执行方法
            currentMethod = target.getClass().getMethod(mig.getName(), mig.getParameterTypes());
            //拼接输出字符串
            classAndMethodName = target.getClass() + "的" + currentMethod.getName() + "方法";
            log.debug("正在执行：{}", classAndMethodName);
            if (list != null) {
                if (list.size() > 1) {
                    list.stream().filter(x -> x instanceof Serializable).forEach(x -> {
                        if (x instanceof MultipartFile) {
                            log.debug("入参:{}", null == x ? "空文件" : ((MultipartFile) x).getName());
                        } else {
                            log.debug("入参:{}", JSONObject.toJSONString(x), SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty);
                        }
                    });
                } else {
                    if (list.size() > 0 && list.get(0) instanceof Serializable) {
                        log.debug("入参：{}", JSONObject.toJSONString(list.get(0), SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty));
                    }
                }
            }

        } catch (Throwable e) {
            log.error("记录日志的时候出错:", e);
        }
        object = joinPoint.proceed();
        if (object != null && currentMethod != null) {
            log.debug("执行方法：{}，返回参数:{}", classAndMethodName, JSONObject.toJSONString(object));
        } else {
            log.debug("执行方法不存在或执行中断无输出！");
            return null;
        }

        return object;
    }
}
