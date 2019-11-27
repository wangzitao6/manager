package com.wzt.demo.utils;

 

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 高性能拷贝工具.
 *
 * @author : Sven Li
 * @version :
 * @date :2018-1-17
 */
public class BeanCopyUtil {

	/**
	 * 将目标对象转换为指定类型对象，相同属性名进行属性值复制
	 * @author Sven Li
	 * @param source
	 * @param cls
	 * @return
	 */
	public static <T> T copyToClass(Object source, Class<T> cls, String... ignoreProperties) {
		if (source == null) {
			return null;
		}
		T obj = ReflectUtil.newInstance(cls);
		BeanUtil.copyProperties(source, obj, ignoreProperties);
		return obj;
	}

	/**
	 * 复制一整个集合的对象
	 * @param source
	 * @param cls
	 * @return
	 */
	public static<T> List<T> copyList(List<?> source, Class<T> cls, String... ignoreProperties) {
		List<T> listTarget = new ArrayList<>();
		if (source != null) {
			for (Object object : source) {
				T objTarget = copyToClass(object, cls, ignoreProperties);
				listTarget.add(objTarget);
			}
		}
		return listTarget;
	}
	
 
}
