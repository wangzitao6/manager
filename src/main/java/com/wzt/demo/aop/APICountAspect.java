package com.wzt.demo.aop;

import com.wzt.demo.utils.NetworkIPUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author wangzitao
 * @create 2018-05-18 9:54
 **/
@Aspect
//@Component

public class APICountAspect {
    private final static Log LOGGER = LogFactory.getLog(APICountAspect.class);

    @Pointcut("execution (* com.wzt.demo.ctrl..*(..))")
    public void controllerAspect() {
    }

    @Pointcut("execution (* com.wzt.*.service..*(..)) || execution (* com.wzt.*.mapper..*(..))")
    public void methodAspect() {
    }

    /**
     * 切面应用范围是在com.mj.spring.aop.api下面所有的接口函数
     *
     * @param proceedingJoinPoint
     * @throws Throwable
     */
    @Around("methodAspect()")
    public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        /**
         * 接口增加日志
         */
        //获取当前请求属性集
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //获取请求
        HttpServletRequest request = sra.getRequest();
        String requestIP = NetworkIPUtils.getRequestIp(request);
        LOGGER.info(String.format("请求端ip:%s ", requestIP));
        //获取请求地址
        String requestUrl = request.getRequestURL().toString();
        //记录请求地址
        LOGGER.info(String.format("请求路径[%s]", requestUrl));
        Signature signature = proceedingJoinPoint.getSignature();
        String name = signature.getName();
        String test = signature.getDeclaringTypeName();
        String args = Arrays.toString(proceedingJoinPoint.getArgs());
        LOGGER.info("name:" + name + "||typename:" + test);
        long start = System.currentTimeMillis();
        try {
            proceedingJoinPoint.proceed();
        } catch (Exception e) {
            LOGGER.error(String.format("Method:%s call failed  parameter input:%s",
                    signature,
                    args), e);
        } finally {
            LOGGER.info(String.format("method:%s  parameter input:%s carry_out_time:%s ms",
                    signature, args, System.currentTimeMillis() - start));
        }
    }
}
