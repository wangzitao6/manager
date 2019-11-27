package com.wzt.demo.utils;

import cn.hutool.core.util.StrUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 采用lambda表达式，可以方便的通过get方法取得一对应的属性，同时重构时可以自动重构.
 * <br/>
 * 举例：LambdaUtil.fieldToCol(TVsVisitinfo::getCompId);
 * 返回的值是comp_id<br/>
 * 举例：LambdaUtil.fieldToCol2(TVsVisitinfo::getCompId);
 * 返回的值是COMP_ID
 *
 * @author : huanglin
 * @version : 1.0
 * @since :2018/3/16 上午11:02 v1.0.1
 */
public class LambdaUtil {

    private static final Map<LambdaFun, SerializedLambda> CACHE = new HashMap<>();

    private LambdaUtil() {
    }

    /**
     * 通过字节码写入对象后反序列化回来得到lambda函数的信息
     *
     * @param lambda
     * @return
     */
    private static SerializedLambda extractLambda(Serializable lambda) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream(2000);
            try (ObjectOutputStream out = new ObjectOutputStream(byteOut)) {
                out.writeObject(lambda);
            }

            byte[] data = byteOut.toByteArray();
            try (ObjectInputStream in = new ObjectInputStream(
                    new ByteArrayInputStream(data)) {

                @Override
                protected Class<?> resolveClass(ObjectStreamClass desc)
                        throws IOException, ClassNotFoundException {

                    Class<?> resolvedClass = super.resolveClass(desc);
                    if (resolvedClass == java.lang.invoke.SerializedLambda.class) {
                        return SerializedLambda.class;
                    }
                    return resolvedClass;
                }

            }) {
                Object obj = in.readObject();
                return (SerializedLambda) obj;
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static <T, R> SerializedLambda resolver(LambdaFun<T, R> func) {
        if (!CACHE.containsKey(func)) {
            CACHE.put(func, extractLambda(func));
        }
        return CACHE.get(func);
    }

    /**
     * 将属性获取并转为下划线类型，注意都是小写
     *
     * @param func
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> String fieldToCol(LambdaFun<T, R> func) {
        return StrUtil.toUnderlineCase(getField(func));
    }


    /**
     * 将属性获取并转为下划线类型，注意都是大写
     *
     * @param func
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> String fieldToCol2(LambdaFun<T, R> func) {
        return StrUtil.toUnderlineCase(getField(func)).toUpperCase();
    }

    /**
     * 根据get方法获取属性名称
     *
     * @param func lambda表达式
     * @param <T>  类的类型
     * @param <R>  返回类型
     * @return
     */
    public static <T, R> String getField(LambdaFun<T, R> func) {
        SerializedLambda lambda = resolver(func);
        String method = lambda.getFunctionName();
        String field = StrUtil.lowerFirst(method.substring(3));
        return field;
    }

    public interface LambdaFun<T, R> extends Function<T, R>, Serializable {
    }

}

/**
 * 通过字节对象写入后读取反转回来读取lambda信息.
 *
 * @author : huanglin
 * @version : 1.0
 * @since :2018/3/16 下午3:24
 */
class SerializedLambda implements Serializable {
    private static final long serialVersionUID = 8025925345765570181L;
    private final String implMethodName = null;

    public String getFunctionName() {
        return implMethodName;
    }
}
